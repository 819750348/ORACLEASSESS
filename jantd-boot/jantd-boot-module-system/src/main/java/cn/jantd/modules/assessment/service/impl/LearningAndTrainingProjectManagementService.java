package cn.jantd.modules.assessment.service.impl;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import cn.jantd.modules.assessment.entity.TBusiStudy;
import cn.jantd.modules.assessment.mapper.LearningAndTrainingProjectManagementMapper;
import cn.jantd.modules.assessment.mapper.PersonnelSettingsMapper;
import cn.jantd.modules.assessment.model.PersonnelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.service.impl
 * @ClassName: LearningAndTrainingProjectManagementService
 * @Author: admin
 * @Description:
 * @Date: 2020/5/28 15:04
 * @Version: 1.0
 */
@Service
public class LearningAndTrainingProjectManagementService {
    @Autowired
    LearningAndTrainingProjectManagementMapper learningAndTrainingProjectManagementMapper;

    public PersonnelResult queryPageList(int pageNo){
        int pageSize = (pageNo-1) * 10;
        List<TBusiStudy> tBusiStudyList= learningAndTrainingProjectManagementMapper.queryPageList(pageSize);
        int total= learningAndTrainingProjectManagementMapper.queryPageTotal();
        PersonnelResult personnelResult = new PersonnelResult();
        personnelResult.setPersonnelList(tBusiStudyList);
        personnelResult.setTotal(total);
        return personnelResult;
    }


    public void addLearningAndTrainingProject(TBusiStudy tBusiStudy){
        learningAndTrainingProjectManagementMapper.insert(tBusiStudy);
//        learningAndTrainingProjectManagementMapper.addLearningAndTrainingProject(tBusiStudy.getName(),tBusiStudy.getStudyType(),tBusiStudy.getEquipPosition(),tBusiStudy.getStudyTime());
    }


    public void editAndTrainingProjectManagement(String learningManagementId,String equipPosition){
        learningAndTrainingProjectManagementMapper.editAndTrainingProjectManagement(learningManagementId, equipPosition);
    }
}
