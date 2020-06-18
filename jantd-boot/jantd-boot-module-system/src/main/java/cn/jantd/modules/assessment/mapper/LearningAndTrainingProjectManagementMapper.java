package cn.jantd.modules.assessment.mapper;

import cn.jantd.modules.teacher.entity.TBusiStudyTemp;
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
public interface LearningAndTrainingProjectManagementMapper extends BaseMapper<TBusiStudyTemp> {
    List<TBusiStudyTemp> queryPageList(int pageNo);
    int queryPageTotal();


    void addLearningAndTrainingProject(String name ,String studyType,String equipPosition, String studyTime);

    void editAndTrainingProjectManagement(String id,String equipPosition);
}
