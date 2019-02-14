package com.lichunliang.huoyunwuliu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.mapper.SourceInformationMapper;
import com.lichunliang.huoyunwuliu.pojo.SourceInformation;
import com.lichunliang.huoyunwuliu.service.SourceInformationService;
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
public class SourceInformationServiceImpl implements SourceInformationService {
    @Resource
    private SourceInformationMapper sourceInformationMapper;

    @Override
    public PageInfo<SourceInformation> getSourceInformationList(int pageNum,
                                                                String departureCity,
                                                                String reachingCity,
                                                                Integer vehicleTypeId,
                                                                Integer vehicleLengthId) {
        PageHelper.startPage(pageNum, 2);
        if (departureCity == null || departureCity.equals("")) {
        }
        if (reachingCity == null || reachingCity.equals("")) {
        }
        if (vehicleTypeId == null || vehicleTypeId.equals("")) {
        }
        if (vehicleLengthId == null || vehicleLengthId.equals("")) {
        }
        List<SourceInformation> sourceInformations =
                sourceInformationMapper.selectSourceInformationList(departureCity, reachingCity, vehicleTypeId, vehicleLengthId);
        PageInfo<SourceInformation> pageInfo = new PageInfo<>(sourceInformations);
        return pageInfo;
    }

    @Override
    public PageInfo<SourceInformation> getSourceInformationListByUserId(Integer userId, int pageNum) {
        PageHelper.startPage(pageNum, 2);
        List<SourceInformation> sourceInformations = sourceInformationMapper.selectSourceInformationListByUserId(userId);
        PageInfo<SourceInformation> pageInfo = new PageInfo<>(sourceInformations);
        return pageInfo;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int addSourceInformation(SourceInformation sourceInformation) throws Exception {
        return sourceInformationMapper.insertSourceInformation(sourceInformation);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int delSourceInformationByIdAndUserId(Integer id, Integer userId) throws Exception {
        return sourceInformationMapper.deleteSourceInformationByIdAndUserId(id, userId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int updateSourceInformationByIdAndUserId(Integer status, Integer id, Integer userId, Date date) {
        return sourceInformationMapper.updateSourceInformationByIdAndUserId(status, id, userId, date);
    }
}
