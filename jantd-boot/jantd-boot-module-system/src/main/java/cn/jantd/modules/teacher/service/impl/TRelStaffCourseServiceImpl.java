package cn.jantd.modules.teacher.service.impl;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.VO.TBusiStudyCourseTempVO;
import cn.jantd.modules.teacher.VO.TBusiStudyTempVO;
import cn.jantd.modules.teacher.VO.TRelStaffCourseVO;
import cn.jantd.modules.teacher.entity.TRelStaffCourse;
import cn.jantd.modules.teacher.mapper.TBusiStudyTempMapper;
import cn.jantd.modules.teacher.mapper.TRelStaffCourseMapper;
import cn.jantd.modules.teacher.service.ITRelStaffCourseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学员课程关联表 服务实现类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Service
@Slf4j
public class TRelStaffCourseServiceImpl extends ServiceImpl<TRelStaffCourseMapper, TRelStaffCourse> implements ITRelStaffCourseService {

    @Autowired
    private TRelStaffCourseMapper scMapper;
    @Autowired
    private TBusiStudyTempMapper studyTempMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean configCourse(String studentid, String ids) {
        boolean flag = true;
        try {
            //删除原有关联
            LambdaQueryWrapper<TRelStaffCourse> query = new LambdaQueryWrapper<>();
            query.eq(TRelStaffCourse::getStaffId, studentid);
            this.remove(query);
            //添加新关联
            String[] cs = ids.split(",");
            for (int i=0;i<cs.length;i++) {
                TRelStaffCourse st = new TRelStaffCourse();
                st.setStaffId(studentid);
                st.setCourseId(cs[i]);
                st.setCourseOrder(i+1);
                st.setCourseStatus("0");
                st.setCreateBy("73");
                st.setCreateTime(new Date());
                st.setUpdateBy("73");
                st.setUpdateTime(new Date());
                this.save(st);
            }
        } catch (Exception e){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean configDelete(String studentid, String courseid) {
        boolean ok = true;
        try{
            LambdaQueryWrapper<TRelStaffCourse> query = new LambdaQueryWrapper<>();
            query.eq(TRelStaffCourse::getStaffId, studentid);
            query.eq(TRelStaffCourse::getCourseId, courseid);
            this.remove(query);
        } catch (Exception e){
            ok = false;
        }
        return ok;
    }

    @Override
    public PersonnelResult searchPage(Map<String, Object> pageParam) {
        PersonnelResult result = new PersonnelResult();
        Integer total = scMapper.searchPageTotal(pageParam);
        result.setTotal(total);
        //pageStart  mysql用
        int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        pageParam.put("pageStart",pageStart);
        //以项目为单位分组查询
        List<TBusiStudyTempVO> voList = scMapper.searchPageForMysql(pageParam);
        for (TBusiStudyTempVO vo : voList) {
            int percentage = vo.getCourseCompleteNum()/vo.getCourseNum() * 100;
            vo.setPercentage(percentage + "%");
        }
        result.setPersonnelList(voList);
        return result;
    }

    @Override
    public PersonnelResult searchStaffPageByStudy(Map<String, Object> pageParam) {
        PersonnelResult result = new PersonnelResult();
        Integer total = scMapper.searchStaffTotal(pageParam);
        result.setTotal(total);
        //pageStart  mysql用
        int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        pageParam.put("pageStart",pageStart);
        //以人员为单位分组查询返回关联表结果
        List<TRelStaffCourseVO> voList = scMapper.searchStaffPageByStudy(pageParam);
        for (TRelStaffCourseVO vo : voList) {
            int percentage = vo.getCourseCompleteNum()/vo.getCourseNum() * 100;
            vo.setPercentage(percentage + "%");
        }
        result.setPersonnelList(voList);
        return result;
    }

    @Override
    public Map<String, Object> searchPageByStudent(Map<String, Object> pageParam) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //先去掉分页
        //Integer total = scMapper.searchStudyTotalByStudent(pageParam);
        //pageStart  mysql用
        //int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        //pageParam.put("pageStart",pageStart);
        List<TBusiStudyTempVO> voList = scMapper.searchStudyByStudent(pageParam);
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
