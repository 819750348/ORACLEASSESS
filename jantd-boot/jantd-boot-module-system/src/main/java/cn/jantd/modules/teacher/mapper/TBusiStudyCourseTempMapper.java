package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TBusiStudyCourseTempVO;
import cn.jantd.modules.teacher.VO.TBusiStudyTempVO;
import cn.jantd.modules.teacher.entity.TBusiStudyCourseTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  学习和训练计划表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-04-20
 */
@Repository
public interface TBusiStudyCourseTempMapper extends BaseMapper<TBusiStudyCourseTemp>  {

    /**
     * 分页列表mysql
     * @param pageParam 页面分页参数,数字型(pageSize,pageNo),其他参数,字符型(包括排序等)
     * @return List
     */
    List<TBusiStudyCourseTempVO> searchPageForMysql(Map<String, Object> pageParam);

    /**
     * 总数
     * @param pageParam 参数,字符型(查询条件)
     * @return List
     */
    Integer searchPageTotal(Map<String, Object> pageParam);

    /**
     * 查询所有计划
     * @param map
     * @return
     */
    List<TBusiStudyCourseTemp> searchByTeacher(Map<String, Object> map);
}
