package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TBusiStudyCourseTempVO;
import cn.jantd.modules.teacher.VO.TBusiStudyTempVO;
import cn.jantd.modules.teacher.VO.TRelStaffCourseVO;
import cn.jantd.modules.teacher.entity.TRelStaffCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  学员课程关联表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Repository
public interface TRelStaffCourseMapper extends BaseMapper<TRelStaffCourse>  {

    /**
     * 列表数据总数
     * @param pageParam
     * @return
     */
    Integer searchPageTotal(Map<String, Object> pageParam);

    /**
     * 列表分页查询(查询课程进度，以项目为单位分组)
     * @param pageParam
     * @return
     */
    List<TBusiStudyTempVO> searchPageForMysql(Map<String, Object> pageParam);

    /**
     * 列表数据总数(根据项目查找人员列表)
     * @param pageParam
     * @return
     */
    Integer searchStaffTotal(Map<String, Object> pageParam);

    /**
     * 列表分页查询(根据项目查找人员列表)
     * @param pageParam
     * @return
     */
    List<TRelStaffCourseVO> searchStaffPageByStudy(Map<String, Object> pageParam);

    /**
     * 列表分页查询(根据学员查找课程列表)
     * @param pageParam
     * @return
     */
    List<TBusiStudyTempVO> searchStudyByStudent(Map<String, Object> pageParam);
}
