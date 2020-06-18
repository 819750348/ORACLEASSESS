package cn.jantd.modules.teacher.service.impl;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.VO.*;
import cn.jantd.modules.teacher.entity.*;
import cn.jantd.modules.teacher.mapper.*;
import cn.jantd.modules.teacher.service.*;
import cn.jantd.modules.teacher.util.DictUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 试卷表 服务实现类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Service
@Slf4j
public class TBusiExamServiceImpl extends ServiceImpl<TBusiExamTempMapper, TBusiExamTemp> implements ITBusiExamService {

    @Autowired
    private TBusiStudyCourseTempMapper studyCourseTempMapper;
    @Autowired
    private TSysStaffMapper staffMapper;
    @Autowired
    private TBusiExamCopyMapper examCopyMapper;
    @Autowired
    private TBusiExamTempMapper examMapper;
    @Autowired
    private ITBusiExamCopyService examCopyService;
    @Autowired
    private TBusiExamProblemCopyMapper problemCopyMapper;
    @Autowired
    private TBusiExamProblemTempMapper problemMapper;
    @Autowired
    private ITBusiExamProblemCopyService problemCopyService;
    @Autowired
    private TBusiProblemAnswerCopyMapper answerCopyMapper;
    @Autowired
    private TBusiProblemAnswerTempMapper answerMapper;
    @Autowired
    private ITRelExamProblemTempService examProblemTempService;
    @Autowired
    private ITRelExamStaffService examStaffService;


    private final String[] PROBLEM_ORDER = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};



    @Override
    public PersonnelResult searchExamPage(Map<String, Object> pageParam) {
        PersonnelResult result = new PersonnelResult();
        Integer total = examMapper.searchExamTotal(pageParam);
        result.setTotal(total);
        //pageStart  mysql用
        int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        pageParam.put("pageStart",pageStart);
        List<TBusiExamVO> voList = examMapper.searchExamPageForMysql(pageParam);
        result.setPersonnelList(voList);
        return result;
    }

    @Override
    public List<TBusiExamStaffVO> getStaffById(String id) {
        List<TBusiExamStaffVO> esList = new ArrayList<TBusiExamStaffVO>();
        //假数据
        String[] str = {"邓力夫,邹...","徐牧,雷...","罗秋蒙,董...","张骁,邓力...","雷虹宇,段..."};
        String[] sss = {"操作手模拟考","雷达车指挥试卷","操作指控车测试","指控车维护","断电步骤"};
        for (int i=1;i<=5;i++){
            TBusiExamStaffVO vo = new TBusiExamStaffVO();
            vo.setId(i+"");
            vo.setName(sss[i-1]);
            vo.setStudents(str[i-1]);
            vo.setSendStatusStr("已完成");
            vo.setTotalScores(100);
            esList.add(vo);
        }
        //注掉正式代码
        /*List<TBusiExamTemp> examList = tBusiExamTempMapper.getExamByStaffid(id);
        List<TSysStaffVO> staffList = tsysStaffMapper.getStaffbyTeacherid(id);
        for (TBusiExamStaffVO vo : esList){
            List<TSysStaffVO> studentList = vo.getSutdentList();
            StringBuffer students = new StringBuffer();
            String sendFlag = "已发送";
            for (TSysStaffVO staff : staffList){
                if(staff.getExamId().equals(vo.getId())){
                    studentList.add(staff);
                    students.append(staff.getName() + ",");
                    if (staff.getSend_status().equals("0")) { // 只判断未发送的
                        sendFlag = "未发送";
                    }
                }
            }
            vo.setSutdentList(studentList);
            vo.setSendStatusStr(if(students.length()>5):students.toString().substring(0,5-1)+"..."?students.toString().substring(0,students.toString().length()-1));
            vo.setSendStatusStr(sendFlag);
        }*/
        return esList;
    }

    @Override
    public List<TBusiExamVO> queryLastExamByStudent(String id) {
        List<TBusiExamVO> examList = examCopyMapper.queryLastExamByStudent(id);
        if(examList!=null && examList.size()>0){
            /*List<SysDictItemVO> dilist = sysDictItemService.queryEPSG();
            for (TBusiExamVO vo : examList) {
                vo = DictUtil.examDictToStr(vo, dilist);
            }*/
        }
        return examList;
    }

    @Override
    public JSONObject detailExamByStaff(String examid, String staffid) {
        JSONObject exam = new JSONObject();
        JSONArray examProblemTypeArray = new JSONArray();
        TBusiExamVO examVO = examCopyMapper.examDetail(examid, staffid);
        exam.put("examTitle",examVO.getName());//试卷标题
        exam.put("examTime",examVO.getExamTime());//答题时间
        List<TBusiExamProblemVO> problemList = problemCopyMapper.searchByExam(examid);
        List<TBusiProblemAnswerVO> answerList = answerCopyMapper.searchByExam(examid);
        if(problemList != null && problemList.size() > 0){
            JSONObject problemTypeObject = new JSONObject();
            JSONArray pArray = new JSONArray();
            int typeNum = 0;
            String typeStr = "";
            for (TBusiExamProblemVO p : problemList) {
                String problemType = "";
                if(p.getProblemType().equals("单选")){
                    problemType = "单选题";
                }else if(p.getProblemType().equals("复选")){
                    problemType = "复选题";
                }else if(p.getProblemType().equals("判断")){
                    problemType = "判断题";
                }else{
                    //没有题型字段说明数据有错误,返回null
                    return null;
                }
                JSONObject po = new JSONObject();
                JSONArray ansArray = new JSONArray();
                po.put("problemTitle",p.getProblemTitle());//题目标题
                po.put("studentAnswer",p.getProblemAnswer());//学生答案
                po.put("problemType",problemType);//题型
                po.put("problemOrder",p.getProblemOrder());//题目序号
                //正确答案需要通过答案表循环获取
                StringBuffer correct = new StringBuffer("");
                if(answerList != null && answerList.size() > 0){
                    for (int i=answerList.size()-1;i>=0;i--) {
                        TBusiProblemAnswerVO ans = answerList.get(i);
                        if(ans.getProblemId().equals(p.getId())){
                            JSONObject ansObject = new JSONObject();
                            String ansOrderStr = this.PROBLEM_ORDER[ans.getAnswerOrder()-1];
                            String ansStr = ansOrderStr + ": " + ans.getAnswerStr();
                            if(ans.getIsRight().equals("1")){
                                correct.append(ansOrderStr);
                            }
                            ansObject.put("answerOrder",ansOrderStr);//正确答案序号(已由数字转换成ABC)
                            ansObject.put("ansStr",ansStr);//答案文本(已加上序号ABC)
                            ansArray.add(ansObject);
                            answerList.remove(ans);
                        }
                    }
                    po.put("correct",correct);//正确答案
                    po.put("isRight", (correct.toString().equals(p.getProblemAnswer()))?"1":"0");//学生答案是否正确
                }else{
                    //如果查不到答案说明数据有错误,返回null
                    return null;
                }
                if(problemType.equals(typeStr)){
                    typeNum++;
                    pArray.add(po);
                }else{
                    problemTypeObject.put("num",typeNum);//本题型题目数量
                    problemTypeObject.put("type",typeStr);//题型名称
                    problemTypeObject.put("problemList",pArray);//本题型题目集合
                    examProblemTypeArray.add(problemTypeObject);
                    //同一类型题目存储在集中后清空四个值
                    pArray.clear();
                    typeNum = 1;
                    typeStr = problemType;
                    problemTypeObject.clear();
                    pArray.add(po);
                }
            }
        }else{
            //如果查不到题目说明数据有错误,返回null
            return null;
        }
        exam.put("problemType",examProblemTypeArray);//题型集合
        return exam;
    }

    @Override
    public JSONObject toEditByExamAndStaff(String examid, String staffid) {
        JSONObject exam = new JSONObject();
        JSONArray problemArray = new JSONArray();
        TBusiExamVO examVO = examCopyMapper.examDetail(examid, staffid);
        exam.put("examTitle",examVO.getName());//试卷标题
        exam.put("examTime",examVO.getTiming());//答题计时(总分钟数)
        List<TBusiExamProblemVO> problemList = problemCopyMapper.searchByExam(examid);
        //List<TBusiProblemAnswerVO> answerList = answerCopyMapper.searchByExam(examid);
        if(problemList != null && problemList.size() > 0){
            exam.put("problemNum",problemList.size());//总题目数量
            for (TBusiExamProblemVO p : problemList) {
                String problemType = "";
                if(p.getProblemType().equals("单选") || p.getProblemType().equals("1")){
                    problemType = "单选题";
                }else if(p.getProblemType().equals("复选") || p.getProblemType().equals("2")){
                    problemType = "复选题";
                }else if(p.getProblemType().equals("判断") || p.getProblemType().equals("3")){
                    problemType = "判断题";
                }else{
                    //没有题型字段说明数据有错误,返回null
                    return null;
                }
                JSONObject problemObject = new JSONObject();
                JSONArray ansArray = new JSONArray();
                problemObject.put("problemTitle",p.getProblemTitle());//题目标题
                problemObject.put("problemOrder",p.getProblemOrder());//题目序号
                problemObject.put("problemTypeStr",problemType);//题型Str
                problemObject.put("problemType",p.getProblemType());//题型
                ansArray = JSONArray.parseArray(p.getAnswerStr());
                StringBuffer correct = new StringBuffer("");
                problemObject.put("correct",p.getSuccessAnswer());//正确答案
                problemObject.put("answerStr",ansArray);//答案字符串
                problemArray.add(problemObject);
            }
            exam.put("problem",problemArray);
        }else{
            //如果查不到题目说明数据有错误,返回null
            return null;
        }
        return exam;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> editExamScores(JSONObject paper) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            JSONArray data = (JSONArray) paper.get("data");
            String examid = String.valueOf(paper.get("examid"));
            //String staffid = (String) paper.get("staffid");
            TBusiExamCopy examCopy = examCopyService.getById(examid);
            examCopy.setExamTime(new Date());
            int scoresCount = 0;
            for(int i=0;i<data.size();i++) {
                System.out.println(data.getJSONObject(i).get("problemid"));
                TBusiExamProblemCopy problemCopy = problemCopyService.getById(String.valueOf(data.getJSONObject(i).get("problemid")));
                problemCopy.setProblemAnswer(String.valueOf(data.getJSONObject(i).get("stuKey")));
                if(String.valueOf(data.getJSONObject(i).get("stuKey")).equals(String.valueOf(data.getJSONObject(i).get("rightKey")))){
                    problemCopy.setScores(problemCopy.getScoresCount());
                    scoresCount += problemCopy.getScoresCount();
                }else{
                    problemCopy.setScores(0);
                }
                problemCopyService.updateById(problemCopy);
            }
            examCopy.setScoresCount(scoresCount);
            examCopyService.updateById(examCopy);
            returnMap.put("scoresCount",scoresCount);
        } catch (Exception e) {
            //报错直接返回null
            returnMap = null;
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> searchLastByStaff(Map<String, Object> pageParam) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try{
            long total = (long) examCopyMapper.searchTotalByStaff(pageParam);
            List<TBusiExamVO> voList = examCopyMapper.searchLastPageByStaff(pageParam);
            if(voList!=null && voList.size()>0){
                /*List<SysDictItemVO> dilist = sysDictItemService.queryEPSG();
                for (TBusiExamVO vo : voList) {
                    vo = DictUtil.examDictToStr(vo, dilist);
                }*/
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
        }catch (Exception e){
            return null;
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> searchBatchByStaff(String studentid) {
        //成绩倒序查询同一批次的试卷，查询下标获取排名,如果有问题也可以用成绩冒泡排序
        List<TBusiExamVO> examList = examCopyMapper.searchBatchByStaff(studentid);
        if (examList != null && examList.size() > 0) {
            int top = 1;
            int scores = 0;
            for (TBusiExamVO vo : examList) {
                if (studentid.equals(vo.getStaffId())) {
                    scores = vo.getScoresCount();
                    break;
                }
                top++;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("top",top);
            map.put("scores",scores);
            return map;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> createExam(String teacherId){
        String batchId = UUID.randomUUID().toString().replace("-", "");
        boolean flag = true;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<JSONObject> olist = new ArrayList<JSONObject>();
            //根据登录教学管理员id，获取所有的计划，并根据计划取到所有的岗位数组
            LambdaQueryWrapper<TBusiStudyCourseTemp> scQuery = new LambdaQueryWrapper<>();
            scQuery.eq(TBusiStudyCourseTemp::getTeacherId,String.valueOf(teacherId));
            List<TBusiStudyCourseTemp> sclist = studyCourseTempMapper.selectList(scQuery);
            Map<String,String> epMap = new HashMap<String,String>();
            // List<String> epList = new ArrayList<String>();
            for (TBusiStudyCourseTemp temp : sclist) {
                String[] epStr = temp.getEquipPosition().split(",");
                for (String ep : epStr) {
                    if(null == epMap.get(ep)){
                        //studyType学习类型1理论2操作
                        epMap.put(ep,temp.getStudyType());
                    }
                }
            }
            //每个岗位一个试卷，查询已经有的试卷，并且把已经创建过试卷的去掉
            LambdaQueryWrapper<TBusiExamTemp> examQuery = new LambdaQueryWrapper<>();
            examQuery.eq(TBusiExamTemp::getStaffId,String.valueOf(teacherId));
            List<TBusiExamTemp> examlist = this.list(examQuery);
            for (TBusiExamTemp temp : examlist) {
                set: for (String key : epMap.keySet()) {
                    if(temp.getEquipPosition().equals(key)){
                        epMap.remove(key);
                        break set;
                    }
                }
            }
            //循环没有试卷的岗位(key岗位 value类型1理论2操作)，添加试卷
            problemFor: for (String key : epMap.keySet()) {
                TBusiExamTemp exam = new TBusiExamTemp();
                int typeNum = 0;//共有几个类型
                //判断试卷类型
                if(String.valueOf(epMap.get(key)).equals("1")){
                    typeNum = 3;
                    exam.setExamType("1");
                }else{
                    typeNum = 4;
                    exam.setExamType("2");
                }
                //创建人员取session
                exam.setStaffId("73");
                //名称用岗位名+试卷
                exam.setName(DictUtil.getEquipPositionStr(key)+"-试卷");
                exam.setTotalScores(100);
                exam.setPassScores(60);
                exam.setTiming(120);
                exam.setBatchId(batchId);
                exam.setEquipPosition(key);
                for(int i = 1;i<=typeNum;i++){
                    Integer problemNum = i<4?10:4;//前三个类型的题是10道，操作题是4道
                    Integer pNum = 0;//已加题目数量
                    //Set<Integer> problemSet = new HashSet<Integer>();//已加题目放到set中方便去重
                    List<TBusiExamProblemVO> plist = problemMapper.searchProblemByType(String.valueOf(i));
                    System.out.println("a:   "+plist.size()+"        b:  "+problemNum);
                    if(plist != null && plist.size() >= problemNum){
                        while (pNum < problemNum){
                            System.out.println("fsf:           "+plist.size());
                            int problemTypeIndex = (int) (Math.random() * (plist.size() - 1));
                            TBusiExamProblemVO p = plist.get(problemTypeIndex);
                            //循环时存储题目id，类型
                            JSONObject jo = new JSONObject();
                            jo.put("problemId",p.getId());
                            jo.put("problemType",String.valueOf(i));
                            olist.add(jo);
                            plist.remove(problemTypeIndex);
                            pNum++;
                        }
                    }else{
                        flag = false;
                        map.put("message","题目可用数量不足");
                        break problemFor;
                    }
                }
                //总题目数
                exam.setProblemCount(olist.size());
                //存储试卷表
                this.save(exam);
                //循环存储试卷题目关联表,排序通过下标
                List<TRelExamProblemTemp> eptList = new ArrayList<TRelExamProblemTemp>();
                for (int i=1;i<=olist.size();i++){
                    JSONObject jo = olist.get(i-1);
                    TRelExamProblemTemp ept = new TRelExamProblemTemp();
                    if(typeNum == 3){
                        //判断类型给分数1单选40,2复选40,3判断20
                        if(String.valueOf(jo.get("problemId")).equals("1") || String.valueOf(jo.get("problemId")).equals("2")){
                            ept.setScoresCount(4);
                        }else{
                            ept.setScoresCount(2);
                        }
                    }else if(typeNum == 4){
                        //判断类型给分数1单选20,2复选20,3判断20,4操作40
                        if(String.valueOf(jo.get("problemId")).equals("1")
                                || String.valueOf(jo.get("problemId")).equals("2")
                                || String.valueOf(jo.get("problemId")).equals("3")){
                            ept.setScoresCount(2);
                        }else{
                            ept.setScoresCount(4);
                        }
                    }
                    ept.setExamId(exam.getId());
                    ept.setProblemId(String.valueOf(jo.get("problemId")));
                    ept.setProblemOrder(i);
                    eptList.add(ept);
                }
                examProblemTempService.saveBatch(eptList);
            }
            map.put("result",flag);
        } catch (Exception e){
            flag = false;
            map.put("result",flag);
            map.put("message","操作失败");
        }
        return map;
    }

    @Override
    public JSONObject detailExamById(String examid) {
        JSONObject examObject = new JSONObject();
        JSONArray examProblemTypeArray = new JSONArray();
        TBusiExamVO examVO = examMapper.examDetail(examid);
        examObject.put("examTitle",examVO.getName());//试卷标题
        examObject.put("timing",examVO.getTiming());//答题计时
        List<TBusiExamProblemVO> problemList = problemMapper.searchByExam(examid); //倒序查询，循环时每次删除数据
        Integer[] num = {10,10,10,4}; //各题型数量集合，下标对应题型减一
        Integer numIndex = 0;
        if(problemList != null && problemList.size() > 0){
            JSONObject problemTypeObject = new JSONObject();
            JSONArray pArray = new JSONArray();
            int typeNum = 1;
            String typeStr = "";
            //倒序循环时可以删除已用数据,优化查询效率
            for (int i=problemList.size()-1;i>=0;i--) {
                TBusiExamProblemVO p = problemList.get(i);
                String problemType = "";
                //因为生成试卷时各类型题目放在一起，所以不会出现各题型穿差出现的问题
                if(p.getProblemType().equals("1")){
                    problemType = "单选题";
                }else if(p.getProblemType().equals("2")){
                    problemType = "复选题";
                }else if(p.getProblemType().equals("3")){
                    problemType = "判断题";
                }else if(p.getProblemType().equals("4")){
                    problemType = "操作题";
                }else{
                    //没有题型字段说明数据有错误,返回null
                    return null;
                }
                JSONObject po = new JSONObject();
                po.put("title",p.getProblemTitle());//题目标题
                po.put("rightKey",p.getSuccessAnswer());//正确答案
                po.put("serialNumber",p.getProblemOrder());//题目序号
                JSONArray answer = JSON.parseArray(p.getAnswerStr());
                JSONArray currentAnswer = new JSONArray();
                for (Object ans : answer) {
                    JSONObject currentAns = (JSONObject) ans;
                    currentAnswer.add(currentAns.get("No")+": "+currentAns.get("answerStr"));
                }
                po.put("option",currentAnswer);

                if(typeNum < num[(Integer.parseInt(p.getProblemType())-1)]){
                    typeNum++;
                    pArray.add(po);
                }else{
                    pArray.add(po);
                    //题型名称
                    String title = new String();
                    if(p.getProblemType().equals("1")){
                        title = "一、"+problemType;
                    }else if(p.getProblemType().equals("2")){
                        title = "二、"+problemType;
                    }else if(p.getProblemType().equals("3")){
                        title = "三、"+problemType;
                    }else if(p.getProblemType().equals("4")){
                        title = "四、"+problemType;
                    }else{
                        //没有题型字段说明数据有错误,返回null
                        return null;
                    }

                    //每题分数
                    Integer problemScores = 0;
                    if(examVO.getExamType().equals("1")){ //理论试卷
                        if(p.getProblemType().equals("1") || p.getProblemType().equals("2")){
                            problemScores = 4;
                        }else{
                            problemScores = 2;
                        }
                    }else if(examVO.getExamType().equals("2")){ //操作试卷
                        if(p.getProblemType().equals("1") || p.getProblemType().equals("2") || p.getProblemType().equals("3")){
                            problemScores = 2;
                        }else{
                            problemScores = 4;
                        }
                    }
                    problemTypeObject.put("No",examVO.getExamType());
                    problemTypeObject.put("questionType",title);
                    problemTypeObject.put("fraction",String.valueOf(problemScores));
                    problemTypeObject.put("numbenOfQuestions",typeNum);//本题型题目数量
                    problemTypeObject.put("data",pArray);//本题型题目集合
                    examProblemTypeArray.add(problemTypeObject);
                    //同一类型题目存储在集中后清空四个值
                    pArray = new JSONArray();
                    typeNum = 1;
                    problemTypeObject = new JSONObject();
                }
            }
            examObject.put("paper",examProblemTypeArray);
            return examObject;
        }else{
            return null;
        }
    }

    @Override
    public Map<String, Object> queryPageList(Map<String, Object> pageParam) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //pageStart  mysql用
        int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        pageParam.put("pageStart",pageStart);
        long total = (long) examMapper.searchExamTotal(pageParam);
        List<TBusiExamVO> voList = examMapper.searchExamPageForMysql(pageParam);
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
    public Map<String, String> removeExamAndRelByIds(List<String> ids) {
        Map<String, String> map = new HashMap<String, String>();
        boolean ok = this.removeByIds(ids);
        if(ok){
            // 当批量删除时,删除在TRelExamProblemTemp中的关联数据
            for (String id : ids) {
                LambdaQueryWrapper<TRelExamProblemTemp> query = new LambdaQueryWrapper<>();
                query.eq(TRelExamProblemTemp::getExamId, id);
                boolean ook = examProblemTempService.remove(query);
                //System.out.println(ook);
            }
            map.put("success","true");
            map.put("msg","删除成功");
        }else{
            map.put("success","false");
            map.put("msg","删除失败");
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> sendExam(String examId,String teacherId) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //查询该试卷及关联的题目和答案
            TBusiExamTemp examTemp = this.getById(examId);
            String batchId = examTemp.getBatchId();
            List<TBusiExamProblemVO> problemList = problemMapper.searchByExam(examId);
            String[] epStr = examTemp.getEquipPosition().split(",");

            //查询所有岗位下的学员
            List<TSysStaff> stafflist = staffMapper.getSutdentByPosition(epStr);

            //查找已经有的试卷，匹配学员id，去掉已推送过的学员
            LambdaQueryWrapper<TBusiExamCopy> ecQuery = new LambdaQueryWrapper<>();
            ecQuery.eq(TBusiExamCopy::getSendBy,String.valueOf(teacherId));
            List<TBusiExamCopy> eclist = examCopyService.list(ecQuery);
            for (int i=stafflist.size()-1;i>=0;i--) {
                TSysStaff s = stafflist.get(i);
                se: for (TBusiExamCopy ec: eclist) {
                    if(s.getId().equals(ec.getStaffId())){
                        stafflist.remove(i);
                        break se;
                    }
                }
            }

            List<TRelExamStaff> eslist = new ArrayList<TRelExamStaff>();
            List<TBusiExamProblemCopy> pclist = new ArrayList<TBusiExamProblemCopy>();
            //循环人员
            for (TSysStaff staff: stafflist) {
                String stuid = staff.getId();
                //生成试卷
                TBusiExamCopy ec = new TBusiExamCopy();
                ec.setStaffId(stuid);
                ec.setName(examTemp.getName());
                ec.setExamType(examTemp.getExamType());
                ec.setTotalScores(examTemp.getTotalScores());
                ec.setPassScores(examTemp.getPassScores());
                ec.setEquipPosition(examTemp.getEquipPosition());
                ec.setTiming(examTemp.getTiming());
                ec.setPosition(examTemp.getPosition());
                ec.setEquip(examTemp.getEquip());
                ec.setOldId(examTemp.getId());
                ec.setProblemCount(examTemp.getProblemCount());
                ec.setSendBy(teacherId);
                ec.setBatchId(batchId);
                examCopyService.save(ec);
                //生成下发数据
                TRelExamStaff res = new TRelExamStaff();
                res.setStudentId(stuid);
                res.setExamId(ec.getId());
                res.setBatchId(batchId);
                res.setSendStatus("1");
                res.setSendBy(teacherId);//后端取登录id，目前没有
                eslist.add(res);
                //examStaffService.save(res);
                if(problemList != null && problemList.size() > 0){
                    //循环生成题目copy数据
                    for (int i=0;i< problemList.size();i++) {
                        TBusiExamProblemVO p = problemList.get(i);
                        TBusiExamProblemCopy pc = new TBusiExamProblemCopy();
                        pc.setOldId(p.getId());
                        pc.setScoresCount(p.getScoresCount());
                        pc.setProblemAnswer(p.getProblemAnswer());
                        pc.setAnswerStr(p.getAnswerStr());
                        pc.setEquipPosition(p.getEquipPosition());
                        pc.setExamId(ec.getId()); //关联试卷是copy表的新id
                        pc.setProblemClassify(p.getProblemClassify());
                        pc.setProblemOrder((i+1));
                        pc.setProblemTitle(p.getProblemTitle());
                        pc.setProblemType(p.getProblemType());
                        pc.setScoresCount(p.getScoresCount());
                        pc.setStaffId(stuid);
                        pc.setSuccessAnswer(p.getSuccessAnswer());
                        pclist.add(pc);
                    }
                }
            }
            if(eslist != null && eslist.size() > 0 && pclist != null && pclist.size() > 0){
                examStaffService.saveBatch(eslist);
                problemCopyService.saveBatch(pclist);
                map.put("success","true");
                map.put("msg","推送成功");
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            map = null;
        }
        return map;
    }




}
