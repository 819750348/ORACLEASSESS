package cn.jantd.modules.teacher.service;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.entity.TBusiStudyCourseTemp;
import cn.jantd.modules.teacher.entity.TBusiStudyTemp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  学习和训练计划表 服务类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-05
 */
public interface ITBusiStudyCourseTempService extends IService<TBusiStudyCourseTemp> {

    /**
     * 分页查询
     */
    PersonnelResult searchPage(Map<String, Object> pageParam);

    /**
     * 批量删除课表，并根据课表岗位删除试卷
     * @param ids
     * @return
     */
    Boolean removeIdsAndRel(String ids);

    /**
     * 创建学习和训练计划并根据岗位生成试卷
     * @param study
     * @return
     */
    boolean saveStudy(TBusiStudyCourseTemp study);

    /**
     * 推送课表
     * @param teacherId
     * @return
     */
    Boolean toStudents(String teacherId);
}