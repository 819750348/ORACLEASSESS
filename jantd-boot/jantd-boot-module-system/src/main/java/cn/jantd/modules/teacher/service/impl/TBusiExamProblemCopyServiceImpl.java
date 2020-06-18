package cn.jantd.modules.teacher.service.impl;

import cn.jantd.modules.teacher.entity.TBusiExamCopy;
import cn.jantd.modules.teacher.entity.TBusiExamProblemCopy;
import cn.jantd.modules.teacher.mapper.TBusiExamCopyMapper;
import cn.jantd.modules.teacher.mapper.TBusiExamProblemCopyMapper;
import cn.jantd.modules.teacher.service.ITBusiExamCopyService;
import cn.jantd.modules.teacher.service.ITBusiExamProblemCopyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目复制表 服务实现类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Service
@Slf4j
public class TBusiExamProblemCopyServiceImpl extends ServiceImpl<TBusiExamProblemCopyMapper, TBusiExamProblemCopy> implements ITBusiExamProblemCopyService {

    @Autowired
    private TBusiExamProblemCopyMapper problemCopyMapper;




}
