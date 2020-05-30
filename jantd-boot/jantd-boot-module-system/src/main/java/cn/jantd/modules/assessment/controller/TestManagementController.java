package cn.jantd.modules.assessment.controller;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.assessment.service.impl.TestManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
