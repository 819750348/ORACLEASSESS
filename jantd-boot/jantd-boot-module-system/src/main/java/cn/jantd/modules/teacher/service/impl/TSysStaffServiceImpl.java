package cn.jantd.modules.teacher.service.impl;

import cn.jantd.modules.system.service.ISysDictItemService;
import cn.jantd.modules.teacher.VO.TSysStaffVO;
import cn.jantd.modules.teacher.entity.TSysStaff;
import cn.jantd.modules.teacher.mapper.TSysStaffMapper;
import cn.jantd.modules.teacher.service.ITSysStaffService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.*;

/**
 * <p>
 * 人员表 服务实现类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Service
@Slf4j
public class TSysStaffServiceImpl extends ServiceImpl<TSysStaffMapper, TSysStaff> implements ITSysStaffService {

    @Autowired
    private TSysStaffMapper tsysStaffMapper;

    @Override
    public List<TSysStaffVO> selectAll(String type) {
        return tsysStaffMapper.selectStaffAll(type, null, null);
    }

    @Override
    public JSONArray getStudentsCourse(String dictCode, String itemValue) {
        JSONArray ja = new JSONArray();
        //假数据
        JSONObject o1 = new JSONObject();
        o1.put("studentName","邓力夫");
        o1.put("successPercent","4");
        o1.put("all","15");
        o1.put("describe","该学员尚未完成所有课程的学习");
        ja.add(o1);
        JSONObject o2 = new JSONObject();
        o2.put("studentName","彭雅琪");
        o2.put("successPercent","15");
        o2.put("all","15");
        o2.put("describe","该学员已完成所有课程的学习");
        ja.add(o2);
        JSONObject o3 = new JSONObject();
        o3.put("studentName","刘强");
        o3.put("successPercent","4");
        o3.put("all","12");
        o3.put("describe","该学员尚未完成所有课程的学习");
        ja.add(o3);
        JSONObject o4 = new JSONObject();
        o4.put("studentName","肖博约");
        o4.put("successPercent","7");
        o4.put("all","10");
        o4.put("describe","该学员尚未完成所有课程的学习");
        ja.add(o4);
        JSONObject o5 = new JSONObject();
        o5.put("studentName","董紫璇");
        o5.put("successPercent","10");
        o5.put("all","15");
        o5.put("describe","该学员尚未完成所有课程的学习");
        ja.add(o5);
        JSONObject o6 = new JSONObject();
        o6.put("studentName","段雨君");
        o6.put("successPercent","8");
        o6.put("all","13");
        o6.put("describe","该学员尚未完成所有课程的学习");
        ja.add(o6);

        //注掉正式代码
        /*List<TSysStaffVO> resultList = selectStaffAll("2", dictCode, itemValue);
        List<TBusiCourseVO> courseList = tbusiCourseMapper.selectAll();
        List<SysDictItemVO> dilist = sysDictItemMapper.queryEPSG();
        for (TSysStaffVO vo : resultList){
            int courseCount = 0;
            int courseCurr = 0;
            List<String> cList = new ArrayList<String>();
            for (TBusiCourseVO cvo : courseList){
                if(vo.getId().equals(cvo.getStaffId())){
                    courseCount++;
                    if(cvo.getCourseStatus().equals("1")){
                        courseCurr++;
                    }
                    cList.add(cvo.getName());
                }
            }
            //分组取值,其他四个标签等同
            String staffgroup = vo.getStaffGroup();
            String[] sg = staffgroup.split(",");
            StringBuffer sb = new StringBuffer();
            for(String s : sg){
                for (SysDictItemVO di : dilist){
                    if(di.getDictCode.equals("staff_group") && di.getItemValue.equals(s)){
                        sb.append(di.getItemText()+",");
                    }
                }
            }
            vo.setStaffGroupStr(sb.toString().substring(0,sb.toString().length()-1));

            vo.setCourseCount(courseCount);
            vo.setStepProgree(courseCurr);
            vo.setStudentName(vo.getName());
            vo.setStepInfo(cList);
        }*/
        return ja;
    }

    @Override
    public TSysStaffVO getStaffById(String id) {
       /* TSysStaffVO vo = new TSysStaffVO();
        vo.setName("赵飞");
        vo.setUserName("登录用户名");
        vo.setStaffGroup("1");
        //String[] dictCode = {"equip", "position", "specialities", "staff_group"};
        List<SysDictItemVO> dilist = sysDictItemService.queryEPSG();
        vo = DictUtil.staffDictToStr(vo, dilist);
        //vo.setStaffGroupStr("指挥控制组");
        vo.setStaffTypeStr("1");
        vo.setExamCount(10);
        return vo;*/
        TSysStaffVO vo = tsysStaffMapper.getStaffbyId(id);
        if(vo != null){
            /*List<SysDictItemVO> dilist = sysDictItemService.queryEPSG();
            return DictUtil.staffDictToStr(vo,dilist);*/
            return null;
        }else{
            return null;
        }
    }

    @Override
    public JSONArray getStaffByExamid(String id) {
        JSONArray ja = new JSONArray();
        JSONObject jo1 = new JSONObject();
        jo1.put("studentName","邓力夫");
        jo1.put("scores","50");
        ja.add(jo1);
        JSONObject jo2 = new JSONObject();
        jo2.put("studentName","段雨君");
        jo2.put("scores","70");
        ja.add(jo2);
        JSONObject jo3 = new JSONObject();
        jo3.put("studentName","雷虹宇");
        jo3.put("scores","66");
        ja.add(jo3);
        JSONObject jo4 = new JSONObject();
        jo4.put("studentName","董紫璇");
        jo4.put("scores","68");
        ja.add(jo4);
        JSONObject jo5 = new JSONObject();
        jo5.put("studentName","罗秋蒙");
        jo5.put("scores","87");
        ja.add(jo5);

        //注掉正式代码
        //resultList = tsysStaffMapper.getStaffByExamid(id);
        return ja;
    }

    @Override
    public Map<String, Object> searchStaffPage(Map<String, Object> pageParam) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        long total = (long) tsysStaffMapper.searchStaffTotal(pageParam);
        //pageStart  mysql用
        int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        pageParam.put("pageStart",pageStart);
        List<TSysStaffVO> voList = tsysStaffMapper.searchStaffPageForMysql(pageParam);
        if(voList!=null && voList.size()>0){
            /*List<SysDictItemVO> dilist = sysDictItemService.queryEPSG();
            for (TSysStaffVO vo : voList) {
                vo = DictUtil.staffDictToStr(vo, dilist);
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
        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> importExcelToStaff(InputStream file) throws Exception {
        Map<String, String> returnMap = new HashMap<String, String>();
        returnMap.put("num", "0");
        returnMap.put("code", "0");
        returnMap.put("message", "操作成功");
        StringBuffer message = new StringBuffer();
        Workbook book = null;
        List<TSysStaff> staffList = new ArrayList<TSysStaff>();
        /*List<SysDictItemVO> dilist = sysDictItemService.queryEPSG();*/

        boolean isXSSFWorkbook = true;
        if (!(file.markSupported())) {
            file = new PushbackInputStream(file, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(file)) {
            book = new HSSFWorkbook(file);
            isXSSFWorkbook = false;
        } else if (POIXMLDocument.hasOOXMLHeader(file)) {
            book = new XSSFWorkbook(OPCPackage.open(file));
        }
        //从第三行开始
        int i = 2;
        while (null != book.getSheetAt(0).getRow(i) && null != book.getSheetAt(0).getRow(i).getCell(0) && !"".equals(book.getSheetAt(0).getRow(i).getCell(0).getStringCellValue())) {
            StringBuffer sb = new StringBuffer();
            TSysStaff staff = new TSysStaff();
            //创建人,创建时间,取当前用户,更新人员,更新时间,初始和创建一同录入,更新数据时更新
            staff.setCreateBy("73");
            staff.setCreateTime(new Date());
            staff.setCreateBy("73");
            staff.setCreateTime(new Date());

            //人员名称在while中验证过了
            String staffName = book.getSheetAt(0).getRow(i).getCell(0).getStringCellValue();
            if (null != staffName && !"".equals(staffName)) {
                staff.setName(staffName);
            }
            //用户名
            String userName = book.getSheetAt(0).getRow(i).getCell(1).getStringCellValue();
            if (null != userName && !"".equals(userName)) {
                if(this.checkUser(null, userName)){
                    staff.setUserName(userName);
                }else{
                    sb.append(staffName+": " + "登录用户名已经存在\n");
                }
            } else {
                sb.append(staffName+": " + "登录用户名不能为空\n");
            }
            //密码：默认密码为123456，如果有录入密码为录入值
            staff.setPassword("123456");
            if (null != book.getSheetAt(0).getRow(i).getCell(2) && !"".equals(book.getSheetAt(0).getRow(i).getCell(2).getStringCellValue())) {
                String password = book.getSheetAt(0).getRow(i).getCell(2).getStringCellValue();
                staff.setPassword(password);
            }
            //人员类型
            if (null != book.getSheetAt(0).getRow(i).getCell(3) && !"".equals(book.getSheetAt(0).getRow(i).getCell(3).getStringCellValue())) {
                String staffType1 = book.getSheetAt(0).getRow(i).getCell(3).getStringCellValue();
                if ("学员".equals(staffType1)) {
                    staff.setStaffType("2");
                } else if ("教学管理员".equals(staffType1)) {
                    staff.setStaffType("1");
                }else{
                    sb.append(staffName+": " + "人员类型必须输入规定值\n");
                }
            } else {
                sb.append(staffName+": " + "人员类型不能为空\n");
            }
            //装备
            if (null != book.getSheetAt(0).getRow(i).getCell(4) && !"".equals(book.getSheetAt(0).getRow(i).getCell(4).getStringCellValue())) {
                String equip = book.getSheetAt(0).getRow(i).getCell(4).getStringCellValue();
                /*String itemValue = DictUtil.strToItemVaule("equip", equip, dilist);*/
                String itemValue = "";
                if(!"".equals(itemValue)){
                    staff.setEquip(itemValue);
                }else{
                    sb.append(staffName+": " + "装备必须输入规定值\n");
                }
            } else {
                sb.append(staffName+": " + "装备不能为空\n");
            }
            //岗位
            if (null != book.getSheetAt(0).getRow(i).getCell(5) && !"".equals(book.getSheetAt(0).getRow(i).getCell(5).getStringCellValue())) {
                String position = book.getSheetAt(0).getRow(i).getCell(5).getStringCellValue();
                //String itemValue = DictUtil.strToItemVaule("position", position, dilist);
                String itemValue = "";
                if(!"".equals(itemValue)){
                    staff.setPosition(itemValue);
                }else{
                    sb.append(staffName+": " + "岗位必须输入规定值\n");
                }
            } else {
                sb.append(staffName+": " + "岗位不能为空\n");
            }

            if(sb.length() > 0){
                message.append(sb.toString() + "\n");
            }else {
                staffList.add(staff);
            }
            i++;
        }
        //System.out.println(message);
        if (staffList.size() > 0) {
            this.saveBatch(staffList, 30);
            returnMap.put("num", staffList.size() + "");
            if(message.length() > 0){
                returnMap.put("message", message.toString());
            }
            return returnMap;
        } else {
            returnMap.put("code", "1");
            if(message.length() > 0){
                returnMap.put("message", message.toString());
            }else{
                returnMap.put("message", "没有数据");
            }
            return returnMap;
        }
    }

    @Override
    public boolean checkUser(String id, String username) {
        List<TSysStaff> list = tsysStaffMapper.checkUser(id, username);
        if(list!=null && list.size() > 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Map<String, Object> searchStudentPage(Map<String, Object> pageParam) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        long total = (long) tsysStaffMapper.searchStaffTotal(pageParam);
        List<TSysStaffVO> voList = tsysStaffMapper.searchStaffPage(pageParam);
        if(voList!=null && voList.size()>0){
            /*List<SysDictItemVO> dilist = sysDictItemService.queryEPSG();
            for (TSysStaffVO vo : voList) {
                vo = DictUtil.staffDictToStr(vo, dilist);
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
        return resultMap;
    }

    @Override
    public Map<String, Object> studentConfigList(Map<String, Object> pageParam) {
        String teacherid = String.valueOf(pageParam.get("teacherid"));
        pageParam.put("teacherid","");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        long total = (long) tsysStaffMapper.searchStaffTotal(pageParam);
        List<TSysStaffVO> voList = tsysStaffMapper.searchStaffPage(pageParam);
        List<TSysStaffVO> stList = tsysStaffMapper.searchStudentByTeacher(teacherid);
        if(voList!=null && voList.size()>0){
            /*List<SysDictItemVO> dilist = sysDictItemService.queryEPSG();*/
            for (TSysStaffVO vo : voList) {
                /*vo = DictUtil.staffDictToStr(vo, dilist);*/
                if(stList != null && stList.size() > 0){
                    for (TSysStaffVO st: stList) {
                        if(st.getId().equals(vo.getId())){
                            vo.setTeacherId(st.getTeacherId());
                        }
                    }
                }
            }
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
    @Transactional(rollbackFor = Exception.class)
    public boolean removeStaffAndRelById(String id, String staffType) throws Exception {
        boolean ok = this.removeById(id);
        return ok;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeBatchStaffAndRelByIds(List<String> ids) throws Exception {
        boolean ok = this.removeByIds(ids);
    }

    @Override
    public List<TSysStaffVO> getStudentsByLogin(Map<String, Object> pageParam) {
        List<TSysStaffVO> staffList = tsysStaffMapper.searchStaffByLogin(pageParam);
        return staffList;
    }
}
