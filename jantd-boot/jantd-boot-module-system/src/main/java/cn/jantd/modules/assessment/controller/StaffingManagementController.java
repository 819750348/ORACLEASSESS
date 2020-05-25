package cn.jantd.modules.assessment.controller;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.assessment.service.impl.StaffingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.controller
 * @ClassName: StaffingManagementController
 * @Author: admin
 * @Description:
 * @Date: 2020/5/21 19:40
 * @Version: 1.0
 */

@RestController
@RequestMapping("/api/staffingManagement")
public class StaffingManagementController {

    @Autowired
    StaffingManagementService staffingManagementService;
    @RequestMapping("/queryPageList")
    public PersonnelResult queryPageList(@RequestParam(value = "pageNo", required = false) int pageNo, @RequestParam(value = "equipPosition", required = false) String equipPosition){
        PersonnelResult personnelResult = staffingManagementService.queryPageList(pageNo,equipPosition);
        return personnelResult;
    }
}
