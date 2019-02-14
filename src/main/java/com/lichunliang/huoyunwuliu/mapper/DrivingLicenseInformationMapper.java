package com.lichunliang.huoyunwuliu.mapper;

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
public interface DrivingLicenseInformationMapper {
    /**
     * 添加驾照信息
     * @param drivingLicenseInformation
     * @return
     */
    int insertDrivingLicenseInformation(DrivingLicenseInformation drivingLicenseInformation);

    /**
     * 查询全部驾照信息
     * @param userId
     * @param drivingAddressCity
     * @param drivingLicenseTypeId
     * @return
     */
    List<DrivingLicenseInformation> selectDrivingLicenseInformationList(@Param("userId") Integer userId,
                                                                        @Param("drivingAddressCity")String drivingAddressCity,
                                                                        @Param("drivingLicenseTypeId")Integer drivingLicenseTypeId);

    /**
     * 删除过期驾照信息
     * @param id
     * @return
     */
    int deleteDrivingLicenseInformationByid(Integer id);

    /**
     * 更新驾照信息状态
     * @param status
     * @param id
     * @return
     */
    int updateDrivingLicenseInformationById(@Param("status")Integer status,@Param("id")Integer id);


}
