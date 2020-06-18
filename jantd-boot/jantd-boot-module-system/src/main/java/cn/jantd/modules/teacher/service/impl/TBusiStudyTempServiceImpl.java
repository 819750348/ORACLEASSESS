package cn.jantd.modules.teacher.service.impl;

import cn.jantd.modules.system.service.ISysDictItemService;
import cn.jantd.modules.teacher.VO.TBusiStudyTempVO;
import cn.jantd.modules.teacher.entity.TBusiStudyCourseTemp;
import cn.jantd.modules.teacher.entity.TBusiStudyTemp;
import cn.jantd.modules.teacher.mapper.TBusiStudyCourseTempMapper;
import cn.jantd.modules.teacher.mapper.TBusiStudyTempMapper;
import cn.jantd.modules.teacher.service.ITBusiStudyTempService;
import cn.jantd.modules.teacher.util.DictUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学习和训练项目表 服务实现类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Service
@Slf4j
public class TBusiStudyTempServiceImpl extends ServiceImpl<TBusiStudyTempMapper, TBusiStudyTemp> implements ITBusiStudyTempService {

    @Autowired
    private TBusiStudyTempMapper studyTempMapper;
    @Autowired
    private TBusiStudyCourseTempMapper studyCourseTempMapper;


    @Override
    public Map<String, Object> searchPage(Map<String, Object> pageParam) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer total = studyTempMapper.searchPageTotal(pageParam);
        //pageStart  mysql用
        int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        pageParam.put("pageStart",pageStart);
        List<TBusiStudyTempVO> voList = studyTempMapper.searchPageForMysql(pageParam);
        if(voList!=null && voList.size()>0){
            resultMap.putAll(pageParam);
            resultMap.put("total", total);
            long pages = total / Long.parseLong(pageParam.get("pageSize").toString());
            if (total % Long.parseLong(pageParam.get("pageSize").toString()) != 0) {
                pages++;
            }
            resultMap.put("pages", pages);
        }else{
            resultMap.put("pages", 0);
            resultMap.put("total", 0);
        }
        resultMap.put("records", voList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getStudySelect(Map<String, Object> pageParam) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //查询教学管理员已添加的项目
        LambdaQueryWrapper<TBusiStudyCourseTemp> query = new LambdaQueryWrapper<>();
        query.eq(TBusiStudyCourseTemp::getTeacherId,pageParam.get("teacherId"));
        List<TBusiStudyCourseTemp> list = studyCourseTempMapper.selectList(query);
        //只查询没有添加过的项目
        List<String> studyList = new ArrayList<String>();
        for (TBusiStudyCourseTemp temp : list) {
            studyList.add(temp.getStudyId());
        }
        LambdaQueryWrapper<TBusiStudyTemp> studyQuery = new LambdaQueryWrapper<>();
        studyQuery.notIn(TBusiStudyTemp::getId,studyList);
        List<TBusiStudyTemp> voList = this.list(studyQuery);
        if(voList != null && voList.size() > 0){
            resultMap.put("records", voList);
            return resultMap;
        }else{
            return null;
        }
    }
}
