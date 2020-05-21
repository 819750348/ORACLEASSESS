package cn.jantd.modules.assessment.controller;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.assessment.service.impl.PersonnelSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.controller
 * @ClassName: personnelSettingsController
 * @Author: admin
 * @Description: 用户设置
 * @Date: 2020/5/20 13:44
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/personnelSettings")
public class PersonnelSettingsController {
    @Autowired
    PersonnelSettingsService personnelSettingsService;


/**
 * @Author:     风中的那朵云
 * @Description:  初始化用户设置界面
 * @Date:    2020/5/6
 * @Version:    1.0
 */
    @RequestMapping("/queryPageList")
    public PersonnelResult queryPageList(@RequestParam(value = "pageNo", required = false) int pageNo){
        PersonnelResult personnelResult = personnelSettingsService.queryPageList(pageNo);
        return personnelResult;
    }
    @RequestMapping("addPersonnel")
    @ResponseBody
    public String addPersonnel(PersonnelSettings personnelSettings){
        try {
            personnelSettingsService.addPersonnel(personnelSettings);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }

    }

}
