package com.lichunliang.huoyunwuliu.controller;

import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.pojo.*;
import com.lichunliang.huoyunwuliu.service.CarInformationService;
import com.lichunliang.huoyunwuliu.utils.PnUtil;
import com.lichunliang.huoyunwuliu.utils.RedisUtil;
import com.lichunliang.huoyunwuliu.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
public class CarInformationController {

    @Autowired
    private CarInformationService carInformationServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(CarInformationController.class);
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 发布车源信息
     * @param request
     * @param carInformation
     * @param weightOrVolumeType
     * @param vehicleTypeId
     * @param vehicleLengthId
     * @param vehicleVolumeId
     * @param map
     * @return
     */
    @PostMapping("/addCarInformation.html")
    public String addCarInformation(HttpServletRequest request, CarInformation carInformation,
                                    String weightOrVolumeType,
                                    String vehicleTypeId,
                                    String vehicleLengthId,
                                    String vehicleVolumeId,
                                    Map<String, Object> map){
        redisUtil.getThreeV(map);
        if (weightOrVolumeType == null || vehicleTypeId == null || vehicleLengthId == null || vehicleVolumeId == null
                || weightOrVolumeType.trim().equals("") || vehicleTypeId.trim().equals("") || vehicleLengthId.trim().equals("")
                || vehicleVolumeId.trim().equals("")) {
            return "/freight-info.html";
        }
        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        try{
            //重量拼接
            carInformation.setOrderId(""+System.currentTimeMillis());
            carInformation.setWeightOrVolume(carInformation.getWeightOrVolume()+weightOrVolumeType);
            carInformation.setVehicleLength(new VehicleLength(Integer.parseInt(vehicleLengthId), null));
            carInformation.setVehicleType(new VehicleType(Integer.parseInt(vehicleTypeId), null));
            carInformation.setVehicleVolume(new VehicleVolume(Integer.parseInt(vehicleVolumeId), null));
            carInformation.setUser(user_token);
            carInformation.setShipperName(user_token.getRelName());
            carInformation.setStatus(1);
            int i = carInformationServiceImpl.addCarInformation(carInformation);
            if (i==1){
                return "redirect:/index.html";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "/freight-info.html";
        }
        return "/freight-info.html";
    }
    /**
     * 车源信息详情，显示全部车源信息,分页，条件查询，分页条件查询
     *
     * @param map
     * @return
     */
    @ResponseBody
    @GetMapping("/freight-details/showCheyuanxinxi.html")
    public ResultData showCheyuanxinxi(Map<String, Object> map,
                                       @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
                                       @RequestParam(name = "cheyuanDepartureCity", defaultValue = "") String cheyuanDepartureCity,
                                       @RequestParam(name = "cheyuanReachingCity", defaultValue = "") String cheyuanReachingCity,
                                       @RequestParam(name = "cheyuanVehicleTypeId", defaultValue = "0") Integer cheyuanVehicleTypeId,
                                       @RequestParam(name = "cheyuanVehicleLengthId", defaultValue = "0") Integer cheyuanVehicleLengthId) {
        Integer pn = PnUtil.getPn(pageNum);
        PageInfo<CarInformation> pageInfo = carInformationServiceImpl.getCatInformationListByUserId(pn, null,cheyuanDepartureCity,cheyuanReachingCity,cheyuanVehicleTypeId,cheyuanVehicleLengthId);
        map.put("carInformationList", pageInfo.getList());
        map.put("prePage", pageInfo.getPrePage() == 0 ? 1 : pageInfo.getPrePage());
        map.put("nextPage", pageInfo.getNextPage() == 0 ? pageInfo.getPages() : pageInfo.getNextPage());
        map.put("pages", pageInfo.getPages());
        map.put("cheyuanDepartureCity", cheyuanDepartureCity);
        map.put("cheyuanReachingCity", cheyuanReachingCity);
        map.put("cheyuanVehicleTypeId", cheyuanVehicleTypeId);
        map.put("cheyuanVehicleLengthId", cheyuanVehicleLengthId);
        redisUtil.getThreeV(map);
        for (CarInformation carInformation : pageInfo.getList()) {
            logger.info(""+carInformation);
        }
        return new ResultData().success(map);
    }
    /**
     * 我的车源
     *
     * @param map
     * @return
     */
    @ResponseBody
    @GetMapping("/freight-details/showMyCheyuanxinxi.html")
    public ResultData showMyCheyuanxinxi(Map<String, Object> map,
                                         HttpServletRequest request,
                                         @RequestParam(name = "pageNum", defaultValue = "1") String pageNum) {
        Integer pn = PnUtil.getPn(pageNum);
        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        PageInfo<CarInformation> pageInfo = carInformationServiceImpl.getCatInformationListByUserId(pn,user_token.getId(),null,null,null,null);
        map.put("carInformationList", pageInfo.getList());
        map.put("pages", pageInfo.getPages());
        map.put("prePage", pageInfo.getPrePage() == 0 ? 1 : pageInfo.getPrePage());
        map.put("nextPage", pageInfo.getNextPage() == 0 ? pageInfo.getPages() : pageInfo.getNextPage());
        for (CarInformation carInformation : pageInfo.getList()) {
            logger.info("" + carInformation);
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
    @DeleteMapping("/freight-details/delMyCheyuanxinxi.html")
    public ResultData deleteMyHuoyuanxinxi(Integer id, HttpServletRequest request) {

        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        try {
            int i = carInformationServiceImpl.deleteCarInformationByIdAndUserId(id, user_token.getId());
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
    @PutMapping("/freight-details/updateMyCheyuanxinxi.html")
    public ResultData updateMyHuoyuanxinxi(Integer status,Integer id, HttpServletRequest request){
        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        //避免再次操作数据库查询状态，直接判断修改状态
        if (status!=null && status==0){
            status = 1;
        }
        try{
            int i = carInformationServiceImpl.updateCarInformationByIdAndUserId(status,id,user_token.getId(),new Date());
            if (i==1){
                return new ResultData().success();
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResultData().error("重发失败！");
        }
        return new ResultData().error("重发失败！");
    }


}
