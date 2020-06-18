package cn.jantd.modules.teacher.service.impl;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.VO.TBusiExamCopyVO;
import cn.jantd.modules.teacher.VO.TBusiExamVO;
import cn.jantd.modules.teacher.VO.TBusiStudyTempVO;
import cn.jantd.modules.teacher.entity.TBusiExamCopy;
import cn.jantd.modules.teacher.entity.TRelExamProblemTemp;
import cn.jantd.modules.teacher.mapper.TBusiExamCopyMapper;
import cn.jantd.modules.teacher.mapper.TRelExamProblemTempMapper;
import cn.jantd.modules.teacher.service.ITBusiExamCopyService;
import cn.jantd.modules.teacher.service.ITRelExamProblemTempService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试卷复制表 服务实现类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Service
@Slf4j
public class TBusiExamCopyServiceImpl extends ServiceImpl<TBusiExamCopyMapper, TBusiExamCopy> implements ITBusiExamCopyService {

    @Autowired
    private TBusiExamCopyMapper examCopyMapper;


    @Override
    public PersonnelResult searchPage(Map<String, Object> pageParam) {
        PersonnelResult result = new PersonnelResult();
        Integer total = examCopyMapper.searchTotalByTeacher(pageParam);
        result.setTotal(total);
        //pageStart  mysql用
        int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        pageParam.put("pageStart",pageStart);
        List<TBusiExamCopyVO> voList = examCopyMapper.searchPageByTeacher(pageParam);
        for (TBusiExamCopyVO vo: voList) {
            if(vo.getScoresCount() != null && vo.getScoresCount() >= 60){
                vo.setScoresStr("通过（"+vo.getScoresCount()+"）");
            }else if(vo.getScoresCount() != null && vo.getScoresCount() < 60 && vo.getScoresCount() > 0){
                vo.setScoresStr("不通过（"+vo.getScoresCount()+"）");
            }else{
                vo.setScoresStr("未答题");
            }
        }
        result.setPersonnelList(voList);
        return result;
    }

    @Override
    public Map<String, Object> searchExamByStudent(Map<String, Object> pageParam) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //Integer total = examCopyMapper.searchPageTotal(pageParam);
        //pageStart  mysql用
        //int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        //pageParam.put("pageStart",pageStart);
        List<TBusiExamCopyVO> voList = examCopyMapper.searchExamByStudent(pageParam);
        resultMap.putAll(pageParam);
        /*if(voList!=null && voList.size()>0){
            resultMap.put("total", total);
            long pages = total / Long.parseLong(pageParam.get("pageSize").toString());
            if (total % Long.parseLong(pageParam.get("pageSize").toString()) != 0) {
                pages++;
            }
            resultMap.put("pages", pages);
        }else{
            resultMap.put("pages", 0);
            resultMap.put("total", 0);
        }*/
        resultMap.put("records", voList);
        return resultMap;
    }
}
