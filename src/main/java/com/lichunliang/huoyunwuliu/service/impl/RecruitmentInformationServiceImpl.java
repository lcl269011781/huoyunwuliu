package com.lichunliang.huoyunwuliu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.mapper.RecruitmentInformationMapper;
import com.lichunliang.huoyunwuliu.pojo.RecruitmentInformation;
import com.lichunliang.huoyunwuliu.service.RecruitmentInformationService;
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
public class RecruitmentInformationServiceImpl implements RecruitmentInformationService {

    @Resource
    private RecruitmentInformationMapper recruitmentInformationMapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int addRecruitmentInformation(RecruitmentInformation recruitmentInformation) {
        return recruitmentInformationMapper.insertRecruitmentInformation(recruitmentInformation);
    }

    @Override
    public PageInfo<RecruitmentInformation> getRecruitmentInformationList(int pageNum, Integer userId, Integer identityId, Integer postId, String addressCity) {
        PageHelper.startPage(pageNum, 2);
        List<RecruitmentInformation> recruitmentInformations = recruitmentInformationMapper.selectRecruitmentInformationList(userId, identityId, postId, addressCity);
        PageInfo<RecruitmentInformation> pageInfo = new PageInfo<>(recruitmentInformations);
        return pageInfo;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int deleteRecruitmentInformationByid(Integer id) throws Exception {
        return recruitmentInformationMapper.deleteRecruitmentInformationByid(id);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public int updateRecruitmentInformationById(Integer status, Integer id) throws Exception {
        return recruitmentInformationMapper.updateRecruitmentInformationById(status, id);
    }
}
