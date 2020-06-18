package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TBusiExamProblemVO;
import cn.jantd.modules.teacher.VO.TSysStaffVO;
import cn.jantd.modules.teacher.entity.TBusiExamProblemTemp;
import cn.jantd.modules.teacher.entity.TBusiExamTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  题目表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Repository
public interface TBusiExamProblemTempMapper extends BaseMapper<TBusiExamProblemTemp>  {

    /**
     * 分页列表
     * @param param 页面分页参数,数字型(pageSize,pageNo),其他参数,字符型(包括排序等)
     * @return List
     */
    public List<TBusiExamProblemVO> searchProblemPage(Map<String, Object> param);

    /**
     * 分页列表mysql
     * @param param 页面分页参数,数字型(pageSize,pageNo),其他参数,字符型(包括排序等)
     * @return List
     */
    public List<TBusiExamProblemVO> searchProblemPageForMysql(Map<String, Object> param);

    /**
     * 总数
     * @param param 参数,字符型(查询条件)
     * @return List
     */
    public Integer searchProblemTotal(Map<String, Object> param);

    /**
     * 唯一性验证
     * @param title
     * @return List
     */
    public List<TBusiExamProblemTemp> checkProblem(@Param("title") String title);

    /**
     * 根据类型查询所有题目
     * @param problemType
     * @return List
     */
    List<TBusiExamProblemVO> searchProblemByType(@Param("problemType") String problemType);

    /**
     * 根据试卷id查题目列表
     * @param examid
     * @return List
     */
    List<TBusiExamProblemVO> searchByExam(@Param("examid") String examid);

    /**
     * 验证是否在试卷中引用该题目
     * @param ids
     * @return boolean
     */
    List<TBusiExamProblemVO> checkRelExamProblem(List<String> ids);
}
