package cn.jantd.modules.assessment.service.impl;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import cn.jantd.modules.assessment.entity.TBusiExamProblemTemp;
import cn.jantd.modules.assessment.mapper.TestManagementMapper;
import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.assessment.util.AnswerStrObejct;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.*;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.service.impl
 * @ClassName: TestManagementService
 * @Author: admin
 * @Description:
 * @Date: 2020/5/29 11:26
 * @Version: 1.0
 */
@Service
public class TestManagementService extends ServiceImpl<TestManagementMapper,TBusiExamProblemTemp> {
    @Autowired
    TestManagementMapper testManagementMapper;

    public PersonnelResult queryPageList(int pageNo,String equipPosition,String  testDescribe){
        int pageSize = (pageNo-1) * 10;
        List<TBusiExamProblemTemp> personnelSettingsList= testManagementMapper.queryPageList(pageSize,equipPosition,"%"+ testDescribe + "%");
        int total= testManagementMapper.queryPageTotal(equipPosition,"%"+ testDescribe + "%");
        PersonnelResult personnelResult = new PersonnelResult();
        personnelResult.setPersonnelList(personnelSettingsList);
        personnelResult.setTotal(total);
        return personnelResult;
    }


    public void editTestManagement(String testManagementId,String testManagementEquipPosition){
        testManagementMapper.editTestManagement(testManagementId,testManagementEquipPosition);
    }

    public void deleteTestManagement(String personnelId){
        String[]  listIds = personnelId.split(",");
        List listId = new ArrayList();
        for(int i = 0;i<listIds.length;i++){
            listId.add(listIds[i]);
        }
        testManagementMapper.deleteTestManagement(listId);
    }

    public void addTest(String problemTitle,String problemClassify,String problemType,String equipPosition,String answerStr, String successAnswer){
        TBusiExamProblemTemp tBusiExamProblemTemp =new TBusiExamProblemTemp();
        tBusiExamProblemTemp.setProblemTitle(problemTitle);
        tBusiExamProblemTemp.setProblemClassify(problemClassify);
        tBusiExamProblemTemp.setProblemType(problemType);
        tBusiExamProblemTemp.setEquipPosition(equipPosition);

        String[] o = answerStr.split(",");


        JSONObject jsonObject1=new JSONObject();
        JSONObject jsonObject2=new JSONObject();
        JSONObject jsonObject3=new JSONObject();
        JSONObject jsonObject4=new JSONObject();
        JSONArray jsonArray = new JSONArray();

        if(o.length> 0){
            for(int i= 0;i<o.length;i++){
                if(i == 0){
                    try {
                        jsonObject1.put("order","1");
                        jsonObject1.put("No","A");
                        jsonObject1.put("answerStr",o[i]);
                        jsonArray.put(jsonObject1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if(i == 1){
                    try {
                        jsonObject2.put("order","2");
                        jsonObject2.put("No","B");
                        jsonObject2.put("answerStr",o[i]);
                        jsonArray.put(jsonObject2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if(i== 2){
                    try {
                        jsonObject3.put("order","3");
                        jsonObject3.put("No","C");
                        jsonObject3.put("answerStr",o[i]);
                        jsonArray.put(jsonObject3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else if(i ==3){
                    try {
                        jsonObject4.put("order","4");
                        jsonObject4.put("No","D");
                        jsonObject4.put("answerStr",o[i]);
                        jsonArray.put(jsonObject4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        tBusiExamProblemTemp.setAnswerStr(jsonArray.toString());
        tBusiExamProblemTemp.setSuccessAnswer(successAnswer);
        testManagementMapper.insert(tBusiExamProblemTemp);
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> importExcel(InputStream file) throws Exception {
        Map<String, String> returnMap = new HashMap<String, String>();
        returnMap.put("num", "0");
        returnMap.put("code", "0");
        returnMap.put("message", "操作成功");
        StringBuffer message = new StringBuffer();
        Workbook book = null;

       List<TBusiExamProblemTemp> tBusiExamProblemTemps = new ArrayList<TBusiExamProblemTemp>();


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
            TBusiExamProblemTemp tBusiExamProblemTemp = new TBusiExamProblemTemp();
            String problemTitle = book.getSheetAt(0).getRow(i).getCell(0).getStringCellValue();
            if (null != problemTitle && !"".equals(problemTitle)) {
                tBusiExamProblemTemp.setProblemTitle(problemTitle);
            }
            String problemClassify = book.getSheetAt(0).getRow(i).getCell(1).getStringCellValue();
            if (null != problemClassify && !"".equals(problemClassify)) {
                tBusiExamProblemTemp.setProblemClassify(problemClassify);
            }

            String problemType = book.getSheetAt(0).getRow(i).getCell(2).getStringCellValue();
            if (null != problemType && !"".equals(problemType)) {
                tBusiExamProblemTemp.setProblemType(problemType);
            }

            String equipPosition = book.getSheetAt(0).getRow(i).getCell(3).getStringCellValue();
            if (null != equipPosition && !"".equals(equipPosition)) {
                tBusiExamProblemTemp.setEquipPosition(equipPosition);
            }
            String successAnswer = book.getSheetAt(0).getRow(i).getCell(4).getStringCellValue();
            if (null != successAnswer && !"".equals(successAnswer)) {
                tBusiExamProblemTemp.setSuccessAnswer(successAnswer);
            }

//            String answerStr = book.getSheetAt(0).getRow(i).getCell(5).getStringCellValue();
//            if (null != answerStr && !"".equals(answerStr)) {
//                tBusiExamProblemTemp.setAnswerStr(answerStr);
//            }

            int a = 5;
            int b = 0;
            String[] s = {"A","B","C","D"};
            JSONArray jsonArray = new JSONArray();

            while (null != book.getSheetAt(0).getRow(i).getCell(a) && !"".equals(book.getSheetAt(0).getRow(i).getCell(a).getStringCellValue())) {
                if(null != book.getSheetAt(0).getRow(i).getCell(a) && !"".equals(book.getSheetAt(0).getRow(i).getCell(a).getStringCellValue())){
                    String answerStr = book.getSheetAt(0).getRow(i).getCell(a).getStringCellValue();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("answerStr",answerStr );
                    jsonObject.put("No",s[b]);
                    jsonObject.put("order",b+1);
                    jsonArray.put(jsonObject);
                    a++;
                    b++;
                }else{
                    break;
                }
            }
            tBusiExamProblemTemp.setAnswerStr(jsonArray.toString());
            if(sb.length() > 0){
                message.append(sb.toString() + "\n");
            }else {
                tBusiExamProblemTemps.add(tBusiExamProblemTemp);
            }
            i++;
        }
        if (tBusiExamProblemTemps.size() > 0) {
            this.saveBatch(tBusiExamProblemTemps, 30);
            returnMap.put("num", tBusiExamProblemTemps.size() + "");
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


}
