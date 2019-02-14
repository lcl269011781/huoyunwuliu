package com.lichunliang.huoyunwuliu.mapper;

import com.lichunliang.huoyunwuliu.pojo.SourceInformation;
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
public interface SourceInformationMapper {
    /**
     * 查询全部货源信息
     * @return
     */
    List<SourceInformation> selectSourceInformationList(@Param("departureCity")String departureCity,
                                                        @Param("reachingCity")String reachingCity,
                                                        @Param("vehicleTypeId")Integer vehicleTypeId,
                                                        @Param("vehicleLengthId")Integer vehicleLengthId);

    /**
     * 根据用户查询对应货源信息
     * @param userId
     * @return
     */
    List<SourceInformation> selectSourceInformationListByUserId(Integer userId);

    /**
     * 添加货源信息，对应用户id
     * @param sourceInformation
     * @return
     */
    int insertSourceInformation(SourceInformation sourceInformation);

    /**
     * 根据id和用户id删除我的货源信息
     * @param id
     * @return
     */
    int deleteSourceInformationByIdAndUserId(@Param("id") Integer id,@Param("userId") Integer userId);

    /**
     * 根据id和用户id更新我的货源信息
     * @param id
     * @param userId
     * @return
     */
    int updateSourceInformationByIdAndUserId(@Param("status")Integer status, @Param("id") Integer id, @Param("userId") Integer userId,
                                             @Param("date")Date date);
}
