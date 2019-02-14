package com.lichunliang.huoyunwuliu.service;

import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.pojo.DrivingLicenseInformation;
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
public interface DrivingLicenseInformationService {

    /**
     * 添加驾照信息
     *
     * @param drivingLicenseInformation
     * @return
     */
    int addDrivingLicenseInformation(DrivingLicenseInformation drivingLicenseInformation) throws Exception;

    /**
     * 查询全部驾照信息
     *
     * @param userId
     * @param drivingAddressCity
     * @param drivingLicenseTypeId
     * @return
     */
    PageInfo<DrivingLicenseInformation> getDrivingLicenseInformationList(int pageNum, Integer userId,
                                                                         String drivingAddressCity,
                                                                         Integer drivingLicenseTypeId);

    /**
     * 删除过期驾照信息
     *
     * @param id
     * @return
     */
    int deleteDrivingLicenseInformationByid(Integer id) throws Exception;

    /**
     * 更新驾照信息状态
     *
     * @param status
     * @param id
     * @return
     */
    int updateDrivingLicenseInformationById(Integer status, Integer id) throws Exception;

}
