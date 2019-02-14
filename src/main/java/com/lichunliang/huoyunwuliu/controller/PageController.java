package com.lichunliang.huoyunwuliu.controller;

import com.lichunliang.huoyunwuliu.pojo.User;
import com.lichunliang.huoyunwuliu.pojo.VehicleLength;
import com.lichunliang.huoyunwuliu.pojo.VehicleType;
import com.lichunliang.huoyunwuliu.pojo.VehicleVolume;
import com.lichunliang.huoyunwuliu.service.UserService;
import com.lichunliang.huoyunwuliu.service.VehicleLengthService;
import com.lichunliang.huoyunwuliu.service.VehicleTypeService;
import com.lichunliang.huoyunwuliu.service.VehicleVolumeService;
import com.lichunliang.huoyunwuliu.utils.RedisUtil;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
public class PageController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userServiceImpl;

    /**
     * 跳转货源发布页面
     *
     * @param map
     * @return
     */
    @RequestMapping("/freight-info.html")
    public String freightInfo(Map<String, Object> map) {
        redisUtil.getThreeV(map);
        return "/freight-info.html";
    }

    /**
     * 互动详情
     * @param map
     * @return
     */
    @RequestMapping("/line-details.html")
    public String lineDetails(Map<String, Object> map){
        redisUtil.getTwoDriving(map);
        return "/line-details.html";
    }

    /**
     * 跳转驾照信息页面
     * @param map
     * @return
     */
    @RequestMapping("/line-interaction.html")
    public String lineinteraction(Map<String,Object> map){
        redisUtil.getTwoDriving(map);
        return "/line-interaction.html";
    }

    /**
     * 退出登录
     * @param method
     * @return
     */
    @RequestMapping("/login.html")
    public String logout(HttpServletRequest request,String method){
        if ("logout".equals(method)){
            request.getSession().removeAttribute("USER_TOKEN");
        }
        return "/login.html";
    }

    /**
     * 货源地图没资源
     * @return
     */
    @RequestMapping("/freight-map.html")
    public String freightmap(){
        return "/freight-map.html";
    }

    /**
     * 支付成功刷新首页金钱
     * @param request
     * @return
     */
    @RequestMapping("/flush/index.html")
    public String flushIndex(HttpServletRequest request){
        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        User user = userServiceImpl.getUserByUsername(user_token.getUsername());
        request.getSession().setAttribute("USER_TOKEN",user);
        return "redirect:/index.html";
    }


}
