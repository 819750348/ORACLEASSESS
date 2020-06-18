package cn.jantd.modules.teacher.controller;

import cn.jantd.core.api.vo.Result;
import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.entity.TBusiStudyCourseTemp;
import cn.jantd.modules.teacher.entity.TBusiStudyTemp;
import cn.jantd.modules.teacher.service.ITBusiStudyCourseTempService;
import cn.jantd.modules.teacher.service.ITBusiStudyTempService;
import cn.jantd.modules.teacher.service.ITRelStaffCourseService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author qiji
 * @date 2020-03-03
 */
@RestController
@RequestMapping("/teacher/studyCourse")
@Api(tags = "学习和训练计划(课表配置)类")
@Slf4j
public class TBusiStudyCourseTempController {

    @Autowired
    private ITBusiStudyCourseTempService studyCourseTempService;
    @Autowired
    private ITRelStaffCourseService relStaffCourseService;


    /**
     * @param pageNo
     * @param pageSize
     * @return
     * @功能：学习和训练计划(课表配置)管理-分页查询列表
     */
    //@AutoLog(value = "学习和训练计划(课表配置)管理-分页查询列表")
    @ApiOperation(value = "学习和训练计划(课表配置)管理-分页查询列表")
    @GetMapping(value = "/queryPageList")
    public PersonnelResult queryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("pageNo", pageNo);
        pageParam.put("pageSize", pageSize);
        pageParam.put("teacherId","73");//session获取登录教学管理员id
        //前端传参条件查询，装备和岗位
        PersonnelResult personnelResult = studyCourseTempService.searchPage(pageParam);
        return personnelResult;
    }

    /**
     * 学习和训练计划(课表配置)管理-批量删除
     * @param ids
     * @return
     */
    //@AutoLog(value = "学习和训练计划(课表配置)管理-批量删除")
    @ApiOperation(value = "学习和训练计划(课表配置)管理-批量删除")
    @PostMapping(value = "/deleteBatch")
    public Result<Map<String,String>> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<Map<String,String>> result = new Result<>();
        if (StringUtils.isEmpty(ids)) {
            result.error500("参数不识别！");
        } else {
            try {
                Boolean flag = studyCourseTempService.removeIdsAndRel(ids);
                if(flag == true){
                    result.success("删除成功!");
                }else{
                    result.success("删除失败!");
                }
            } catch (Exception e) {
                result.success("操作失败!");
            }
        }
        return result;
    }

    /**
     * 学习和训练计划(课表配置)管理-编辑
     * @param studyCourseTemp
     * @return
     */
    //@AutoLog(value = "学习和训练计划(课表配置)管理-编辑")
    @ApiOperation(value = "学习和训练计划(课表配置)管理-编辑")
    @PostMapping(value = "/edit")
    public Result<TBusiStudyTemp> edit(@RequestParam(name = "studyCourseTemp", required = true) String studyCourseTemp) {
        Result<TBusiStudyTemp> result = new Result<>();
        try {
            TBusiStudyCourseTemp study = JSON.parseObject(studyCourseTemp, TBusiStudyCourseTemp.class);
            //创建教学管理员取session,测试先写死
            study.setTeacherId("73");
            boolean flag = false;
            flag = studyCourseTempService.saveStudy(study);
            result.setSuccess(flag);
            if(flag){
                result.setMessage("添加成功！");
            }else{
                result.success("添加失败！");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 学习和训练计划(课表配置)管理-推送
     * @return
     */
    //@AutoLog(value = "学习和训练计划(课表配置)管理-推送")
    @ApiOperation(value = "学习和训练计划(课表配置)管理-推送")
    @PostMapping(value = "/toStudents")
    public Result<Map<String,String>> toStudents() {
        Result<Map<String,String>> result = new Result<>();
        try {
            //因为是一次性全部推送出去，只需要后台查该教学管理员名下所有计划即可
            //session中取教学管理员id
            Boolean flag = studyCourseTempService.toStudents("73");
            result.setSuccess(flag);
            if(flag == true){
                result.setMessage("推送成功!");
            }else{
                result.setMessage("推送失败!");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("操作失败!");
        }
        return result;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     * @功能：学习和训练进度-分页查询列表
     */
    //@AutoLog(value = "学习和训练进度-分页查询列表")
    @ApiOperation(value = "学习和训练进度-分页查询列表")
    @GetMapping(value = "/rateQueryPageList")
    public PersonnelResult rateQueryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("pageNo", pageNo);
        pageParam.put("pageSize", pageSize);
        PersonnelResult personnelResult = relStaffCourseService.searchPage(pageParam);
        return personnelResult;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     * @功能：学习和训练进度-根据项目分页查询人员列表
     */
    //@AutoLog(value = "学习和训练进度-根据项目分页查询人员列表")
    @ApiOperation(value = "学习和训练进度-根据项目分页查询人员列表")
    @GetMapping(value = "/staffQueryPageList")
    public PersonnelResult staffQueryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("pageNo", pageNo);
        pageParam.put("pageSize", pageSize);
        //页面传参
        pageParam.put("studyId", req.getParameter("studyId")!=null?String.valueOf(req.getParameter("studyId")):"");
        PersonnelResult personnelResult = relStaffCourseService.searchStaffPageByStudy(pageParam);
        return personnelResult;
    }

}
