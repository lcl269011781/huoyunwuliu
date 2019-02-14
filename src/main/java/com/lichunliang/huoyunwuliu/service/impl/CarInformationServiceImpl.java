package com.lichunliang.huoyunwuliu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.mapper.CarInformationMapper;
import com.lichunliang.huoyunwuliu.pojo.CarInformation;
import com.lichunliang.huoyunwuliu.service.CarInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
@Service
public class CarInformationServiceImpl implements CarInformationService {

    @Resource
    private CarInformationMapper carInformationMapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int addCarInformation(CarInformation carInformation) {
        return carInformationMapper.insertCarInformation(carInformation);
    }

    @Override
    public PageInfo<CarInformation> getCatInformationListByUserId(int pageNum, Integer userId, String departureCity, String reachingCity, Integer vehicleTypeId, Integer vehicleLengthId) {
        PageHelper.startPage(pageNum, 2);
        List<CarInformation> carInformations = carInformationMapper.selectCatInformationListByUserId(userId, departureCity, reachingCity, vehicleTypeId, vehicleLengthId);
        PageInfo<CarInformation> pageInfo = new PageInfo<>(carInformations);
        return pageInfo;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int deleteCarInformationByIdAndUserId(Integer id, Integer userId) {
        return carInformationMapper.deleteCarInformationByIdAndUserId(id, userId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int updateCarInformationByIdAndUserId(Integer status, Integer id, Integer userId, Date date) {
        return carInformationMapper.updateCarInformationByIdAndUserId(status, id, userId, date);
    }

}
