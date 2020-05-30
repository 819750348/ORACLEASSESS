package cn.jantd.modules.assessment.service.impl;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import cn.jantd.modules.assessment.entity.TBusiExamProblemTemp;
import cn.jantd.modules.assessment.mapper.TestManagementMapper;
import cn.jantd.modules.assessment.model.PersonnelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
public class TestManagementService {
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
            listId.add(Integer.parseInt(listIds[i]));
        }
        testManagementMapper.deleteTestManagement(listId);
    }
}
