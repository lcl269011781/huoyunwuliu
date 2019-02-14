package com.lichunliang.huoyunwuliu.mapper;

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
public interface CarInformationMapper {

    /**
     * 添加车源信息
     * @param carInformation
     * @return
     */
    int insertCarInformation(CarInformation carInformation);

    /**
     * 根据用户id查询车源信息
     * @param userId
     * @return
     */
    List<CarInformation> selectCatInformationListByUserId(Integer userId, @Param("departureCity") String departureCity,
                                                          @Param("reachingCity") String reachingCity,
                                                          @Param("vehicleTypeId") Integer vehicleTypeId,
                                                          @Param("vehicleLengthId") Integer vehicleLengthId);
    /**
     * 根据id和用户id删除我的车源信息
     * @param id
     * @return
     */
    int deleteCarInformationByIdAndUserId(@Param("id") Integer id,@Param("userId") Integer userId);

    /**
     * 根据id和用户id更新我的车源信息
     * @param id
     * @param userId
     * @return
     */
    int updateCarInformationByIdAndUserId(@Param("status")Integer status, @Param("id") Integer id, @Param("userId") Integer userId,
                                             @Param("date") Date date);


}
