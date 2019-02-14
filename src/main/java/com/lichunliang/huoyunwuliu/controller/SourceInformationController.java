package com.lichunliang.huoyunwuliu.controller;

import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.pojo.*;
import com.lichunliang.huoyunwuliu.service.SourceInformationService;
import com.lichunliang.huoyunwuliu.utils.PnUtil;
import com.lichunliang.huoyunwuliu.utils.RedisUtil;
import com.lichunliang.huoyunwuliu.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
public class SourceInformationController {

    @Autowired
    private SourceInformationService sourceInformationServiceImpl;
    @Autowired
    private RedisUtil redisUtil;
    private static final Logger logger = LoggerFactory.getLogger(SourceInformationController.class);

    /**
     * 首页显示货源信息最新十条
     *
     * @return
     */
    @GetMapping(value = "/index.html")
    public String showSourceIndex(Map<String, Object> map) {
        PageInfo<SourceInformation> pageInfo = sourceInformationServiceImpl.getSourceInformationList(1, null, null, null, null);
        map.put("sourceInformationList", pageInfo.getList());
        return "/index.html";
    }

    /**
     * 发布货源信息
     *
     * @param sourceInformation
     * @param weightOrVolumeType
     * @param vehicleTypeId
     * @param vehicleLengthId
     * @param vehicleVolumeId
     * @return
     */
    @PostMapping("/addSourceInformation.html")
    public String addSourceInformation(HttpServletRequest request, SourceInformation sourceInformation,
                                       String weightOrVolumeType, String freight,
                                       String vehicleTypeId,
                                       String vehicleLengthId,
                                       String vehicleVolumeId,
                                       Map<String, Object> map
    ) {
        redisUtil.getThreeV(map);
        if (weightOrVolumeType == null || vehicleTypeId == null || vehicleLengthId == null || vehicleVolumeId == null
                || weightOrVolumeType.trim().equals("") || vehicleTypeId.trim().equals("") || vehicleLengthId.trim().equals("")
                || vehicleVolumeId.trim().equals("")) {
            return "/freight-info.html";
        }

        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        try {
            //重新设置某些属性值
            Integer.parseInt(freight);//运费输入格式不正确
            sourceInformation.setUser(user_token);
            sourceInformation.setShipperName(user_token.getRelName());
            sourceInformation.setReleaseTime(new Date());
            sourceInformation.setVehicleLength(new VehicleLength(Integer.parseInt(vehicleLengthId), null));
            sourceInformation.setVehicleType(new VehicleType(Integer.parseInt(vehicleTypeId), null));
            sourceInformation.setVehicleVolume(new VehicleVolume(Integer.parseInt(vehicleVolumeId), null));
            sourceInformation.setWeightOrVolume(sourceInformation.getWeightOrVolume() + weightOrVolumeType);
            sourceInformation.setGoodsDescription(sourceInformation.getGoodsName());
            sourceInformation.setStatus(1);
            int i = sourceInformationServiceImpl.addSourceInformation(sourceInformation);
            if (i == 1) {
                return "redirect:/index.html";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "/freight-info.html";
        }
        return "/freight-info.html";
    }



    /**
     * 货源信息详情，显示全部货源信息,分页，条件查询，分页条件查询
     *
     * @param map
     * @return
     */
    @ResponseBody
    @GetMapping("/freight-details/showHuoyuanxinxi.html")
    public ResultData showHuoyuanxinxi(Map<String, Object> map,
                                       @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
                                       @RequestParam(name = "huoyuanDepartureCity", defaultValue = "") String huoyuanDepartureCity,
                                       @RequestParam(name = "huoyuanreachingCity", defaultValue = "") String huoyuanreachingCity,
                                       @RequestParam(name = "huoyuanVehicleTypeId", defaultValue = "0") Integer huoyuanVehicleTypeId,
                                       @RequestParam(name = "huoyuanVehicleLengthId", defaultValue = "0") Integer huoyuanVehicleLengthId) {
        Integer pn = PnUtil.getPn(pageNum);
        PageInfo<SourceInformation> pageInfo = sourceInformationServiceImpl.getSourceInformationList(pn, huoyuanDepartureCity, huoyuanreachingCity, huoyuanVehicleTypeId, huoyuanVehicleLengthId);
        map.put("sourceInformationList", pageInfo.getList());
        map.put("prePage", pageInfo.getPrePage() == 0 ? 1 : pageInfo.getPrePage());
        map.put("nextPage", pageInfo.getNextPage() == 0 ? pageInfo.getPages() : pageInfo.getNextPage());
        map.put("pages", pageInfo.getPages());
        map.put("huoyuanDepartureCity", huoyuanDepartureCity);
        map.put("huoyuanreachingCity", huoyuanreachingCity);
        map.put("huoyuanVehicleTypeId", huoyuanVehicleTypeId);
        map.put("huoyuanVehicleLengthId", huoyuanVehicleLengthId);
        redisUtil.getThreeV(map);
        for (SourceInformation sourceInformation : pageInfo.getList()) {
            logger.info("" + sourceInformation);
        }
        return new ResultData().success(map);
    }

    /**
     * 我的货源
     *
     * @param map
     * @return
     */
    @ResponseBody
    @GetMapping("/freight-details/showMyHuoyuanxinxi.html")
    public ResultData showMyHuoyuanxinxi(Map<String, Object> map,
                                         HttpServletRequest request,
                                         @RequestParam(name = "pageNum", defaultValue = "1") String pageNum) {
        Integer pn = PnUtil.getPn(pageNum);
        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        PageInfo<SourceInformation> pageInfo = sourceInformationServiceImpl.getSourceInformationListByUserId(user_token.getId(), pn);
        map.put("sourceInformationList", pageInfo.getList());
        map.put("pages", pageInfo.getPages());
        map.put("prePage", pageInfo.getPrePage() == 0 ? 1 : pageInfo.getPrePage());
        map.put("nextPage", pageInfo.getNextPage() == 0 ? pageInfo.getPages() : pageInfo.getNextPage());
        for (SourceInformation sourceInformation : pageInfo.getList()) {
            logger.info("" + sourceInformation);
        }
        return new ResultData().success(map);
    }

    /**
     * 删除我的货源信息
     * @param id
     * @param request
     * @return
     */
    @ResponseBody
    @DeleteMapping("/freight-details/delMyHuoyuanxinxi.html")
    public ResultData deleteMyHuoyuanxinxi(Integer id, HttpServletRequest request) {

        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        try {
            int i = sourceInformationServiceImpl.delSourceInformationByIdAndUserId(id, user_token.getId());
            if (i==1){
                return new ResultData().success();
            }
        } catch (Exception e) {
            return new ResultData().error("删除失败!");
        }
        return new ResultData().error("删除失败!");
    }

    /**
     * 重发我的货源信息，表示更新其状态和日期改为当下时间
     * @param id
     * @param request
     * @return
     */
    @ResponseBody
    @PutMapping("/freight-details/updateMyHuoyuanxinxi.html")
    public ResultData updateMyHuoyuanxinxi(Integer status,Integer id, HttpServletRequest request){
        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        //避免再次操作数据库查询状态，直接判断修改状态
        if (status!=null && status==0){
            status = 1;
        }
        try{
            int i = sourceInformationServiceImpl.updateSourceInformationByIdAndUserId(status,id,user_token.getId(),new Date());
            if (i==1){
                return new ResultData().success();
            }
        }catch (Exception e){
            return new ResultData().error("重发失败！");
        }
        return new ResultData().error("重发失败！");
    }


}
