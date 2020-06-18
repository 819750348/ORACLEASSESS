package cn.jantd.modules.teacher.service;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.entity.TRelStaffCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  学员课程关联表 服务类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
public interface ITRelStaffCourseService extends IService<TRelStaffCourse> {

    /**
     * 先删除教学管理员原有关联课程，再重新添加
     * @param studentid
     * @param ids
     * @return
     */
    boolean configCourse(String studentid, String ids);

    boolean configDelete(String studentid, String courseid);

    /**
     * 学习进度列表查询
     * @param pageParam
     * @return
     */
    PersonnelResult searchPage(Map<String, Object> pageParam);

    /**
     * 根据项目id分页查询人员列表
     * @param pageParam
     * @return
     */
    PersonnelResult searchStaffPageByStudy(Map<String, Object> pageParam);

    /**
     * 根据学员id分页查询课程列表
     * @param pageParam
     * @return
     */
    Map<String, Object> searchPageByStudent(Map<String, Object> pageParam);
}