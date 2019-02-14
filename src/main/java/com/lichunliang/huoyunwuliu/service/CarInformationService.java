package com.lichunliang.huoyunwuliu.service;

import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.pojo.CarInformation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

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
public interface CarInformationService {

    /**
     * 添加车源信息
     * @param carInformation
     * @return
     */
    int addCarInformation(CarInformation carInformation) throws Exception;

    /**
     * 根据用户id查询车源信息
     * @param userId
     * @return
     */
    PageInfo<CarInformation> getCatInformationListByUserId(int pageNum,Integer userId,String departureCity,String reachingCity,Integer vehicleTypeId,Integer vehicleLengthId);
    /**
     * 根据id和用户id删除我的车源信息
     * @param id
     * @return
     */
    int deleteCarInformationByIdAndUserId( Integer id,Integer userId) throws Exception;

    /**
     * 根据id和用户id更新我的车源信息
     * @param id
     * @param userId
     * @return
     */
    int updateCarInformationByIdAndUserId(Integer status,Integer id, Integer userId,
                                          Date date) throws Exception;


}
