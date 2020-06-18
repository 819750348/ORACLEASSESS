package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TBusiExamCopyVO;
import cn.jantd.modules.teacher.VO.TBusiExamProblemVO;
import cn.jantd.modules.teacher.VO.TBusiExamVO;
import cn.jantd.modules.teacher.VO.TBusiStudyTempVO;
import cn.jantd.modules.teacher.entity.TBusiExamCopy;
import cn.jantd.modules.teacher.entity.TBusiExamProblemTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  试卷复制表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Repository
public interface TBusiExamCopyMapper extends BaseMapper<TBusiExamCopy>  {

    /**
     * 根据学员id查询最新下发的试卷,不分页
     * @param id
     * @return
     */
    List<TBusiExamVO> queryLastExamByStudent(@Param("id") String id);

    /**
     * 根据试卷id和学员id查询试卷明细
     * @param examid
     * @param staffid
     * @return
     */
    TBusiExamVO examDetail(@Param("examid") String examid, @Param("staffid") String staffid);

    /**
     *  根据学员id查询试卷总数
     * @param pageParam
     * @return
     */
    Integer searchTotalByStaff(Map<String, Object> pageParam);

    /**
     *  根据学员id查询试卷总数
     * @param pageParam
     * @return
     */
    List<TBusiExamVO> searchLastPageByStaff(Map<String, Object> pageParam);

    /**
     * 根据学员id查询最后一批试卷的所有人员成绩
     * @param studentid
     * @return
     */
    List<TBusiExamVO> searchBatchByStaff(@Param("studentid") String studentid);

    /**
     * 查询成绩总数
     * @param pageParam
     * @return
     */
    Integer searchTotalByTeacher(Map<String, Object> pageParam);

    /**
     * 分页查询学生成绩
     * @param pageParam
     * @return
     */
    List<TBusiExamCopyVO> searchPageByTeacher(Map<String, Object> pageParam);

    /**
     * 根据学员分页查询试卷列表
     * @param pageParam
     * @return
     */
    List<TBusiExamCopyVO> searchExamByStudent(Map<String, Object> pageParam);
}
