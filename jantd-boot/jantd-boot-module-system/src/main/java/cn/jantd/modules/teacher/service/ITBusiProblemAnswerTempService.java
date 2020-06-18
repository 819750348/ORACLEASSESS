package cn.jantd.modules.teacher.service;

import cn.jantd.modules.teacher.VO.TBusiProblemAnswerVO;
import cn.jantd.modules.teacher.entity.TBusiExamProblemTemp;
import cn.jantd.modules.teacher.entity.TBusiProblemAnswerTemp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  答案表 服务类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
public interface ITBusiProblemAnswerTempService extends IService<TBusiProblemAnswerTemp> {

    /**
     * 根据题目id查询答案
     */
    List<TBusiProblemAnswerVO> searchAnswerByProblem(String problemId);

}