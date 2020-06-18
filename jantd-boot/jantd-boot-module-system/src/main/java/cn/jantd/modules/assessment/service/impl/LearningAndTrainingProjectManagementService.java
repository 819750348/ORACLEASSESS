package cn.jantd.modules.assessment.service.impl;

import cn.jantd.modules.assessment.mapper.LearningAndTrainingProjectManagementMapper;
import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.entity.TBusiStudyCourseTemp;
import cn.jantd.modules.teacher.entity.TBusiStudyTemp;
import cn.jantd.modules.teacher.mapper.TBusiStudyCourseTempMapper;
import cn.jantd.modules.teacher.mapper.TBusiStudyTempMapper;
import cn.jantd.modules.teacher.service.ITBusiStudyCourseTempService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
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
    @Autowired
    TBusiStudyCourseTempMapper tBusiStudyCourseTempMapper;
    @Autowired
    ITBusiStudyCourseTempService studyCourseTempService;

    public PersonnelResult queryPageList(int pageNo){
        int pageSize = (pageNo-1) * 10;
        List<TBusiStudyTemp> tBusiStudyList= learningAndTrainingProjectManagementMapper.queryPageList(pageSize);
        int total= learningAndTrainingProjectManagementMapper.queryPageTotal();
        PersonnelResult personnelResult = new PersonnelResult();
        personnelResult.setPersonnelList(tBusiStudyList);
        personnelResult.setTotal(total);
        return personnelResult;
    }


    public void addLearningAndTrainingProject(TBusiStudyTemp tBusiStudy){
        learningAndTrainingProjectManagementMapper.insert(tBusiStudy);
        TBusiStudyCourseTemp tBusiStudyCourseTemp=new TBusiStudyCourseTemp();
//        tBusiStudyCourseTemp.setId(tBusiStudy.getId());
        tBusiStudyCourseTemp.setEquipPosition(tBusiStudy.getEquipPosition());
        tBusiStudyCourseTemp.setStudyType(tBusiStudy.getStudyType());
        tBusiStudyCourseTemp.setStudyTime(tBusiStudy.getStudyTime());
        tBusiStudyCourseTemp.setTeacherId("73");
        tBusiStudyCourseTemp.setStudyId(tBusiStudy.getId());
        tBusiStudyCourseTempMapper.insert(tBusiStudyCourseTemp);

//        learningAndTrainingProjectManagementMapper.addLearningAndTrainingProject(tBusiStudy.getName(),tBusiStudy.getStudyType(),tBusiStudy.getEquipPosition(),tBusiStudy.getStudyTime());
    }


    public void editAndTrainingProjectManagement(String learningManagementId,String equipPosition){
        //添加更新计划表
        LambdaQueryWrapper<TBusiStudyCourseTemp> qeq = new LambdaQueryWrapper<TBusiStudyCourseTemp>();
        qeq.eq(TBusiStudyCourseTemp::getStudyId,learningManagementId);
        List<TBusiStudyCourseTemp> sc = studyCourseTempService.list(qeq);
        for (TBusiStudyCourseTemp temp: sc) {
            temp.setEquipPosition(equipPosition);
        }
        studyCourseTempService.updateBatchById(sc);

        learningAndTrainingProjectManagementMapper.editAndTrainingProjectManagement(learningManagementId, equipPosition);
    }
}
