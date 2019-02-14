package com.lichunliang.huoyunwuliu.controller;

import com.alibaba.fastjson.JSONObject;
import com.lichunliang.huoyunwuliu.pojo.User;
import com.lichunliang.huoyunwuliu.service.UserService;
import com.lichunliang.huoyunwuliu.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>>   ", e);
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param vcode
     * @return
     */
    @PostMapping(value = "/login")
    public String login(HttpServletRequest request,HttpServletResponse response, String username, String password, String vcode,
                        Map<String, Object> map) {
        logger.info("===> 用户名：" + username + ",密码:" + password + ",验证码:" + vcode);

        String vcode_error = "";
        String user_error = "";
        try {
            //获取session中存的临时验证码
            String code = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
            //根据用户名获取用户信息
            User user = userServiceImpl.getUserByUsername(username);
            if (user != null) {
                if (!password.equals(user.getPassword())) {
                    user_error = URLEncoder.encode("用户名或密码错误！", "utf-8");
                }
                if (!code.equals(vcode)) {
                    vcode_error = URLEncoder.encode("验证码错误！", "utf-8");
                }
                if (password.equals(user.getPassword()) && code.equals(vcode)) {
                    //用户信息存进session
                    request.getSession().setAttribute("USER_TOKEN", user);
//                    Cookie cookie = new Cookie("USERNAME", String.valueOf(user.getId()));
//                    response.addCookie(cookie);
                    //清楚session中验证码信息
                    request.getSession().removeAttribute("RANDOMVALIDATECODEKEY");
                    return "redirect:/index.html";
                }

            } else {
                user_error = URLEncoder.encode("用户名或密码错误！", "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/login.html?vcode_error=" + vcode_error + "&user_error=" + user_error;
    }

    /**
     * 获取短信验证码
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/getJMS")
    public ResultData getJMS(HttpServletRequest request, HttpServletResponse response, String phoneNumber) {
        //时间戳存入cookie，redis中存放时间戳为key，value为手机验证码
        String time = String.valueOf(System.currentTimeMillis());
        Cookie newCookie = new Cookie("USER_TIME", time);
        newCookie.setMaxAge(60 * 3);
        response.addCookie(newCookie);
        //二次发送，删除之前所存验证码，
        delCode(request);
        //后端二次校验
        boolean isMobile = AccountValidatorUtil.isMobile(phoneNumber);
        if (isMobile == true) {
            //1.判断手机号是否被注册
            User user = userServiceImpl.getUserByUsername(phoneNumber);
            if (user != null) {
                return new ResultData().error("手机号已被注册！");
            }
            //2.生成手机号验证码
            String code = "";
            for (int i = 0; i < 4; i++) {
                code += Math.round(Math.random() * 9);
            }
            logger.info("手机验证码：" + code);
            //加入activemq消息队列
            jmsMessagingTemplate.convertAndSend(ActiveMqDestination.DUANXIN_QUEUE,code+","+phoneNumber+","+time);
            return new ResultData().success();
        }
        return new ResultData().error("手机号格式不正确！");
    }
    //监听消息队列，进行发送
    @JmsListener(destination = ActiveMqDestination.DUANXIN_QUEUE,containerFactory="jmsListenerContainerFactoryQueue")
    private void sendJMS(String code){
        System.out.println("验证码："+code);
        String[] split = code.split(",");
        //发送短信
        String result = JMSUtil.mobileQuery(split[1], split[0]);
        //转换成json格式
        JSONObject jsonObject = JSONObject.parseObject(result);
        //error_code = 0 表示成功
        if (jsonObject.getString("error_code").equals("0")) {
            redisTemplate.opsForValue().set(split[2], split[0]);
        }
    }


    /**
     * 注册
     *
     * @param phone_code
     * @param user
     * @return
     */
    @PostMapping(value = "/register.html")
    public String register(HttpServletRequest request, String phone_code, User user, Model model) {

        logger.info("注册用户信息：" + user);
        //拿到redis中存储的手机验证码
        String code = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("USER_TIME")) {
                //从redis中拿到临时存储的手机验证码
                code = (String) redisTemplate.opsForValue().get(cookie.getValue());
                break;
            }
        }
        //判断输入验证码与redis存储的验证码是否相同
        if (code != null && code.equals(phone_code)) {
            int i = userServiceImpl.addUser(user);
            if (i == 1) {
                //验证码正确删除redis存储的验证码
                delCode(request);
                model.addAttribute("username", user.getUsername());
                model.addAttribute("password", user.getPassword());
                return "/login.html";
            } else {
//                delCode(request);
                model.addAttribute("error", "注册失败！");
                return "/register.html";
            }
        }
        model.addAttribute("error", "手机验证码不正确！");
        return "/register.html";
    }


    /**
     * 删除手机验证码的key
     *
     * @param request
     * @return
     */
    public void delCode(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("USER_TIME")) {
                //从redis中拿到临时存储的手机验证码
                redisTemplate.delete(cookie.getName());
            }
        }
    }

}
