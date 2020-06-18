package cn.jantd.modules.teacher.service.impl;

import cn.jantd.modules.teacher.VO.TBusiExamStaffVO;
import cn.jantd.modules.teacher.entity.TBusiExamTemp;
import cn.jantd.modules.teacher.entity.TRelExamProblemTemp;
import cn.jantd.modules.teacher.mapper.TBusiExamTempMapper;
import cn.jantd.modules.teacher.mapper.TRelExamProblemTempMapper;
import cn.jantd.modules.teacher.mapper.TSysStaffMapper;
import cn.jantd.modules.teacher.service.ITBusiExamService;
import cn.jantd.modules.teacher.service.ITRelExamProblemTempService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 试卷题目关联表 服务实现类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Service
@Slf4j
public class TRelExamProblemTempServiceImpl extends ServiceImpl<TRelExamProblemTempMapper, TRelExamProblemTemp> implements ITRelExamProblemTempService {

    @Autowired
    private TRelExamProblemTempMapper examProblemMapper;




}
