package cn.jantd.modules.teacher.service.impl;

import cn.jantd.modules.teacher.VO.TBusiProblemAnswerVO;
import cn.jantd.modules.teacher.entity.TBusiExamProblemTemp;
import cn.jantd.modules.teacher.entity.TBusiProblemAnswerTemp;
import cn.jantd.modules.teacher.mapper.TBusiExamProblemTempMapper;
import cn.jantd.modules.teacher.mapper.TBusiProblemAnswerTempMapper;
import cn.jantd.modules.teacher.service.ITBusiExamProblemTempService;
import cn.jantd.modules.teacher.service.ITBusiProblemAnswerTempService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 答案表 服务实现类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Service
@Slf4j
public class TBusiProbjemAnswerTempServiceImpl extends ServiceImpl<TBusiProblemAnswerTempMapper, TBusiProblemAnswerTemp> implements ITBusiProblemAnswerTempService {

    @Autowired
    private TBusiProblemAnswerTempMapper answerTempMapper;

    @Override
    public List<TBusiProblemAnswerVO> searchAnswerByProblem(String problemId) {
        return answerTempMapper.searchAnswerByProblem(problemId);
    }
}
