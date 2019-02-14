package com.lichunliang.huoyunwuliu.controller;

import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.pojo.Message;
import com.lichunliang.huoyunwuliu.service.MessageService;
import com.lichunliang.huoyunwuliu.utils.PnUtil;
import com.lichunliang.huoyunwuliu.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;
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
public class MessageController {

    @Autowired
    private MessageService messageServiceImpl;

    /**
     * 添加留言
     * @param message
     * @return
     */
    @ResponseBody
    @PostMapping("/contact-us/addMessage.html")
    public ResultData addMessage(Message message) {

        try {
            int i = messageServiceImpl.addMessage(message);
            if (i==1){
                return new ResultData().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData().error("添加留言失败,请联系管理员！");
    }

    /**
     * 显示留言信息
     * @param map
     * @param pageNum
     * @return
     */
    @GetMapping("/contact-us.html")
    public String showMessage(Map<String,Object> map,
                                  @RequestParam(name = "pageNum",defaultValue = "1")String pageNum){
        Integer pn = PnUtil.getPn(pageNum);
        PageInfo<Message> pageInfo = messageServiceImpl.getMessageList(pn);
        map.put("pageInfo",pageInfo);
        return "/contact-us.html";
    }

}
