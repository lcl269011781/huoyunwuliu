package com.lichunliang.huoyunwuliu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.mapper.VehicleTransactionMapper;
import com.lichunliang.huoyunwuliu.pojo.VehicleTransaction;
import com.lichunliang.huoyunwuliu.service.VehicleTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
@Service
public class VehicleTransactionServiceImpl implements VehicleTransactionService {

    @Resource
    private VehicleTransactionMapper vehicleTransactionMapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int addVehicleTransaction(VehicleTransaction vehicleTransaction) throws Exception {
        return vehicleTransactionMapper.insertVehicleTransaction(vehicleTransaction);
    }

    @Override
    public PageInfo<VehicleTransaction> getVehicleTransactionList(int pageNum, Integer userId, Integer identityId, String addressCity, Integer vehicleTypeId) {
        PageHelper.startPage(pageNum, 2);
        List<VehicleTransaction> vehicleTransactions = vehicleTransactionMapper.selectVehicleTransactionList(userId, identityId, addressCity, vehicleTypeId);
        PageInfo<VehicleTransaction> pageInfo = new PageInfo<>(vehicleTransactions);
        return pageInfo;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int deleteVehicleTransactionById(Integer id) throws Exception {
        return vehicleTransactionMapper.deleteVehicleTransactionById(id);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int updateVehicleTransactionById(Integer id, Integer status) throws Exception {
        return vehicleTransactionMapper.updateVehicleTransactionById(id, status);
    }
}
