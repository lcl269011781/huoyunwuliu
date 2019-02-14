package com.lichunliang.huoyunwuliu.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
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
public interface SourceInformationService {

    /**
     * 查询全部货源信息
     *
     * @return
     */
    PageInfo<SourceInformation> getSourceInformationList(int pageNum, String departureCity,
                                                         String reachingCity,
                                                         Integer vehicleTypeId,
                                                         Integer vehicleLengthId);

    /**
     * 根据用户查询对应货源信息
     *
     * @param userId
     * @return
     */
    PageInfo<SourceInformation> getSourceInformationListByUserId(Integer userId, int pageNum);

    /**
     * 添加货源信息，对应用户id
     *
     * @param sourceInformation
     * @return
     */
    int addSourceInformation(SourceInformation sourceInformation) throws Exception;

    /**
     * 根据id和用户id删除
     *
     * @param id
     * @param userId
     * @return
     * @throws Exception
     */
    int delSourceInformationByIdAndUserId(Integer id, Integer userId) throws Exception;

    /**
     * 根据id和用户id更新我的货源信息
     *
     * @param id
     * @param userId
     * @return
     */
    int updateSourceInformationByIdAndUserId(Integer status, Integer id, Integer userId, Date date) throws Exception;

}
