package com.lichunliang.huoyunwuliu.controller;

import com.github.pagehelper.PageInfo;
import com.lichunliang.huoyunwuliu.pojo.DrivingLicenseInformation;
import com.lichunliang.huoyunwuliu.pojo.DrivingLicenseScore;
import com.lichunliang.huoyunwuliu.pojo.DrivingLicenseType;
import com.lichunliang.huoyunwuliu.pojo.User;
import com.lichunliang.huoyunwuliu.service.DrivingLicenseInformationService;
import com.lichunliang.huoyunwuliu.utils.PnUtil;
import com.lichunliang.huoyunwuliu.utils.RedisUtil;
import com.lichunliang.huoyunwuliu.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class DrivingLicenseInformationController {

    @Autowired
    private DrivingLicenseInformationService drivingLicenseInformationServiceImpl;
    @Autowired
    private RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(DrivingLicenseInformationController.class);

    /**
     * 获取下拉框驾照分数，驾照类型
     *
     * @param map
     * @return
     */
    @ResponseBody
    @GetMapping("/getDrivingLicenseTwo.html")
    public ResultData getDrivingLicenseTwo(Map<String, Object> map) {
        redisUtil.getTwoDriving(map);
        logger.info("" + map);
        return new ResultData().success(map);
    }

    /**
     * 添加驾照信息
     *
     * @param drivingLicenseInformation
     * @param drivingLicenseTypeId
     * @param drivingLicenseScoreId
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/line-interaction/add.html")
    public ResultData addDrivingLicense(DrivingLicenseInformation drivingLicenseInformation,
                                        Integer drivingLicenseTypeId,
                                        Integer drivingLicenseScoreId, HttpServletRequest request, Map<String, Object> map
    ) {
        redisUtil.getTwoDriving(map);
        User user_token = (User) request.getSession().getAttribute("USER_TOKEN");
        //添加数据
        drivingLicenseInformation.setDrivingLicenseType(new DrivingLicenseType(drivingLicenseTypeId, null));
        drivingLicenseInformation.setDrivingLicenseScore(new DrivingLicenseScore(drivingLicenseScoreId, null));
        drivingLicenseInformation.setUser(user_token);
        drivingLicenseInformation.setStatus(1);
        try {
            int i = drivingLicenseInformationServiceImpl.addDrivingLicenseInformation(drivingLicenseInformation);
            if (i == 1) {
                return new ResultData().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData().error("添加失败，未知错误！");
        }
        return new ResultData().error("添加失败，请填写完整信息！");
    }

    /**
     * 显示全部驾照信息+条件查询
     *
     * @param map
     * @param pageNum
     * @param drivingDrivingAddressCity
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/line-interaction/showDrivingLicense.html")
    public ResultData showDrivingLicense(Map<String, Object> map,
                                         @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
                                         @RequestParam(name = "drivingDrivingAddressCity", defaultValue = "") String drivingDrivingAddressCity,
                                         @RequestParam(name = "showDrivingLicenseTypeId", defaultValue = "0") Integer showDrivingLicenseTypeId) {
        redisUtil.getTwoDriving(map);
        Integer pn = PnUtil.getPn(pageNum);
        PageInfo<DrivingLicenseInformation> pageInfo = drivingLicenseInformationServiceImpl.getDrivingLicenseInformationList(pn, null, drivingDrivingAddressCity,
                showDrivingLicenseTypeId);
        map.put("drivingLicenseInformationList", pageInfo.getList());
        map.put("prePage", pageInfo.getPrePage() == 0 ? 1 : pageInfo.getPrePage());
        map.put("nextPage", pageInfo.getNextPage() == 0 ? pageInfo.getPages() : pageInfo.getNextPage());
        map.put("pages", pageInfo.getPages());
        //搜索条件回显
        map.put("drivingDrivingAddressCity", drivingDrivingAddressCity);
        map.put("showDrivingLicenseTypeId", showDrivingLicenseTypeId);
        for (DrivingLicenseInformation drivingLicenseInformation : pageInfo.getList()) {
            logger.info("" + drivingLicenseInformation);
        }
        return new ResultData().success(map);
    }

    /**
     * 我的分数
     * @param map
     * @param pageNum
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping("/line-interaction/showWdDrivingLicense.html")
    public ResultData showWdDrivingLicense(Map<String,Object> map,
                                           @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
                                           HttpServletRequest request){
        Integer pn = PnUtil.getPn(pageNum);
        User user = (User)request.getSession().getAttribute("USER_TOKEN");
        PageInfo<DrivingLicenseInformation> pageInfo = drivingLicenseInformationServiceImpl.getDrivingLicenseInformationList(pn, user.getId(), null, null);
        map.put("drivingLicenseInformationList", pageInfo.getList());
        map.put("prePage", pageInfo.getPrePage() == 0 ? 1 : pageInfo.getPrePage());
        map.put("nextPage", pageInfo.getNextPage() == 0 ? pageInfo.getPages() : pageInfo.getNextPage());
        map.put("pages", pageInfo.getPages());
        for (DrivingLicenseInformation drivingLicenseInformation : pageInfo.getList()) {
            logger.info("" + drivingLicenseInformation);
        }
        return new ResultData().success(map);
    }

    /**
     * 删除驾照信息
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/line-interaction/deleteDrivingLicense.html")
    public ResultData deleteDrivingLicense(Integer id){

        try {
            int i = drivingLicenseInformationServiceImpl.deleteDrivingLicenseInformationByid(id);
            if (i==1){
                return new ResultData().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData().error("删除失败！未知异常！");
    }

    /**
     * 重发驾照信息
     * @param id
     * @return
     */
    @ResponseBody
    @PutMapping("/line-interaction/updateDrivingLicense.html")
    public ResultData updateDrivingLicense(Integer id){

        try {
            int i = drivingLicenseInformationServiceImpl.updateDrivingLicenseInformationById(1, id);
            if (i==1){
                return new ResultData().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultData().error("重发失败，未知异常！");
    }


}
