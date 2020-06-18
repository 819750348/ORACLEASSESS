package cn.jantd.modules.assessment.controller;

import cn.jantd.core.api.vo.Result;
import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.assessment.service.impl.TestManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.controller
 * @ClassName: TestManagementController
 * @Author: admin
 * @Description:
 * @Date: 2020/5/29 11:23
 * @Version: 1.0
 */

@RestController
@RequestMapping("/api/testManagement")
@Slf4j
public class TestManagementController {
    @Autowired
    TestManagementService testManagementService;

    @RequestMapping("/queryPageList")
    public PersonnelResult queryPageList(@RequestParam(value = "pageNo", required = false) int pageNo, @RequestParam(value = "equipPosition", required = false) String equipPosition, @RequestParam(value = "testDescribe", required = false) String testDescribe){
        PersonnelResult personnelResult = testManagementService.queryPageList(pageNo,equipPosition,testDescribe);
        return personnelResult;
    }


    @RequestMapping("editTestManagement")
    @ResponseBody
    public String editTestManagement(String testManagementId,String testManagementEquipPosition){
        try {
            testManagementService.editTestManagement(testManagementId,testManagementEquipPosition);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @RequestMapping("deleteTestManagement")
    @ResponseBody
    public String deleteTestManagement(String testManagementId){
        try {
            testManagementService.deleteTestManagement(testManagementId);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }


    @RequestMapping("addTest")
    @ResponseBody
    public String addTest(String problemTitle,String problemClassify,String problemType,String equipPosition,String answerStr, String successAnswer){
        try {
            testManagementService.addTest(problemTitle, problemClassify, problemType, equipPosition, answerStr,  successAnswer);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }

    }




    @PostMapping("importExcel")
    @ResponseBody
    public Result<Object> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Map<String,String> returnMap = new HashMap<String,String>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            try {
                returnMap = testManagementService.importExcel(file.getInputStream());
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
        return Result.error("文件导入失败.失败原因：" + returnMap.get("message"));
    }
}

