package com.lichunliang.huoyunwuliu.controller;

import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.pojo.*;
import com.lichunliang.huoyunwuliu.service.VehicleTransactionService;
import com.lichunliang.huoyunwuliu.utils.PnUtil;
import com.lichunliang.huoyunwuliu.utils.RedisUtil;
import com.lichunliang.huoyunwuliu.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class VehicleTransactionController {

    @Autowired
    private VehicleTransactionService vehicleTransactionServiceImpl;
    @Autowired
    private RedisUtil redisUtil;
    private static final Logger logger = LoggerFactory.getLogger(VehicleTransactionController.class);
    @Resource
    private CommonsMultipartResolver multipartResolver;

    /**
     * 获取车辆长度，类型
     *
     * @param map
     * @return
     */
    @ResponseBody
    @GetMapping("/getThreeV.html")
    public ResultData getThreeV(Map<String, Object> map) {
        redisUtil.getThreeV(map);
        return new ResultData().success(map);
    }

    /**
     * 添加车辆交易信息
     *
     * @param addAddressCity
     * @param addPhone
     * @param addVehicleTypeId
     * @param addVehicleLengthId
     * @param request
     * @param saleCar
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/line-interaction/addVehicleTransaction.html")
    public ResultData addVehicleTransaction(String addAddressCity,
                                            String licensePlateTime,
                                            String addPhone,
                                            Integer addVehicleTypeId,
                                            Integer addVehicleLengthId,
                                            HttpServletRequest request,
                                            String vehicleDescription,
                                            Integer saleCar, Map<String, Object> map) {
        redisUtil.getThreeV(map);
        //非空判断
        if (addAddressCity == null || licensePlateTime == null || addPhone == null || addVehicleTypeId == null || addVehicleLengthId == null ||
                saleCar == null || addAddressCity.trim().equals("") || addPhone.trim().equals("") ||
                addVehicleTypeId == 0 || addVehicleLengthId == 0 || licensePlateTime.equals("")) {
            return new ResultData().error("添加车辆交易信息失败，请确保信息填写完整");
        }
        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        VehicleTransaction vehicleTransaction = new VehicleTransaction();
        vehicleTransaction.setAddressCity(addAddressCity);
        vehicleTransaction.setVehicleDescription(vehicleDescription);
        vehicleTransaction.setIdentityId(saleCar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(licensePlateTime);
            vehicleTransaction.setLicensePlateTime(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        vehicleTransaction.setPhone(addPhone);
        vehicleTransaction.setStatus(1);
        vehicleTransaction.setUser(user_token);
        vehicleTransaction.setVehicleLength(new VehicleLength(addVehicleLengthId, null));
        vehicleTransaction.setVehicleType(new VehicleType(addVehicleTypeId, null));
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String path = "F:\\intelij workspace\\huoyunwuliu\\src\\main\\resources\\static\\images\\" + file.getOriginalFilename();
                    vehicleTransaction.setImg("/images/" + file.getOriginalFilename());
                    //上传
                    try {
                        file.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            int i = vehicleTransactionServiceImpl.addVehicleTransaction(vehicleTransaction);
            if (i == 1) {
                return new ResultData().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData().error("添加失败，未知错误");
    }

    /**
     * 显示车辆交易
     * @param map
     * @param request
     * @param pageNum
     * @param showAddressCity
     * @param showSaleCar
     * @param showVehicleTypeId
     * @return
     */
    @ResponseBody
    @GetMapping("/line-interaction/showVehicleTransaction.html")
    public ResultData showVehicleTransaction(Map<String, Object> map, HttpServletRequest request,
                                             @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
                                             @RequestParam(name = "showAddressCity",defaultValue = "") String showAddressCity,
                                             @RequestParam(name = "showSaleCar",defaultValue = "0") Integer showSaleCar,
                                             @RequestParam(name = "showVehicleTypeId", defaultValue = "0") Integer showVehicleTypeId
    ) {
        redisUtil.getThreeV(map);
        Integer pn = PnUtil.getPn(pageNum);
        PageInfo<VehicleTransaction> pageInfo = vehicleTransactionServiceImpl.getVehicleTransactionList(pn, null, showSaleCar, showAddressCity, showVehicleTypeId);
        map.put("vehicleTransactionList", pageInfo.getList());
        map.put("prePage", pageInfo.getPrePage() == 0 ? 1 : pageInfo.getPrePage());
        map.put("nextPage", pageInfo.getNextPage() == 0 ? pageInfo.getPages() : pageInfo.getNextPage());
        map.put("pages", pageInfo.getPages());
        //搜索条件回显
        map.put("showAddressCity",showAddressCity);
        map.put("showSaleCar",showSaleCar);
        map.put("showVehicleTypeId",showVehicleTypeId);
        for (VehicleTransaction vehicleTransaction : pageInfo.getList()) {
            logger.info(""+vehicleTransaction);
        }
        return new ResultData().success(map);
    }

    /**
     * 我的交易
     * @param map
     * @param pageNum
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/line-interaction/showWdVehicleTransaction.html")
    public ResultData showWdVehicleTransaction(Map<String,Object> map,
                                               @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
                                               HttpServletRequest request){

        Integer pn = PnUtil.getPn(pageNum);
        User user = (User)request.getSession().getAttribute("USER_TOKEN");
        PageInfo<VehicleTransaction> pageInfo = vehicleTransactionServiceImpl.getVehicleTransactionList(pn,user.getId(),null,null,null);
        map.put("vehicleTransactionList", pageInfo.getList());
        map.put("prePage", pageInfo.getPrePage() == 0 ? 1 : pageInfo.getPrePage());
        map.put("nextPage", pageInfo.getNextPage() == 0 ? pageInfo.getPages() : pageInfo.getNextPage());
        map.put("pages", pageInfo.getPages());
        for (VehicleTransaction vehicleTransaction : pageInfo.getList()) {
            logger.info("" + vehicleTransaction);
        }
        return new ResultData().success(map);

    }
    /**
     * 删除车辆信息
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/line-interaction/deleteVehicleTransaction.html")
    public ResultData deleteDrivingLicense(Integer id){

        try {
            int i = vehicleTransactionServiceImpl.deleteVehicleTransactionById(id);
            if (i==1){
                return new ResultData().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData().error("删除失败！未知异常！");
    }

    /**
     * 重发车辆信息
     * @param id
     * @return
     */
    @ResponseBody
    @PutMapping("/line-interaction/updateVehicleTransaction.html")
    public ResultData updateDrivingLicense(Integer id){

        try {
            int i = vehicleTransactionServiceImpl.updateVehicleTransactionById(1, id);
            if (i==1){
                return new ResultData().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData().error("重发失败，未知异常！");
    }

}
