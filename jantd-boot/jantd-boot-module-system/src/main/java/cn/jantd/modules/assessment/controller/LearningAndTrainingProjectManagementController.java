package cn.jantd.modules.assessment.controller;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.assessment.service.impl.LearningAndTrainingProjectManagementService;
import cn.jantd.modules.teacher.entity.TBusiStudyTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.controller
 * @ClassName: LearningAndTrainingProjectManagementController
 * @Author: admin
 * @Description:
 * @Date: 2020/5/28 14:43
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/learningAndTrainingProjectManagement")
public class LearningAndTrainingProjectManagementController {

    @Autowired
    LearningAndTrainingProjectManagementService learningAndTrainingProjectManagementService;

    /**
     * @Author: 风中的那朵云
     * @Description: 初始化用户设置界面
     * @Date: 2020/5/6
     * @Version: 1.0
     */
    @RequestMapping("/queryPageList")
    public PersonnelResult queryPageList(@RequestParam(value = "pageNo", required = false) int pageNo) {
        PersonnelResult personnelResult = learningAndTrainingProjectManagementService.queryPageList(pageNo);
        return personnelResult;
    }

    @RequestMapping("addLearningAndTrainingProject")
    @ResponseBody
    public String addLearningAndTrainingProject(TBusiStudyTemp tBusiStudy){
        try {
            learningAndTrainingProjectManagementService.addLearningAndTrainingProject(tBusiStudy);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }

    }


    @RequestMapping("/editAndTrainingProjectManagement")
    public String editAndTrainingProjectManagement(String learningManagementId,String equipPosition){
        try {
            learningAndTrainingProjectManagementService.editAndTrainingProjectManagement(learningManagementId,equipPosition);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

}
