package cn.jantd.modules.teacher.service;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.VO.TBusiExamVO;
import cn.jantd.modules.teacher.entity.TBusiExamCopy;
import cn.jantd.modules.teacher.entity.TRelExamProblemTemp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  试卷复制表 服务类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-05
 */
public interface ITBusiExamCopyService extends IService<TBusiExamCopy> {

    /**
     * 分页查询下发学员成绩
     * @param pageParam
     * @return
     */
    PersonnelResult searchPage(Map<String, Object> pageParam);

    /**
     * 根据学员分页查询试卷列表
     * @param pageParam
     * @return
     */
    Map<String, Object> searchExamByStudent(Map<String, Object> pageParam);
    
}