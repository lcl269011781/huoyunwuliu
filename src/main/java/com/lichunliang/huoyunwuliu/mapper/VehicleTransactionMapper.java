package com.lichunliang.huoyunwuliu.mapper;

import com.lichunliang.huoyunwuliu.pojo.VehicleTransaction;
import org.apache.ibatis.annotations.Param;

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
public interface VehicleTransactionMapper {

    /**
     * 添加车辆交易信息
     * @param vehicleTransaction
     * @return
     */
    int insertVehicleTransaction(VehicleTransaction vehicleTransaction);

    /**
     * 查询车辆交易信息
     * @param userId
     * @param identityId
     * @param addressCity
     * @param vehicleTypeId
     * @return
     */
    List<VehicleTransaction> selectVehicleTransactionList(@Param("userId")Integer userId,
                                                          @Param("identityId")Integer identityId,
                                                          @Param("addressCity")String addressCity,
                                                          @Param("vehicleTypeId")Integer vehicleTypeId);

    /**
     * 根据id删除我的车辆交易信息
     * @param id
     * @return
     */
    int deleteVehicleTransactionById(Integer id);

    /**
     * 根据id更新我的车辆信息
     * @param id
     * @param status
     * @return
     */
    int updateVehicleTransactionById(@Param("id")Integer id ,@Param("status")Integer status);
}
