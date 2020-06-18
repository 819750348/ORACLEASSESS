package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TBusiExamProblemVO;
import cn.jantd.modules.teacher.entity.TBusiExamProblemTemp;
import cn.jantd.modules.teacher.entity.TRelExamProblemTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  试卷题目关联表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Repository
public interface TRelExamProblemTempMapper extends BaseMapper<TRelExamProblemTemp>  {


}
