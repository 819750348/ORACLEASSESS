package cn.jantd.modules.assessment.mapper;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import cn.jantd.modules.assessment.entity.TBusiStudy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.mapper
 * @ClassName: LearningAndTrainingProjectManagementMapper
 * @Author: admin
 * @Description:
 * @Date: 2020/5/28 15:14
 * @Version: 1.0
 */
public interface LearningAndTrainingProjectManagementMapper extends BaseMapper<TBusiStudy> {
    List<TBusiStudy> queryPageList(int pageNo);
    int queryPageTotal();


    void addLearningAndTrainingProject(String name ,String studyType,String equipPosition, String studyTime);

    void editAndTrainingProjectManagement(String id,String equipPosition);
}
