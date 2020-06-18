package cn.jantd.modules.teacher.service.impl;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.VO.TBusiStudyCourseTempVO;
import cn.jantd.modules.teacher.entity.*;
import cn.jantd.modules.teacher.mapper.TBusiStudyCourseTempMapper;
import cn.jantd.modules.teacher.mapper.TBusiStudyTempMapper;
import cn.jantd.modules.teacher.mapper.TSysStaffMapper;
import cn.jantd.modules.teacher.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 学习和训练计划表 服务实现类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Service
@Slf4j
public class TBusiStudyCourseTempServiceImpl extends ServiceImpl<TBusiStudyCourseTempMapper, TBusiStudyCourseTemp> implements ITBusiStudyCourseTempService {

    @Autowired
    private TBusiStudyCourseTempMapper studyCourseTempMapper;
    @Autowired
    private TSysStaffMapper staffMapper;
    @Autowired
    private TBusiStudyTempMapper studyTempMapper;
    @Autowired
    private ITBusiExamService examService;
    @Autowired
    private ITRelStaffCourseService staffCourseService;


    @Override
    public PersonnelResult searchPage(Map<String, Object> pageParam) {
        PersonnelResult result = new PersonnelResult();
        Integer total = studyCourseTempMapper.searchPageTotal(pageParam);
        result.setTotal(total);
        //pageStart  mysql用
        int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        pageParam.put("pageStart",pageStart);
        List<TBusiStudyCourseTempVO> voList = studyCourseTempMapper.searchPageForMysql(pageParam);
        result.setPersonnelList(voList);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeIdsAndRel(String ids) {
        Boolean flag = false;
        try{
            //查询所有非选中的项目
            LambdaQueryWrapper<TBusiStudyCourseTemp> query = new LambdaQueryWrapper<>();
            query.notIn(TBusiStudyCourseTemp::getId,Arrays.asList(ids.split(",")));
            query.eq(TBusiStudyCourseTemp::getTeacherId,"73"); //登录用户id
            List<TBusiStudyCourseTemp> list = this.list(query);
            //循环找到所有岗位
            List<String> epList = new ArrayList<String>();
            for (TBusiStudyCourseTemp object : list) {
                String[] str = object.getEquipPosition().split(",");
                for (String a:str) {
                    if(!epList.contains(a)){
                        epList.add(a);
                    }
                }
            }
            //删除所有没找到的岗位对应的试卷
            LambdaQueryWrapper<TBusiExamTemp> examQuery = new LambdaQueryWrapper<>();
            examQuery.notIn(TBusiExamTemp::getEquipPosition,epList);
            examQuery.eq(TBusiExamTemp::getStaffId,"73"); //登录用户id
            examService.remove(examQuery);
            //删除所有计划
            this.removeByIds(Arrays.asList(ids.split(",")));
            flag = true;
        }catch (Exception e){
            flag = false;
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveStudy(TBusiStudyCourseTemp study) {
        //添加装备岗位
        TBusiStudyTemp st = studyTempMapper.selectById(study.getStudyId());
        study.setEquipPosition(st.getEquipPosition());
        study.setStudyType(st.getStudyType());
        // 设置创建时间
        study.setCreateTime(new Date());
        study.setCreateBy("73");
        study.setDelFlag("0");
        boolean flag = false;
        try{
            this.save(study);
            flag = true;
        }catch (Exception e){
            flag = false;
        }
        return flag;
    }

    @Override
    public Boolean toStudents(String teacherId) {
        System.out.println("1:                     "+teacherId);
        boolean flag = false;
        try {
            //查询所有训练计划(实际每个计划对应一个训练项目),找出所有包含的岗位
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("teacherId",teacherId);
            List<TBusiStudyCourseTemp> sclist = studyCourseTempMapper.searchByTeacher(map);
            //找出所有包含的岗位,改变计划状态为已推送
            List<String> epList = new ArrayList<String>();
            for (TBusiStudyCourseTemp vo : sclist) {
                System.out.println("2:                     "+vo.getEquipPosition());
                String[] voep = vo.getEquipPosition().split(",");
                for (String ep:voep) {
                    if(!epList.contains(ep)){
                        epList.add(ep);
                    }
                }
                //修改计划状态为已推送，再次点击将不会推送已推送的数据
                vo.setScStatus("1");
                this.saveOrUpdate(vo);
            }
            //所有岗位数组
            String[] strings = new String[epList.size()];
            epList.toArray(strings);
            if(strings == null || strings.length == 0){
                strings = null;
            }
            //查询所有岗位对应的学员
            List<TSysStaff> stafflist = staffMapper.getSutdentByPosition(strings);
            //添加关联表
            List<TRelStaffCourse> list = new ArrayList<TRelStaffCourse>();
            //循环添加关联表，注意循环顺序,先人员，再计划项目
            for (int i=0;i<stafflist.size();i++) {
                TSysStaff staff = stafflist.get(i);
                for (int j=0;j<sclist.size();j++) {
                    TBusiStudyCourseTemp bsc = sclist.get(j);
                    //循环人的岗位和课的岗位进行匹配
                    String[] bscEps = bsc.getEquipPosition().split(",");
                    String[] staffEps = staff.getEquipPosition().split(",");
                    boolean splitflag = false;
                    split: for (String bscep : bscEps) {
                        for (String staffep : staffEps) {
                            if(bscep.equals(staffep)){
                                splitflag = true;
                                break split;
                            }
                        }
                    }
                    if(splitflag){
                        //添加实体
                        TRelStaffCourse sc = new TRelStaffCourse();
                        sc.setStaffId(staff.getId());
                        //sc.setCourseId(bc.getId());//没有课程了，用项目和计划取代
                        sc.setStudyId(bsc.getStudyId()); //项目id
                        sc.setStudyCourseId(bsc.getId()); //计划id
                        sc.setTeacherId(teacherId);
                        sc.setCourseOrder((j+1));
                        sc.setCourseStatus("0");
                        sc.setDelFlag("0");
                        sc.setCreateBy("73");//创建人名，session中取
                        sc.setCreateTime(new Date());
                        list.add(sc);
                    }
                }
            }
            staffCourseService.saveBatch(list);
            flag = true;
        }catch (Exception e){
            flag = false;
        }
        return flag;
    }
}
