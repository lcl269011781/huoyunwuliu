package com.lichunliang.huoyunwuliu.service;

import com.github.pagehelper.PageInfo;
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
public interface VehicleTransactionService {

    /**
     * 添加车辆交易信息
     *
     * @param vehicleTransaction
     * @return
     */
    int addVehicleTransaction(VehicleTransaction vehicleTransaction) throws Exception;

    /**
     * 查询车辆交易信息
     *
     * @param userId
     * @param identityId
     * @param addressCity
     * @param vehicleTypeId
     * @return
     */
    PageInfo<VehicleTransaction> getVehicleTransactionList(int pageNum,Integer userId,
                                                           Integer identityId,
                                                           String addressCity,
                                                           Integer vehicleTypeId);
    /**
     * 根据id删除我的车辆交易信息
     * @param id
     * @return
     */
    int deleteVehicleTransactionById(Integer id) throws Exception;

    /**
     * 根据id更新我的车辆信息
     * @param id
     * @param status
     * @return
     */
    int updateVehicleTransactionById(Integer id ,Integer status)throws Exception;
}
