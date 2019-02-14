package com.lichunliang.huoyunwuliu.service;

import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.pojo.RecruitmentInformation;
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
public interface RecruitmentInformationService {
    /**
     * 添加新的招聘信息
     *
     * @param recruitmentInformation
     * @return
     */
    int addRecruitmentInformation(RecruitmentInformation recruitmentInformation) throws Exception;

    /**
     * 全部的招聘信息+条件查询
     *
     * @param userId
     * @param identityId
     * @param postId
     * @param addressCity
     * @return
     */
    PageInfo<RecruitmentInformation> getRecruitmentInformationList(int pageNum,Integer userId,
                                                                   Integer identityId,
                                                                   Integer postId,
                                                                   String addressCity);

    /**
     * 删除我的招聘
     * @param id
     * @return
     */
    int deleteRecruitmentInformationByid(Integer id) throws Exception;

    /**
     * 更新我的招聘
     * @param status
     * @param id
     * @return
     */
    int updateRecruitmentInformationById(Integer status,Integer id) throws Exception;
}
