package com.lichunliang.huoyunwuliu.controller;

import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.pojo.*;
import com.lichunliang.huoyunwuliu.service.RecruitmentInformationService;
import com.lichunliang.huoyunwuliu.utils.PnUtil;
import com.lichunliang.huoyunwuliu.utils.RedisUtil;
import com.lichunliang.huoyunwuliu.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.NamingEnumeration;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
@Controller
public class RecruitmentInformationController {

    @Autowired
    private RecruitmentInformationService recruitmentInformationServiceImpl;
    @Autowired
    private RedisUtil redisUtil;
    private static final Logger logger = LoggerFactory.getLogger(RecruitmentInformationController.class);

    /**
     * 获取年龄范围和薪水范围
     *
     * @param map
     * @return
     */
    @ResponseBody
    @GetMapping("/getAgeAndSalary.html")
    public ResultData getAgeAndSalary(Map<String, Object> map) {
        redisUtil.getAgeAndSalary(map);
        return new ResultData().success(map);
    }

    /**
     * 添加招聘信息
     *
     * @param request
     * @param addIdentityId
     * @param addPostId
     * @param addCorporateName
     * @param addAddressCity
     * @param addSalaryId
     * @param addAgeId
     * @param addJobRequirements
     * @param addPhone
     * @param map
     * @return
     */
    @ResponseBody
    @PostMapping("/line-interaction/addRecruitmentInformation.html")
    public ResultData showRecruitmentInformation(HttpServletRequest request,
                                                 Integer addIdentityId,
                                                 Integer addPostId,
                                                 String addCorporateName,
                                                 String addAddressCity,
                                                 Integer addSalaryId,
                                                 Integer addAgeId,
                                                 String addJobRequirements,
                                                 String addPhone, Map<String, Object> map
    ) {
        redisUtil.getAgeAndSalary(map);
        if (addIdentityId == null || addPostId == null || addCorporateName == null || addAddressCity == null ||
                addSalaryId == null || addAgeId == null || addJobRequirements == null || addPhone == null ||
                addCorporateName.trim().equals("") || addAddressCity.trim().equals("") || addJobRequirements.trim().equals("")) {
            return new ResultData().error("添加失败，请确保信息完整!");
        }
        User user = (User) request.getSession().getAttribute("USER_TOKEN");
        //封装
        RecruitmentInformation recruitmentInformation = new RecruitmentInformation();
        recruitmentInformation.setUser(user);
        recruitmentInformation.setIdentityId(addIdentityId);
        recruitmentInformation.setPostId(addPostId);
        recruitmentInformation.setCorporateName(addCorporateName);
        recruitmentInformation.setAddressCity(addAddressCity);
        recruitmentInformation.setSalaryRange(new SalaryRange(addSalaryId, null));
        recruitmentInformation.setAgeRange(new AgeRange(addAgeId, null));
        recruitmentInformation.setJobRequirements(addJobRequirements);
        recruitmentInformation.setPhone(addPhone);
        recruitmentInformation.setStatus(1);
        try {
            int i = recruitmentInformationServiceImpl.addRecruitmentInformation(recruitmentInformation);
            if (i == 1) {
                return new ResultData().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData().error("添加失败！未知错误！");
    }

    /**
     * 查询招聘信息+条件查询
     * @param map
     * @param request
     * @param pageNum
     * @param identityId
     * @param postId
     * @param addressCity
     * @return
     */
    @ResponseBody
    @GetMapping("/line-interaction/showRecruitmentInformation.html")
    public ResultData showRecruitmentInformation(Map<String, Object> map,
                                                 HttpServletRequest request,
                                                 @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
                                                 @RequestParam(name = "identityId", defaultValue = "0") Integer identityId,
                                                 @RequestParam(name = "postId", defaultValue = "0") Integer postId,
                                                 @RequestParam(name = "addressCity", defaultValue = "") String addressCity) {

        Integer pn = PnUtil.getPn(pageNum);
        PageInfo<RecruitmentInformation> pageInfo = recruitmentInformationServiceImpl.getRecruitmentInformationList(pn,null, identityId, postId, addressCity);
        map.put("recruitmentInformationList", pageInfo.getList());
        map.put("prePage", pageInfo.getPrePage() == 0 ? 1 : pageInfo.getPrePage());
        map.put("nextPage", pageInfo.getNextPage() == 0 ? pageInfo.getPages() : pageInfo.getNextPage());
        map.put("pages", pageInfo.getPages());
        map.put("identityId", identityId);
        map.put("postId", postId);
        map.put("addressCity", addressCity);
        redisUtil.getAgeAndSalary(map);
        for (RecruitmentInformation recruitmentInformation : pageInfo.getList()) {
            logger.info("" + recruitmentInformation);
        }
        return new ResultData().success(map);
    }

    /**
     * 我的招聘
     * @param map
     * @param pageNum
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/line-interaction/showWdRecruitmentInformation.html")
    public ResultData showWdRecruitmentInformation(Map<String,Object> map,
                                                   @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
                                                   HttpServletRequest request){
        Integer pn = PnUtil.getPn(pageNum);
        User user = (User)request.getSession().getAttribute("USER_TOKEN");
        PageInfo<RecruitmentInformation> pageInfo = recruitmentInformationServiceImpl.getRecruitmentInformationList(pn,user.getId(),null,null,null);
        map.put("recruitmentInformationList", pageInfo.getList());
        map.put("prePage", pageInfo.getPrePage() == 0 ? 1 : pageInfo.getPrePage());
        map.put("nextPage", pageInfo.getNextPage() == 0 ? pageInfo.getPages() : pageInfo.getNextPage());
        map.put("pages", pageInfo.getPages());
        for (RecruitmentInformation recruitmentInformation : pageInfo.getList()) {
            logger.info("" + recruitmentInformation);
        }
        return new ResultData().success(map);

    }
    /**
     * 删除我的招聘
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/line-interaction/deleteRecruitmentInformation.html")
    public ResultData deleteRecruitmentInformation(Integer id){

        try {
            int i = recruitmentInformationServiceImpl.deleteRecruitmentInformationByid(id);
            if (i==1){
                return new ResultData().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData().error("删除失败！未知异常！");
    }

    /**
     * 重发我的招聘
     * @param id
     * @return
     */
    @ResponseBody
    @PutMapping("/line-interaction/updateRecruitmentInformation.html")
    public ResultData updateRecruitmentInformation(Integer id){

        try {
            int i = recruitmentInformationServiceImpl.updateRecruitmentInformationById(1, id);
            if (i==1){
                return new ResultData().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData().error("重发失败，未知异常！");
    }
}
