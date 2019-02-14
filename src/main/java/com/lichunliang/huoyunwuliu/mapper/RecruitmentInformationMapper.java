package com.lichunliang.huoyunwuliu.mapper;

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
public interface RecruitmentInformationMapper {

    /**
     * 添加新的招聘信息
     * @param recruitmentInformation
     * @return
     */
    int insertRecruitmentInformation(RecruitmentInformation recruitmentInformation);

    /**
     * 查询全部的招聘信息+条件查询
     * @param userId
     * @param identityId
     * @param postId
     * @param addressCity
     * @return
     */
    List<RecruitmentInformation> selectRecruitmentInformationList(@Param("userId")Integer userId,
                                                                  @Param("identityId")Integer identityId,
                                                                  @Param("postId")Integer postId,
                                                                  @Param("addressCity")String addressCity);
    /**
     * 删除我的招聘
     * @param id
     * @return
     */
    int deleteRecruitmentInformationByid(Integer id);

    /**
     * 更新我的招聘
     * @param status
     * @param id
     * @return
     */
    int updateRecruitmentInformationById(@Param("status")Integer status,@Param("id")Integer id);

}
