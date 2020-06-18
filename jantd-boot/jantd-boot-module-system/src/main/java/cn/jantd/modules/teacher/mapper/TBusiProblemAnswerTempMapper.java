package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TBusiExamProblemVO;
import cn.jantd.modules.teacher.VO.TBusiProblemAnswerVO;
import cn.jantd.modules.teacher.entity.TBusiExamProblemTemp;
import cn.jantd.modules.teacher.entity.TBusiProblemAnswerTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  答案表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Repository
public interface TBusiProblemAnswerTempMapper extends BaseMapper<TBusiProblemAnswerTemp>  {

    /**
     * 根据题目id查询答案
     * @param problemId
     * @return List
     */
    public List<TBusiProblemAnswerVO> searchAnswerByProblem(@Param("problemId") String problemId);

    /**
     * 根据试卷id查询答案列表
     * @param examid
     * @return List
     */
    List<TBusiProblemAnswerVO> searchByExam(@Param("examid") String examid);
}
