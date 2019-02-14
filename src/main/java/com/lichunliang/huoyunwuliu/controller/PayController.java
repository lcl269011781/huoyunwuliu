package com.lichunliang.huoyunwuliu.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lichunliang.huoyunwuliu.config.AlipayConfig;
import com.lichunliang.huoyunwuliu.pojo.Order;
import com.lichunliang.huoyunwuliu.pojo.User;
import com.lichunliang.huoyunwuliu.service.OrderService;
import com.lichunliang.huoyunwuliu.service.UserService;
import com.lichunliang.huoyunwuliu.utils.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ━━━━━━━━━神兽出没━━━━━━━━━
 * <p>
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * ━━━━━━━━━感觉萌萌哒━━━━━━━━━
 */
@Controller
public class PayController {

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private OrderService orderServiceImpl;

    /**
     * 支付页面
     *
     * @param amount
     * @param request
     * @param response
     */
    @PostMapping(value = "/payAmount.html")
    public void payAmount(Float amount, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        if (amount == null || amount < 0) {
            return;
        }
        User user = (User) request.getSession().getAttribute("USER_TOKEN");
        //生成订单号，时间戳加用户手机号
        String orderNum = System.currentTimeMillis() + "-" + user.getUsername();
        Order order = new Order();
        order.setUserId(user.getId());
        order.setOrderNum(orderNum);
        order.setAmount((int) (amount * 100));
        order.setCreateDate(new Date());
        order.setOtherInfo("测试订单");
        orderServiceImpl.addOrder(order);
        try {
            PrintWriter out = response.getWriter();
            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = orderNum;
            //付款金额，必填
            String total_amount = String.valueOf(amount);
            //订单名称，必填
            String subject = "测试订单";
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
           alipayRequest.setReturnUrl(AlipayConfig.return_url);
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
            String body = "";
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println(result);
            //输出
            response.setContentType("text/html;charset=" + AlipayConfig.charset);
            out.println(result);//直接将完整的表单html输出到页面
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 回调
     *
     * @param request
     * @param response
     */
    @PostMapping("/callback.html")
    public void callback(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        try {
            PrintWriter out = response.getWriter();
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }

            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

            //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
            if (signVerified) {//验证成功
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //交易状态
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
                Order order = orderServiceImpl.getOrderByOrderNum(out_trade_no);
                if (trade_status.equals("TRADE_SUCCESS")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序
                    String sellerId = params.get("seller_id");
                    if (!sellerId.equals("2088102176971224")) {//如果商户号一致，代表是我收的钱
                        out.println("fail");
                        return;
                    }

                    String amount = params.get("total_amount");

                    if (order.getStatus() == CommonConstant.ORDER_STATUS_SUCCESS) {//如果是已经处理过的订单,则不处理
                        out.println("success");
                        return;
                    }
                    //保留两位小数
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    String orderAmount = decimalFormat.format(order.getAmount() / 100.0);
                    if (orderAmount.equals(amount)) {//金额一致
                        order.setStatus(CommonConstant.ORDER_STATUS_SUCCESS);
                        order.setResultDate(new Date());
                        order.setTradeNum(trade_no);
                        order.setResult(trade_status);
                        orderServiceImpl.updateOrder(order);
                        //加话费、发游戏道具。。。。通知卖家。。。。
                    }
                    //注意：
                    //付款完成后，支付宝系统发送该交易状态通知
                } else {
                    order.setStatus(CommonConstant.ORDER_STATUS_FAIL);
                    order.setResultDate(new Date());
                    order.setTradeNum(trade_no);
                    order.setResult(trade_status);
                    orderServiceImpl.updateOrder(order);
                }
                out.flush();
                out.println("success");    //请不要修改或删除
            } else {//验证失败
                System.out.println("签名失败");
                out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 返回
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/return.html")
    public String returnPage(HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute("USER_TOKEN");
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //该页面可做页面美工编辑
            //Order order = orderService.getOrderByOrderNum(orderNum);
            //判断交易状态，判断支付金额
            System.out.println("交易状态：" + params.get("trade_status"));
            System.out.println("支付金额：" + params.get("total_amount"));
            float total_amount = Float.parseFloat(params.get("total_amount"))*100;
            userServiceImpl.updateUser(user.getAmount()+(int)total_amount,user.getId());
            return "redirect:/flush/index.html";
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
        } else {
            //该页面可做页面美工编辑
            return "redirect:/index.html";
        }

    }




}
