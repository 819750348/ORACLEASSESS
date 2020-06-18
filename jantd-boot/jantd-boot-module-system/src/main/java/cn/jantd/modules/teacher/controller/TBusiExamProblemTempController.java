package cn.jantd.modules.teacher.controller;

import cn.jantd.core.annotation.AutoLog;
import cn.jantd.core.api.vo.Result;
import cn.jantd.core.util.PasswordUtil;
import cn.jantd.core.util.oConvertUtils;
import cn.jantd.modules.teacher.VO.TBusiExamProblemVO;
import cn.jantd.modules.teacher.entity.TBusiExamProblemTemp;
import cn.jantd.modules.teacher.service.ITBusiExamProblemTempService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * @Author qiji
 * @date 2020-03-03
 */
@RestController
@RequestMapping("/teacher/problem")
@Api(tags = "题库管理")
@Slf4j
public class TBusiExamProblemTempController {

    @Autowired
    private ITBusiExamProblemTempService problemService;


    /**
     * @param pageNo
     * @param pageSize
     * @return
     * @功能：题库管理-分页查询题目列表
     */
    //@AutoLog(value = "题库管理-分页查询题目列表")
    @ApiOperation(value = "题库管理-分页查询题目列表")
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
        Map<String,Object> resultMap = problemService.searchProblemPage(pageParam);
        result.setSuccess(true);
        result.setResult(resultMap);
        return result;
    }

    /**
     * 通过excel导入数据
     * @param request
     * @param response
     * @return
     */
    //@AutoLog(value = "题库管理-通过excel导入数据")
    @ApiOperation(value = "题库管理-通过excel导入数据")
    @PostMapping(value = "/importExcel")
    public Result<Object> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            try {
                //必要传值staffType,用来区分教师学员
                Map<String,String> returnMap = problemService.importExcelToProblem(file.getInputStream());
                if("0".equals(returnMap.get("code"))){
                    return Result.ok("文件导入成功！数据行数：" + returnMap.get("num"));
                }else {
                    return Result.error("文件导入失败.失败原因：" + returnMap.get("message"));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("导入失败.");
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return Result.error("文件导入失败！");
    }

    /**
     * 批量删除人员
     *
     * @param ids
     * @return
     */
    //@AutoLog(value = "题库管理-批量删除人员")
    @ApiOperation(value = "题库管理-批量删除人员")
    @GetMapping(value = "/deleteBatch")
    public Result<Map<String,String>> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<Map<String,String>> result = new Result<>();
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isEmpty(ids)) {
            result.error500("参数不识别！");
        } else {
            try {
                map = problemService.removeProblemAndAnswerByIds(Arrays.asList(ids.split(",")));
                if(map.get("success").equals("true")){
                    result.success("删除成功!");
                    result.setResult(map);
                    result.setMessage(map.get("msg"));
                }else{
                    result.success("删除失败!");
                    result.setResult(map);
                    result.setMessage(map.get("msg"));
                }
            } catch (Exception e) {
                //log.error(e.getMessage(), e);
                result.setResult(map);
                result.success("操作失败!");
            }
        }
        return result;
    }

    /**
     * 通过id查询详情
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "题库管理-通过id查询详情")
    @ApiOperation(value = "题库管理-通过id查询详情")
    @GetMapping(value = "/detail")
    public Result<JSONObject> detail(@RequestParam(name = "id", required = true) String id) {
        Result<JSONObject> result = new Result<>();
        TBusiExamProblemTemp problem = problemService.getById(id);
        if (problem == null) {
            result.error500("未找到对应实体");
        } else {
            JSONObject json = problemService.getDetailByProblem(problem);
            if (json == null) {
                result.error500("未找到对应实体");
            } else {
                result.setResult(json);
                result.setSuccess(true);
            }
        }
        return result;
    }

    /**
     * 添加(未实现，待确认需求再写)
     *
     * @param jsonObject
     * @return
     */
    @AutoLog(value = "题库管理-添加")
    @ApiOperation(value = "题库管理-添加")
    @PutMapping(value = "/add")
    public Result<TBusiExamProblemTemp> add(@RequestBody JSONObject jsonObject) {
        Result<TBusiExamProblemTemp> result = new Result<>();
        try {
            problemService.addProblemAndAnswer(jsonObject);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 编辑
     * @param jsonObject
     * @return
     */
    //@AutoLog(value = "题库管理-编辑标签")
    @ApiOperation(value = "题库管理-编辑标签")
    @PostMapping(value = "/editProblem")
    public Result<TBusiExamProblemTemp> editProblem(@RequestBody JSONObject jsonObject) {
        Result<TBusiExamProblemTemp> result = new Result<>();
        try {
            TBusiExamProblemTemp problemTemp = problemService.getById(jsonObject.getString("id"));
            if (problemTemp == null) {
                result.error500("未找到对应实体");
            } else {
                problemTemp.setUpdateTime(new Date());
                problemTemp.setUpdateBy("73");
                problemTemp.setProblemClassify(jsonObject.getString("problemClassify")!=null?jsonObject.getString("problemClassify"):problemTemp.getProblemClassify());
                problemTemp.setEquip(jsonObject.getString("equip")!=null?jsonObject.getString("equip"):problemTemp.getEquip());
                problemTemp.setPosition(jsonObject.getString("position")!=null?jsonObject.getString("position"):problemTemp.getPosition());
                problemService.saveOrUpdate(problemTemp);
                result.success("修改成功!");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

}
