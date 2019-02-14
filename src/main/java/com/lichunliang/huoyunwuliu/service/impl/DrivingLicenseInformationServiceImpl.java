package com.lichunliang.huoyunwuliu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.mapper.DrivingLicenseInformationMapper;
import com.lichunliang.huoyunwuliu.pojo.DrivingLicenseInformation;
import com.lichunliang.huoyunwuliu.service.DrivingLicenseInformationService;
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
public class DrivingLicenseInformationServiceImpl implements DrivingLicenseInformationService {

    @Resource
    private DrivingLicenseInformationMapper drivingLicenseInformationMapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int addDrivingLicenseInformation(DrivingLicenseInformation drivingLicenseInformation) {
        return drivingLicenseInformationMapper.insertDrivingLicenseInformation(drivingLicenseInformation);
    }

    @Override
    public PageInfo<DrivingLicenseInformation> getDrivingLicenseInformationList(int pageNum, Integer userId, String drivingAddressCity, Integer drivingLicenseTypeId) {

        PageHelper.startPage(pageNum, 2);
        List<DrivingLicenseInformation> drivingLicenseInformations = drivingLicenseInformationMapper.selectDrivingLicenseInformationList(userId, drivingAddressCity, drivingLicenseTypeId);
        PageInfo<DrivingLicenseInformation> pageInfo = new PageInfo<>(drivingLicenseInformations);
        return pageInfo;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int deleteDrivingLicenseInformationByid(Integer id) {
        return drivingLicenseInformationMapper.deleteDrivingLicenseInformationByid(id);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int updateDrivingLicenseInformationById(Integer status, Integer id) {
        return drivingLicenseInformationMapper.updateDrivingLicenseInformationById(status, id);
    }

}
