package cn.jantd.modules.teacher.controller;

import cn.jantd.core.api.vo.Result;
import cn.jantd.modules.teacher.VO.TSysStaffVO;
import cn.jantd.modules.teacher.entity.TSysStaff;
import cn.jantd.modules.teacher.service.ITSysStaffService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
@RequestMapping("/teacher/staff")
// @Api(tags = "人员管理")
@Slf4j
public class TSysStaffController {
    @Autowired
    private ITSysStaffService itSysStaffService;


    /**
     * @param pageNo
     * @param pageSize
     * @param req (如果是教学管理员的人员配置管理必须包含staffType=1)
     * @return
     * @功能：人员配置管理-分页查询人员列表
     */
    //@AutoLog(value = "人员配置管理-分页查询人员列表")
    @ApiOperation(value = "人员配置管理-分页查询人员列表")
    @GetMapping(value = "/staffList")
    public Result<Map<String,Object>> staffList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("pageNo", pageNo);
        pageParam.put("pageSize", pageSize);
        //1教学管理员2学员,前台参数传递
        pageParam.put("staffType", req.getParameter("staffType")!=null?String.valueOf(req.getParameter("staffType")):"");
        //名称模糊查询
        pageParam.put("name", req.getParameter("name")!=null?"%"+String.valueOf(req.getParameter("name"))+"%":"");
        Map<String,Object> resultMap = itSysStaffService.searchStaffPage(pageParam);
        result.setSuccess(true);
        result.setResult(resultMap);
        //System.out.print(result.getResult());
        return result;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    //@AutoLog(value = "人员配置管理-通过excel导入数据")
    @ApiOperation(value = "人员配置管理-通过excel导入数据")
    @PostMapping(value = "/importExcel")
    public Result<Object> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Map<String,String> returnMap = new HashMap<String,String>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            try {
                returnMap = itSysStaffService.importExcelToStaff(file.getInputStream());
                if("0".equals(returnMap.get("code"))){
                    System.out.println(Result.ok("文件导入成功！数据行数：" + returnMap.get("num") + "\n" + returnMap.get("message")));
                    return Result.ok("文件导入成功！数据行数：" + returnMap.get("num") + "\n" + returnMap.get("message"));
                }else {
                    System.out.println(Result.error("文件导入失败.失败原因：" + returnMap.get("message")));
                    return Result.error("文件导入失败.失败原因：" + returnMap.get("message"));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                System.out.println(Result.ok("文件导入成功！数据行数：" + returnMap.get("num") + "\n" + returnMap.get("message")));
                return Result.error("导入失败.\n" + returnMap.get("message"));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        //System.out.println(Result.error("文件导入失败.失败原因：" + returnMap.get("message")));
        return Result.error("文件导入失败.失败原因：" + returnMap.get("message"));
    }


    /**
     * 批量删除人员(单行删除也使用这个方法,暂时不删除关联数据,以后随需求更改)
     * @param ids
     * @return
     */
    //@AutoLog(value = "人员配置管理-批量删除人员")
    @ApiOperation(value = "人员配置管理-批量删除人员")
    @PostMapping(value = "/deleteBatch")
    public Result<TSysStaff> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<TSysStaff> result = new Result<>();
        System.out.print("ids:    "+ids);
        if (StringUtils.isEmpty(ids)) {
            result.error500("参数不识别！");
        } else {
            try {
                this.itSysStaffService.removeBatchStaffAndRelByIds(Arrays.asList(ids.split(",")));
                result.success("删除成功!");
            } catch (Exception e) {
                //log.error(e.getMessage(), e);
                result.success("删除失败!");
            }
        }
        return result;
    }


    /**
     * 编辑
     * @param jsonObject
     * @return
     */
    //@AutoLog(value = "人员管理-编辑")
    @ApiOperation(value = "人员管理-编辑")
    @PutMapping(value = "/editUser")
    public Result<TSysStaff> edit(@RequestBody JSONObject jsonObject) {
        Result<TSysStaff> result = new Result<>();
        try {
            TSysStaff sysUser = itSysStaffService.getById(jsonObject.getString("id"));
            if (sysUser == null) {
                result.error500("未找到对应实体");
            } else {
                sysUser.setUpdateTime(new Date());
                sysUser.setUpdateBy("73");
                sysUser.setStaffType(jsonObject.getString("staffType")!=null?jsonObject.getString("staffType"):sysUser.getStaffType());
                sysUser.setEquip(jsonObject.getString("equip")!=null?jsonObject.getString("equip"):sysUser.getEquip());
                sysUser.setPosition(jsonObject.getString("position")!=null?jsonObject.getString("position"):sysUser.getPosition());
                itSysStaffService.saveOrUpdate(sysUser);
                result.success("修改成功!");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 人员登录接口(FD2000登录)
     * @param username,password
     * @return
     */
    @GetMapping(value = "/FD2000Login")
    @ApiOperation("人员登录接口")
    public Result<TSysStaffVO> FD2000Login(@RequestParam(name = "username", required = true) String username, @RequestParam(name = "password", required = true) String password) throws Exception {
        Result<TSysStaffVO> result = new Result<TSysStaffVO>();
        //dictCode,itemValue是列表条件
        Map<String, Object> pageParam = new HashMap<String, Object>();
        List<TSysStaffVO> list = itSysStaffService.getStudentsByLogin(pageParam);
        if (list == null) {
            result.setSuccess(false);
            result.setMessage("用户名不存在或密码错误");
        } else {
            result.setSuccess(true);
            result.setMessage("登录成功");
            result.setResult(list.get(0));
        }
        return result;
    }





    /* 旧方法------------------------------------------------------------------------------------------------------------ */
    /**
     * 用户管理-查询所有学员教学进度
     * @return
     */
    @GetMapping(value = "/getStudentsCourse")
    @ApiOperation("人员管理-查询所有学员教学进度")
    public Result<JSONArray> getStudentsCourse(@RequestParam(name = "dictCode", required = false) String dictCode, @RequestParam(name = "itemValue", required = false) String itemValue) throws Exception {
        Result<JSONArray> result = new Result<JSONArray>();
        //dictCode,itemValue是列表条件
        JSONArray list = itSysStaffService.getStudentsCourse(dictCode, itemValue);
        if (list == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(list);
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 通过id查询
     * @return
     */
    //@AutoLog(value = "人员管理-通过id查询")
    @ApiOperation(value = "人员管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<TSysStaffVO> queryById(HttpServletRequest req) {
        //staffid从session中取,代码待测试
        //String staffid = (String) req.getSession().getAttribute("id");
        String staffid = "73";
        //查到一条数据即可
        Result<TSysStaffVO> result = new Result<>();
        TSysStaffVO staff = itSysStaffService.getStaffById(staffid);
        if (staff == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(staff);
            result.setSuccess(true);
        }
        return result;
    }


    /**
     * 添加
     * @param jsonObject
     * @return
     */
    //@AutoLog(value = "人员管理-添加")
    @ApiOperation(value = "人员管理-添加")
    @PostMapping(value = "/add")
    public Result<TSysStaff> add(@RequestBody JSONObject jsonObject) {
        Result<TSysStaff> result = new Result<>();
        try {
            TSysStaff staff = JSON.parseObject(jsonObject.toJSONString(), TSysStaff.class);
            staff.setCreateBy("73");
            staff.setCreateTime(new Date());
            staff.setUpdateBy("73");
            staff.setUpdateTime(new Date());
            staff.setPassword("123456");
            itSysStaffService.save(staff);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }


    /**
     * 校验用户账号是否唯一<br>
     * 可以校验其他 需要检验什么就传什么。。。
     * @param tSysStaff
     * @return
     */
    @ApiOperation(value = "人员管理-校验用户账号是否唯一")
    @GetMapping(value = "/checkOnlyUser")
    public Result<Boolean> checkUsername(TSysStaff tSysStaff) {
        Result<Boolean> result = new Result<>();
        // 如果此参数为false则程序发生异常
        result.setResult(true);
        String id = tSysStaff.getId();
        String username = tSysStaff.getUserName();
        log.info("--验证用户信息是否唯一---id:" + id);
        try {
            boolean flag = itSysStaffService.checkUser(id,username);
            if(flag == false){
                result.setSuccess(false);
                result.setMessage("用户账号已存在");
                return result;
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setResult(false);
            result.setMessage(e.getMessage());
            return result;
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 通过id删除
     *
     * @param id
     * @param staffType 需指定删除的人员类型
     * @return
     */
    //@AutoLog(value = "人员管理-通过id删除")
    @ApiOperation(value = "人员管理-通过id删除")
    @GetMapping(value = "/delete")
    public Result<TSysStaff> delete(@RequestParam(name = "id", required = false) String id, @RequestParam(name = "staffType", required = true) String staffType) {
        System.out.println("delete");
        Result<TSysStaff> result = new Result<>();
        TSysStaff staff = itSysStaffService.getById(id);
        if (staff == null) {
            result.error500("未找到对应实体");
        } else {
            try {
                boolean ok = itSysStaffService.removeStaffAndRelById(id, staffType);
                if (ok) {
                    result.success("删除成功!");
                }
            } catch (Exception e) {
                //log.error(e.getMessage(), e);
                result.success("删除失败!");
            }
        }
        return result;
    }





    /**
     * @param pageNo
     * @param pageSize
     * @param teacherid
     * @return
     * @功能：教学管理-分页查询该教师名下学员列表
     */
    //@AutoLog(value = "教学管理-分页查询该教师名下学员列表")
    @ApiOperation(value = "教学管理-分页查询该教师名下学员列表")
    @GetMapping(value = "/queryStudentList")
    public Result<Map<String,Object>> queryStudentList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    @RequestParam(name = "teacherid",required = true) String teacherid, HttpServletRequest req) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("pageNo", pageNo);
        pageParam.put("pageSize", pageSize);
        pageParam.put("staffType", "2");
        //获取当前教学管理员id
        pageParam.put("teacherid", teacherid);
        pageParam.put("orderValue", String.valueOf(req.getParameter("orderValue")));
        pageParam.put("orderDirection", String.valueOf(req.getParameter("orderDirection")));
        Map<String,Object> resultMap = itSysStaffService.searchStudentPage(pageParam);
        result.setSuccess(true);
        result.setResult(resultMap);
        System.out.print(result.getResult());
        return result;
    }

    /**
     * @param pageNo
     * @param pageSize
     * @param teacherid
     * @return
     * @功能：用户管理-教学管理员管理点击展示分配学员列表分页查询
     */
    //@AutoLog(value = "用户管理-教学管理员管理点击展表分配学员列表分页查询")
    @ApiOperation(value = "人员管理-教学管理员管理点击展示分配学员列表分页查询")
    @GetMapping(value = "/studentConfigList")
    public Result<Map<String,Object>> studentConfigList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                        @RequestParam(name = "teacherid", required = true) String teacherid, HttpServletRequest req) {
        Result<Map<String,Object>> result = new Result<>();
        Map<String, Object> pageParam = new HashMap<String, Object>();
        //页码必须
        pageParam.put("pageNo", pageNo);
        // 每页数量默认10,可传值改变
        pageParam.put("pageSize", pageSize);
        //固定查询学员
        pageParam.put("staffType", "2");
        //点击时传值教学管理员id
        pageParam.put("teacherid", teacherid);
        pageParam.put("orderValue", String.valueOf(req.getParameter("orderValue")));
        pageParam.put("orderDirection", String.valueOf(req.getParameter("orderDirection")));
        //返回所有学员数据的分页列表,其中有teacherid的是当前教学管理员已分配的学员
        Map<String,Object> resultMap = itSysStaffService.studentConfigList(pageParam);
        result.setSuccess(true);
        result.setResult(resultMap);
        System.out.print(result.getResult());
        return result;
    }












}
