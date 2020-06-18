package cn.jantd.modules.teacher.controller;

import cn.jantd.core.api.vo.Result;
import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.VO.TBusiExamStaffVO;
import cn.jantd.modules.teacher.VO.TBusiExamVO;
import cn.jantd.modules.teacher.VO.TSysStaffVO;
import cn.jantd.modules.teacher.service.ITBusiExamCopyService;
import cn.jantd.modules.teacher.service.ITBusiExamService;
import cn.jantd.modules.teacher.service.ITSysStaffService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author qiji
 * @date 2020-03-03
 */
@RestController
@RequestMapping("/teacher/exam")
@Api(tags = "试卷")
@Slf4j
public class TBusiExamController {

    @Autowired
    private ITBusiExamService examService;
    @Autowired
    private ITBusiExamCopyService examCopyService;
    @Autowired
    private ITSysStaffService itSysStaffService;

    /**
     * @param pageNo
     * @param pageSize
     * @return
     * @功能：考核评佑-考核评价分页列表
     */
    //@AutoLog(value = "考核评佑-考核评价分页列表")
    @ApiOperation(value = "考核评佑-考核评价分页列表")
    @GetMapping(value = "/queryExamCopyPageList")
    public PersonnelResult queryExamCopyPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("pageNo", pageNo);
        pageParam.put("pageSize", pageSize);
        //前端传参条件查询，装备和岗位
        pageParam.put("teacherId", "73"); // 教师id
        PersonnelResult personnelResult = examCopyService.searchPage(pageParam);
        return personnelResult;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @return
     * @功能：制定试卷-试卷分页列表
     */
    //@AutoLog(value = "制定试卷-试卷分页列表")
    @ApiOperation(value = "制定试卷-试卷分页列表")
    @GetMapping(value = "/queryExamPageList")
    public PersonnelResult queryExamPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        PersonnelResult personnelResult = new PersonnelResult();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("pageNo", pageNo);
        pageParam.put("pageSize", pageSize);
        //前端传参条件查询，装备和岗位
        pageParam.put("teacherId", "73"); // 教师id
        pageParam.put("equipPosition", req.getParameter("equipPosition")!=null?String.valueOf(req.getParameter("equipPosition")):"");
        Map<String, Object> map = examService.createExam(String.valueOf(pageParam.get("teacherId")));
        if(map != null && String.valueOf(map.get("result")).equals("true")){
            personnelResult = examService.searchExamPage(pageParam);
        }
        return personnelResult;
    }

    /**
     * 考核评价-试卷推送
     * @param examId
     * @return
     */
    //@AutoLog(value = "考核评价-试卷推送")
    @ApiOperation(value = "考核评价-试卷推送")
    @PostMapping(value = "/sendExam")
    public Result<Map<String, String>> sendExam(@RequestParam(name = "examId", required = true) String examId) {
        Result<Map<String, String>> result = new Result<>();
        String staffId = "73";//session获取，暂时写死
        Map<String, String> o = examService.sendExam(examId, staffId);
        if(o != null){
            result.setResult(o);
            result.setSuccess(true);
            result.setMessage("推送成功");
        }else{
            result.setSuccess(false);
            result.setMessage("推送失败");
        }
        return result;
    }

    /**
     * 教学管理员端-试卷配置
     * @return
     */
    //@AutoLog(value = "教学管理员端-试卷配置")
    @ApiOperation(value = "教学管理员端-试卷配置")
    @PostMapping(value = "/createExam")
    public Result<Map<String, Object>> createExam() {
        //参数是登录用户id，先写死，后取session
        String teacherId = "73";
        Result<Map<String, Object>> result = new Result<>();
        Map<String, Object> map = examService.createExam(teacherId);
        if(map != null && String.valueOf(map.get("result")).equals("true")){
            result.setResult(map);
            result.setSuccess(true);
            result.setMessage("操作成功");
        }else{
            result.setSuccess(false);
            result.setMessage("操作失败");
        }
        return result;
    }

    /**
     * 试卷配置-试卷查看
     * @param examId
     * @return
     */
    //@AutoLog(value = "试卷配置-试卷查看")
    @ApiOperation(value = "制定试卷-试卷查看")
    @GetMapping(value = "/detailExamById")
    public Result<JSONObject> detailExamById(@RequestParam(name = "examId", required = true) String examId) {
        Result<JSONObject> result = new Result<>();
        JSONObject o = examService.detailExamById(examId);
        if(o != null){
            result.setResult(o);
            result.setSuccess(true);
            result.setMessage("查询成功");
        }else{
            result.setSuccess(false);
            result.setMessage("查询失败");
        }
        return result;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @param req(必须包含学员id)
     * @return
     * @功能：学员端-试卷列表分页查询(去掉分页)
     */
    //@AutoLog(value = "学员端-试卷列表分页查询(去掉分页)")
    @ApiOperation(value = "学员端-试卷列表分页查询(去掉分页)")
    @GetMapping(value = "/queryExamByStudent")
    public Result<Map<String,Object>> queryExamByStudent(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                         @RequestParam(name = "studentId") String studentId, HttpServletRequest req) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("studentId", studentId);
        //pageParam.put("pageNo", pageNo);
        //pageParam.put("pageSize", pageSize);
        Map<String,Object> resultMap = examCopyService.searchExamByStudent(pageParam);
        result.setSuccess(true);
        result.setResult(resultMap);
        System.out.print(result.getResult());
        return result;
    }

    /**
     * 学员端-进入答题
     * @param examId
     * @param staffId
     * @return
     */
    //@AutoLog(value = "学员端-进入答题")
    @ApiOperation(value = "学员端-进入答题")
    @GetMapping(value = "/toEditByExamAndStaff")
    public Result<JSONObject> toEditByExamAndStaff(@RequestParam(name = "examId", required = true) String examId, @RequestParam(name = "staffId", required = true) String staffId) {
        Result<JSONObject> result = new Result<>();
        JSONObject o = examService.toEditByExamAndStaff(examId, staffId);
        if(o != null){
            result.setResult(o);
            result.setSuccess(true);
            result.setMessage("查询成功");
        }else{
            result.setSuccess(false);
            result.setMessage("查询失败");
        }
        return result;
    }

    /**
     * 考题考核-学生端-答题提交
     * @param paper
     * @return
     */
    //@AutoLog(value = "考题考核-学生端-答题提交")
    @ApiOperation(value = "考题考核-学生端-答题提交")
    @PostMapping(value = "/submitByExam")
    public Result<Map<String, Object>> submitByExam(@RequestBody JSONObject paper) {
        Result<Map<String, Object>> result = new Result<>();
        Map<String, Object> map = examService.editExamScores(paper);
        if(map != null){
            //map中scoresCount代表了本次试卷总得分
            result.setResult(map);
            result.setSuccess(true);
            result.setMessage("提交成功");
        }else{
            result.setSuccess(false);
            result.setMessage("提交失败");
        }
        return result;
    }






    /** ------------------------------------------------------------------------------------------------------------ */

    /**
     * 教师端试卷-通过教师id查询下发的试卷列表
     * @return
     */
    //@AutoLog(value = "教师端试卷-通过人员id查询名下试卷列表")
    @ApiOperation(value = "教师端-首页试卷列表-通过教师id查询下发的试卷列表")
    @GetMapping(value = "/queryExamByStaffid")
    public Result<List<TBusiExamStaffVO>> queryById(HttpServletRequest req) {
        //staffid从session中取,代码待测试
        //String staffid = (String) req.getSession().getAttribute("id");
        String staffid = "73";
        Result<List<TBusiExamStaffVO>> result = new Result<>();
        List<TBusiExamStaffVO> examList = examService.getStaffById(staffid);
        if (examList == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(examList);
            result.setSuccess(true);
            //System.out.print(JSON.toJSONString(result));
        }
        return result;
    }

    /**
     * 教师端试卷-根据试卷查询试卷指定人员列表
     * @param id
     * @return
     */
    //@AutoLog(value = "教师端试卷-根据试卷查询试卷指定人员列表")
    @ApiOperation(value = "教师端-首页试卷列表-根据试卷查询试卷指定人员列表")
    @GetMapping(value = "/queryStaffByExamid")
    public Result<JSONArray> queryStaffByExamid(@RequestParam(name = "id", required = false) String id) {
        Result<JSONArray> result = new Result<>();
        JSONArray staffList = itSysStaffService.getStaffByExamid(id);
        if (staffList == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(staffList);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 考题考核-首页-最新试题
     * @param id
     * @return
     */
    //@AutoLog(value = "考题考核-首页-最新试题")
    @ApiOperation(value = "考题考核-首页-最新试题")
    @GetMapping(value = "/queryLastExamByStudent")
    public Result<List<TBusiExamVO>> queryLastExamByStudent(@RequestParam(name = "id", required = true) String id) {
        //学生端登录展示答题列表,显示倒序显示所有,多了滚动
        Result<List<TBusiExamVO>> result = new Result<>();
        List<TBusiExamVO> examList = examService.queryLastExamByStudent(id);
        if (examList == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(examList);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 考题考核-首页-最新试题-查看
     * @param examid
     * @param staffid
     * @return
     */
    //@AutoLog(value = "考题考核-首页-最新试题-查看")
    @ApiOperation(value = "考题考核-首页-最新试题-查看")
    @GetMapping(value = "/detailExamByStaff")
    public Result<JSONObject> detailExamByStaff(@RequestParam(name = "examid", required = true) String examid, @RequestParam(name = "staffid", required = true) String staffid) {
        Result<JSONObject> result = new Result<>();
        JSONObject o = examService.detailExamByStaff(examid, staffid);
        if(o != null){
            result.setResult(o);
            result.setSuccess(true);
            result.setMessage("查询成功");
        }else{
            result.setSuccess(false);
            result.setMessage("查询失败");
        }
        return result;
    }

    /**
     * 考题考核-学生端-答题提交
     * @param paper
     * @return
     */
    //@AutoLog(value = "考题考核-学生端-答题提交")
    @ApiOperation(value = "考题考核-学生端-答题提交")
    @PostMapping(value = "/editByExamAndStaff")
    public Result<Map<String, Object>> editByExamAndStaff(@RequestBody JSONObject paper) {
        Result<Map<String, Object>> result = new Result<>();
        Map<String, Object> map = examService.editExamScores(paper);
        if(map != null){
            //map中scoresCount代表了本次试卷总得分
            result.setResult(map);
            result.setSuccess(true);
            result.setMessage("提交成功");
        }else{
            result.setSuccess(false);
            result.setMessage("提交失败");
        }
        return result;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @param req(必须包含学员id)
     * @return
     * @功能：考题考核-学员端-练习(分页查询历史试卷)
     */
    //@AutoLog(value = "考题考核-学员端-练习(分页查询历史试卷)")
    @ApiOperation(value = "考题考核-学员端-练习(分页查询历史试卷)")
    @GetMapping(value = "/searchLastByStaff")
    public Result<Map<String,Object>> searchLastByStaff(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("studentid", String.valueOf(req.getParameter("studentid")));
        pageParam.put("pageNo", pageNo);
        pageParam.put("pageSize", pageSize);
        //默认以时间倒序排序
        pageParam.put("orderValue", req.getParameter("orderValue")!=null?String.valueOf(req.getParameter("orderValue")):"exam_time");
        pageParam.put("orderDirection", req.getParameter("orderDirection")!=null?String.valueOf(req.getParameter("orderDirection")):"desc");
        Map<String,Object> resultMap = examService.searchLastByStaff(pageParam);
        result.setSuccess(true);
        result.setResult(resultMap);
        System.out.print(result.getResult());
        return result;
    }

    /**
     * @param studentid
     * @return
     * @功能：考题考核-学员端-首页-综合成绩分析
     */
    //@AutoLog(value = "考题考核-学员端-首页-综合成绩分析")
    @ApiOperation(value = "考题考核-学员端-首页-综合成绩分析")
    @GetMapping(value = "/queryLast5")
    public Result<Map<String,Object>> queryLast5(@RequestParam(name = "studentid") String studentid) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        //首页只查询最新五个,用和分页一样的代码并且写死1页5条数据即可实现
        pageParam.put("pageNo", 1);
        pageParam.put("pageSize", 5);
        pageParam.put("orderValue", "exam_time");
        pageParam.put("orderDirection", "desc");
        pageParam.put("studentid", studentid);
        Map<String,Object> resultMap = examService.searchLastByStaff(pageParam);
        result.setSuccess(true);
        result.setResult(resultMap);
        System.out.print(result.getResult());
        return result;
    }

    /**
     * @param studentid
     * @return
     * @功能：考题考核-学员端-首页-成绩概览
     */
    //@AutoLog(value = "考题考核-学员端-首页-成绩概览")
    @ApiOperation(value = "考题考核-学员端-首页-成绩概览")
    @GetMapping(value = "/queryLast")
    public Result<Map<String,Object>> queryLast(@RequestParam(name = "studentid") String studentid) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        Map<String,Object> resultMap = examService.searchBatchByStaff(studentid);
        if(resultMap != null){
            result.setSuccess(false);
            result.setResult(resultMap);
        }else{
            result.setSuccess(false);
            result.setResult(null);
        }
        return result;
    }





    /**
     * @param pageNo
     * @param pageSize
     * @param req(其他参数待定)
     * @return
     * @功能：考题考核-学员端-练习(分页查询历史试卷)
     */
    //@AutoLog(value = "分页查询试卷列表")
    /*@ApiOperation(value = "分页查询试卷列表")
    @GetMapping(value = "/queryPageList")
    public Result<Map<String,Object>> queryPageList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("pageNo", pageNo);
        pageParam.put("pageSize", pageSize);
        //默认以时间倒序排序
        pageParam.put("orderValue", req.getParameter("orderValue")!=null?String.valueOf(req.getParameter("orderValue")):"exam_time");
        pageParam.put("orderDirection", req.getParameter("orderDirection")!=null?String.valueOf(req.getParameter("orderDirection")):"desc");
        Map<String,Object> resultMap = examService.queryPageList(pageParam);
        result.setSuccess(true);
        result.setResult(resultMap);
        System.out.print(result.getResult());
        return result;
    }*/

    /**
     * 批量删除人员
     *
     * @param ids
     * @return
     */
    //@AutoLog(value = "题库管理-批量删除人员")
    @ApiOperation(value = "题库管理-批量删除人员")
    @GetMapping(value = "/deleteBatch")
    public Result<Map<String, String>> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<Map<String, String>> result = new Result<>();
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isEmpty(ids)) {
            result.error500("参数不识别！");
        } else {
            try {
                map = examService.removeExamAndRelByIds(Arrays.asList(ids.split(",")));
                if(map.get("success").equals("true")){
                    result.success("删除成功!");
                    result.setMessage(map.get("msg"));
                }else{
                    result.success("删除失败!");
                    result.setMessage(map.get("msg"));
                }
            } catch (Exception e) {
                //log.error(e.getMessage(), e);
                result.success("操作失败!");
            }
        }
        return result;
    }

    /**
     * 教学管理员端-试卷下发操作
     * @param paper
     * @return
     */
    //@AutoLog(value = "教学管理员端-试卷下发操作")
    /*@ApiOperation(value = "教学管理员端-试卷下发操作")
    @PostMapping(value = "/sendExam")
    public Result<Map<String, String>> sendExam(@RequestBody JSONObject paper) {
        //不确定需求是下发分组还是下发人员，暂时做一个人员下发的，为后面做准备
        //参数不确定，暂定json，传试卷id 和 人员ids，下发人员为当前登录用户
        Result<Map<String, String>> result = new Result<>();
        Map<String, String> map = examService.sendExam(paper);
        if(map != null){
            result.setResult(map);
            result.setSuccess(true);
            result.setMessage("操作成功");
        }else{
            result.setSuccess(false);
            result.setMessage("操作失败");
        }
        return result;
    }*/



}
