package cn.jantd.modules.teacher.controller;

import cn.jantd.core.api.vo.Result;
import cn.jantd.modules.teacher.entity.TBusiStudyTemp;
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
@RequestMapping("/teacher/study")
@Api(tags = "学习和训练项目类")
@Slf4j
public class TBusiStudyTempController {

    @Autowired
    private ITBusiStudyTempService studyTempService;
    @Autowired
    private ITRelStaffCourseService staffCourseService;

    /**
     * @param pageNo
     * @param pageSize
     * @return
     * @功能：学习和训练项目管理-分页查询列表
     */
    //@AutoLog(value = "学习和训练项目管理-分页查询列表")
    @ApiOperation(value = "学习和训练项目管理-分页查询列表")
    @GetMapping(value = "/queryPageList")
    public Result<Map<String,Object>> queryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("pageNo", pageNo);
        pageParam.put("pageSize", pageSize);
        //前端传参条件查询，装备和岗位
        pageParam.put("equip", req.getParameter("equip")!=null?String.valueOf(req.getParameter("equip")):"");
        pageParam.put("position", req.getParameter("position")!=null?String.valueOf(req.getParameter("position")):"");
        Map<String,Object> resultMap = studyTempService.searchPage(pageParam);
        result.setSuccess(true);
        result.setResult(resultMap);
        return result;
    }

    /**
     * @return
     * @功能：学习和训练项目下拉列表
     */
    //@AutoLog(value = "学习和训练项目下拉列表")
    @ApiOperation(value = "学习和训练项目下拉列表")
    @GetMapping(value = "/getStudySelect")
    public Result<Map<String,Object>> getStudySelect(HttpServletRequest req) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("teacherId","73"); //session中取当前登录教学管理员
        Map<String,Object> resultMap = studyTempService.getStudySelect(pageParam);
        if(resultMap != null){
            result.setSuccess(true);
            result.setResult(resultMap);
            return result;
        }else{
            result.setSuccess(false);
            return result;
        }

    }

    /**
     * 学习和训练项目管理-批量删除
     * @param ids
     * @return
     */
    //@AutoLog(value = "学习和训练项目管理-批量删除")
    @ApiOperation(value = "学习和训练项目管理-批量删除")
    @GetMapping(value = "/deleteBatch")
    public Result<Map<String,String>> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<Map<String,String>> result = new Result<>();
        if (StringUtils.isEmpty(ids)) {
            result.error500("参数不识别！");
        } else {
            try {
                Boolean flag = studyTempService.removeByIds(Arrays.asList(ids.split(",")));
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
     * 学习和训练项目管理-编辑
     * @param jsonObject
     * @return
     */
    //@AutoLog(value = "学习和训练项目管理-编辑")
    @ApiOperation(value = "学习和训练项目管理-编辑")
    @PostMapping(value = "/edit")
    public Result<TBusiStudyTemp> edit(@RequestBody JSONObject jsonObject) {
        Result<TBusiStudyTemp> result = new Result<>();
        try {
            TBusiStudyTemp study = JSON.parseObject(jsonObject.toJSONString(), TBusiStudyTemp.class);
            // 设置创建时间
            study.setCreateTime(new Date());
            study.setCreateBy("73");
            study.setDelFlag("0");
            boolean flag = false;
            if(study.getId() != null){
                study.setUpdateBy("73");
                study.setUpdateTime(new Date());
                flag = studyTempService.updateById(study);
            }else{
                flag = studyTempService.save(study);
            }
            if(flag){
                result.success("添加成功！");
            }else{
                result.success("添加失败！");
            }
        } catch (Exception e) {
            //log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }


    /**
     * @param pageNo
     * @param pageSize
     * @return
     * @功能：学习和训练项目列表-分页查询列表,学员端接口(去掉分页)
     */
    //@AutoLog(value = "学习和训练项目列表-分页查询列表,学员端接口(去掉分页)")
    @ApiOperation(value = "学习和训练项目列表-分页查询列表,学员端接口(去掉分页)")
    @GetMapping(value = "/queryStudyListByStudent")
    public Result<Map<String,Object>> queryStudyListByStudent(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    @RequestParam(name = "studentId") String studentId, HttpServletRequest req) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        //pageParam.put("pageNo", pageNo);
        //pageParam.put("pageSize", pageSize);
        pageParam.put("studentId", studentId);
        Map<String,Object> resultMap = staffCourseService.searchPageByStudent(pageParam);
        result.setSuccess(true);
        result.setResult(resultMap);
        return result;
    }

}
