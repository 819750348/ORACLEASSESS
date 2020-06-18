package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TBusiProblemAnswerVO;
import cn.jantd.modules.teacher.entity.TBusiExamCopy;
import cn.jantd.modules.teacher.entity.TBusiProblemAnswerCopy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  答案复制表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Repository
public interface TBusiProblemAnswerCopyMapper extends BaseMapper<TBusiProblemAnswerCopy>  {


    List<TBusiProblemAnswerVO> searchByExam(@Param("examid") String examid);
}
