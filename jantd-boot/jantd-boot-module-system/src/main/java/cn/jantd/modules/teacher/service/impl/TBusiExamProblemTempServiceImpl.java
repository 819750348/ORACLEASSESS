package cn.jantd.modules.teacher.service.impl;

import cn.jantd.modules.system.service.ISysDictItemService;
import cn.jantd.modules.teacher.VO.TBusiExamProblemVO;
import cn.jantd.modules.teacher.VO.TBusiProblemAnswerVO;
import cn.jantd.modules.teacher.entity.TBusiExamProblemTemp;
import cn.jantd.modules.teacher.entity.TBusiProblemAnswerTemp;
import cn.jantd.modules.teacher.entity.TSysStaff;
import cn.jantd.modules.teacher.mapper.TBusiExamProblemTempMapper;
import cn.jantd.modules.teacher.service.ITBusiExamProblemTempService;
import cn.jantd.modules.teacher.service.ITBusiProblemAnswerTempService;
import cn.jantd.modules.teacher.util.DictUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
 * 题目表 服务实现类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Service
@Slf4j
public class TBusiExamProblemTempServiceImpl extends ServiceImpl<TBusiExamProblemTempMapper, TBusiExamProblemTemp> implements ITBusiExamProblemTempService {

    @Autowired
    private TBusiExamProblemTempMapper problemTemaMapper;
    @Autowired
    private ITBusiProblemAnswerTempService answerTempService;
    @Autowired
    private ISysDictItemService sysDictItemService;

    private final String[] PROBLEM_ORDER = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    @Override
    public Map<String, Object> searchProblemPage(Map<String, Object> pageParam) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Integer total = problemTemaMapper.searchProblemTotal(pageParam);
        //pageStart  mysql用
        int pageStart = (Integer.parseInt(String.valueOf(pageParam.get("pageSize")))*(Integer.parseInt(String.valueOf(pageParam.get("pageNo")))-1));
        pageParam.put("pageStart",pageStart);
        List<TBusiExamProblemVO> voList = problemTemaMapper.searchProblemPageForMysql(pageParam);
        if(voList!=null && voList.size()>0){
            /*List<SysDictItemVO> dilist = sysDictItemService.queryEPSG();
            for (TBusiExamProblemVO vo : voList) {
                vo = DictUtil.problemDictToStr(vo, dilist);
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
    public Map<String, String> importExcelToProblem(InputStream file) throws Exception {
        Map<String, String> returnMap = new HashMap<String, String>();
        returnMap.put("num", "0");
        returnMap.put("code", "0");
        returnMap.put("message", "操作成功");
        StringBuffer message = new StringBuffer();
        Workbook book = null;
        List<TBusiExamProblemTemp> problemList = new ArrayList<TBusiExamProblemTemp>();
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
            TBusiExamProblemTemp vo = new TBusiExamProblemTemp();
            //创建人,创建时间,取当前用户,更新人员,更新时间,初始和创建一同录入,更新数据时更新
            vo.setCreateBy("73");
            vo.setCreateTime(new Date());
            vo.setCreateBy("73");
            vo.setCreateTime(new Date());
            //题目标题，while中验证过非空
            String problemTitle = book.getSheetAt(0).getRow(i).getCell(0).getStringCellValue();
            if (null != problemTitle && !"".equals(problemTitle)) {
                if(this.checkProblem(problemTitle)){
                    vo.setProblemTitle(problemTitle);
                }else{
                    sb.append(problemTitle+": " + "题目已经存在\n");
                }
            }

            //试题类
            if (null != book.getSheetAt(0).getRow(i).getCell(1) && !"".equals(book.getSheetAt(0).getRow(i).getCell(1).getStringCellValue())) {
                String problemClassify = book.getSheetAt(0).getRow(i).getCell(1).getStringCellValue();
                //1单选,2多选,3判断
                if("理论试题".equals(problemClassify)){
                    vo.setProblemClassify("1");
                }else if("使用操作试题".equals(problemClassify)){
                    vo.setProblemClassify("2");
                }else if("维护维修操作试题".equals(problemClassify)){
                    vo.setProblemClassify("3");
                }else{
                    sb.append(problemTitle+": " + "试题类必须输入规定值\n");
                }
            } else {
                sb.append(problemTitle+": " + "试题类不能为空\n");
            }

            //题型
            if (null != book.getSheetAt(0).getRow(i).getCell(2) && !"".equals(book.getSheetAt(0).getRow(i).getCell(2).getStringCellValue())) {
                String problemType = book.getSheetAt(0).getRow(i).getCell(2).getStringCellValue();
                //1单选,2多选,3判断
                if("单选".equals(problemType)){
                    vo.setProblemType("1");
                }else if("复选".equals(problemType)){
                    vo.setProblemType("2");
                }else if("判断".equals(problemType)){
                    vo.setProblemType("3");
                }else{
                    sb.append(problemTitle+": " + "题型必须输入规定值\n");
                }
            } else {
                sb.append(problemTitle+": " + "题目类型不能为空\n");
            }

            //装备
            if (null != book.getSheetAt(0).getRow(i).getCell(3) && !"".equals(book.getSheetAt(0).getRow(i).getCell(3).getStringCellValue())) {
                String equip = book.getSheetAt(0).getRow(i).getCell(3).getStringCellValue();
                //String itemValue = DictUtil.strToItemVaule("equip", equip, dilist);
                String itemValue = "";
                if(!"".equals(itemValue)){
                    vo.setEquip(itemValue);
                }else{
                    sb.append(problemTitle+": " + "装备必须输入规定值\n");
                }
            } else {
                sb.append(problemTitle+": " + "装备不能为空\n");
            }

            //岗位
            if (null != book.getSheetAt(0).getRow(i).getCell(4) && !"".equals(book.getSheetAt(0).getRow(i).getCell(4).getStringCellValue())) {
                String position = book.getSheetAt(0).getRow(i).getCell(4).getStringCellValue();
                //String itemValue = DictUtil.strToItemVaule("position", position, dilist);
                String itemValue = "";
                if(!"".equals(itemValue)){
                    vo.setPosition(itemValue);
                }else{
                    sb.append(problemTitle+": " + "岗位必须输入规定值\n");
                }
            } else {
                sb.append(problemTitle+": " + "岗位不能为空\n");
            }

            //一道题至少需要有一个正确答案和两个答案选项
            String isRight = "";
            if(null != book.getSheetAt(0).getRow(i).getCell(5) && !"".equals(book.getSheetAt(0).getRow(i).getCell(5).getStringCellValue())){
                isRight = book.getSheetAt(0).getRow(i).getCell(5).getStringCellValue();
            }
            String ans1 = "";
            if(null != book.getSheetAt(0).getRow(i).getCell(6) && !"".equals(book.getSheetAt(0).getRow(i).getCell(6).getStringCellValue())){
                ans1 = book.getSheetAt(0).getRow(i).getCell(6).getStringCellValue();
            }
            String ans2 = "";
            if(null != book.getSheetAt(0).getRow(i).getCell(7) && !"".equals(book.getSheetAt(0).getRow(i).getCell(7).getStringCellValue())){
                ans2 = book.getSheetAt(0).getRow(i).getCell(7).getStringCellValue();
            }
            boolean flag = true;
            //System.out.println(isRight+"______"+ans1+"_____"+ans2);
            if ("".equals(isRight) || "".equals(ans1) || "".equals(ans2)) {
                sb.append(problemTitle + ": " + "正确答案和答案选项不能为空\n");
            }
            //System.out.println("wefwef: "+sb);
            if(sb.length() > 0){
                flag = false;
                message.append(sb.toString() + "\n");
            }else{
                this.saveOrUpdate(vo);
                problemList.add(vo);
            }
            if (flag) {
                List<TBusiProblemAnswerTemp> answerList = new ArrayList<TBusiProblemAnswerTemp>();
                String[] isRightArray = isRight.split("");
                int j = 1;
                int k = 5 + j;
                while (null != book.getSheetAt(0).getRow(i).getCell(k) && !"".equals(book.getSheetAt(0).getRow(i).getCell(k).getStringCellValue())) {
                    if(null != book.getSheetAt(0).getRow(i).getCell(k) && !"".equals(book.getSheetAt(0).getRow(i).getCell(k).getStringCellValue())){
                        String answerStr = book.getSheetAt(0).getRow(i).getCell(k).getStringCellValue();
                        TBusiProblemAnswerTemp ans = new TBusiProblemAnswerTemp();
                        ans.setProblemId(vo.getId());
                        ans.setAnswerOrder(j);
                        ans.setAnswerStr(answerStr);
                        ans.setCreateBy("73");
                        ans.setCreateTime(new Date());
                        ans.setUpdateBy("73");
                        ans.setUpdateTime(new Date());
                        boolean isRightFlag = false;
                        for(String s : isRightArray){
                            if(this.PROBLEM_ORDER[j-1].equals(s)){
                                isRightFlag = true;
                            }
                        }
                        if(isRightFlag){
                            ans.setIsRight("1");
                        }else{
                            ans.setIsRight("0");
                        }
                        answerList.add(ans);
                        j++;k++;
                    }else{
                        break;
                    }
                }
                answerTempService.saveBatch(answerList, 30);
            }
            i++;
        }
        System.out.println("message: "+message);
        if (problemList.size() > 0) {
            returnMap.put("num", problemList.size() + "");
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
    public boolean checkProblem(String title) {
        List<TBusiExamProblemTemp> list = problemTemaMapper.checkProblem(title);
        if(list!=null && list.size() > 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public JSONObject getDetailByProblem(TBusiExamProblemTemp problem) {
        /*TBusiExamProblemVO vo = new TBusiExamProblemVO();
        vo.setProblemTitle(problem.getProblemTitle());
        StringBuffer isRightSB = new StringBuffer("");
        StringBuffer answerSB = new StringBuffer("");
        List<TBusiProblemAnswerVO> ansList = answerTempService.searchAnswerByProblem(problem.getId());
        if(ansList != null && ansList.size() > 0){
            vo.setAnswer(ansList);
            for (TBusiProblemAnswerVO ans : ansList){
                answerSB.append(ans.getAnswerStr()+"%&");
                if(ans.getIsRight().equals("1")){
                    isRightSB.append(this.PROBLEM_ORDER[ans.getAnswerOrder()-1]);
                }
            }
            vo.setIsRightStr(isRightSB.toString());
        }
        JSONObject json = new JSONObject();
        json.put("problemTitle",vo.getProblemTitle());
        json.put("answer",answerSB.toString().split("%&"));
        json.put("isRight",vo.getIsRightStr());
        return json;*/
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> removeProblemAndAnswerByIds(List<String> ids) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        List<TBusiExamProblemVO> list = problemTemaMapper.checkRelExamProblem(ids);
        if(list == null && list.size() == 0){
            boolean ok = this.removeByIds(ids);
            if(ok){
                // 当批量删除时,删除在TBusiProblemAnswerTemp中对应的所有答案数据
                for (String id : ids) {
                    LambdaQueryWrapper<TBusiProblemAnswerTemp> query = new LambdaQueryWrapper<>();
                    query.eq(TBusiProblemAnswerTemp::getProblemId, id);
                    boolean ook = this.answerTempService.remove(query);
                    //System.out.println(ook);
                }
            }
            map.put("success","true");
            map.put("msg","删除成功");
        }else{
            map.put("success","false");
            map.put("msg","该题目在试卷中被引用，不能删除");
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> removeProblemAndAnswerById(String id) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        List<TBusiExamProblemVO> list = problemTemaMapper.checkRelExamProblem(Arrays.asList(id.split(",")));
        if(list == null && list.size() == 0){
            boolean ok = this.removeById(id);
            if(ok){
                // 当删除时,删除在TBusiProblemAnswerTemp中对应的所有答案数据
                LambdaQueryWrapper<TBusiProblemAnswerTemp> query = new LambdaQueryWrapper<>();
                query.eq(TBusiProblemAnswerTemp::getProblemId, id);
                answerTempService.remove(query);
            }
            map.put("success","true");
            map.put("msg","删除成功");
        }else{
            map.put("success","false");
            map.put("msg","该题目在试卷中被引用，不能删除");
        }
        return map;
    }

    @Override
    public void addProblemAndAnswer(JSONObject jsonObject) {

    }
}
