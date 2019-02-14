package com.lichunliang.huoyunwuliu.utils;

import com.lichunliang.huoyunwuliu.pojo.*;
import com.lichunliang.huoyunwuliu.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
@Component
public class RedisUtil {

    @Autowired
    private VehicleVolumeService vehicleVolumeServiceImpl;
    @Autowired
    private VehicleTypeService vehicleTypeServiceImpl;
    @Autowired
    private VehicleLengthService vehicleLengthServiceImpl;
    @Autowired
    private DrivingLicenseTypeService drivingLicenseTypeServiceImpl;
    @Autowired
    private DrivingLicenseScoreService drivingLicenseScoreServiceImpl;
    @Autowired
    private AgeRangeService ageRangeServiceImpl;
    @Autowired
    private SalaryRangeService salaryRangeServiceImpl;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 年龄范围和薪水范围存入缓存
     * @param map
     */
    public void getAgeAndSalary(Map<String,Object> map){
        List<AgeRange> ageRangeList = null;
        List<SalaryRange> salaryRangeList = null;
        //固定不变的查询结果存入redis，因为反复用且查询结果不变。
        ageRangeList = (List<AgeRange>)redisTemplate.opsForValue().get("huoyunwuliu:ageRangeList");
        if (ageRangeList==null || ageRangeList.size()==0){
            ageRangeList = ageRangeServiceImpl.getAgeRangeList();
            redisTemplate.opsForValue().set("huoyunwuliu:ageRangeList",ageRangeList);
        }
        salaryRangeList = (List<SalaryRange>)redisTemplate.opsForValue().get("huoyunwuliu:salaryRangeList");
        if (salaryRangeList==null || salaryRangeList.size()==0){
            salaryRangeList = salaryRangeServiceImpl.getSalaryRangeList();
            redisTemplate.opsForValue().set("huoyunwuliu:salaryRangeList",salaryRangeList);
        }
        map.put("ageRangeList",ageRangeList);
        map.put("salaryRangeList",salaryRangeList);
    }

    /**
     * 驾照类型和驾照分数存入redis
     *
     * @param map
     */
    public void getTwoDriving(Map<String, Object> map) {
        List<DrivingLicenseScore> drivingLicenseScoreList = null;
        List<DrivingLicenseType> drivingLicenseTypeList = null;
        //固定不变的查询结果存入redis，因为反复用且查询结果不变。
        drivingLicenseScoreList = (List<DrivingLicenseScore>) redisTemplate.opsForValue().get("huoyunwuliu:drivingLicenseScoreList");
        if (drivingLicenseScoreList == null || drivingLicenseScoreList.size() == 0) {
            drivingLicenseScoreList = drivingLicenseScoreServiceImpl.getDrivingLicenseScoreList();
            redisTemplate.opsForValue().set("huoyunwuliu:drivingLicenseScoreList", drivingLicenseScoreList);
        }
        drivingLicenseTypeList = (List<DrivingLicenseType>) redisTemplate.opsForValue().get("huoyunwuliu:drivingLicenseTypeList");
        if (drivingLicenseTypeList == null || drivingLicenseTypeList.size() == 0) {
            drivingLicenseTypeList = drivingLicenseTypeServiceImpl.getDrivingLicenseTypeList();
            redisTemplate.opsForValue().set("huoyunwuliu:drivingLicenseTypeList", drivingLicenseTypeList);
        }
        map.put("drivingLicenseScoreList", drivingLicenseScoreList);
        map.put("drivingLicenseTypeList", drivingLicenseTypeList);
    }

    /**
     * 车辆数量，车辆类型，车辆长度存入redis
     *
     * @param map
     */
    public void getThreeV(Map<String, Object> map) {
        List<VehicleVolume> vehicleVolumeList = null;
        List<VehicleLength> vehicleLengthList = null;
        List<VehicleType> vehicleTypeList = null;
        //固定不变的查询结果存入redis，因为反复用且查询结果不变。
        vehicleVolumeList = (List<VehicleVolume>) redisTemplate.opsForValue().get("huoyunwuliu:vehicleVolumeList");
        if (vehicleVolumeList == null || vehicleVolumeList.size() == 0) {
            vehicleVolumeList = vehicleVolumeServiceImpl.getVehicleVolumeList();
            redisTemplate.opsForValue().set("huoyunwuliu:vehicleVolumeList", vehicleVolumeList);
        }
        vehicleLengthList = (List<VehicleLength>) redisTemplate.opsForValue().get("huoyunwuliu:vehicleLengthList");
        if (vehicleLengthList == null || vehicleLengthList.size() == 0) {
            vehicleLengthList = vehicleLengthServiceImpl.getVehicleLengthList();
            redisTemplate.opsForValue().set("huoyunwuliu:vehicleLengthList", vehicleLengthList);
        }
        vehicleTypeList = (List<VehicleType>) redisTemplate.opsForValue().get("huoyunwuliu:vehicleTypeList");
        if (vehicleTypeList == null || vehicleTypeList.size() == 0) {
            vehicleTypeList = vehicleTypeServiceImpl.getVehicleTypeList();
            redisTemplate.opsForValue().set("huoyunwuliu:vehicleTypeList", vehicleTypeList);
        }
        map.put("vehicleVolumeList", vehicleVolumeList);
        map.put("vehicleLengthList", vehicleLengthList);
        map.put("vehicleTypeList", vehicleTypeList);
    }


}
