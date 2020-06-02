package cn.jantd.modules.assessment.service.impl;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import cn.jantd.modules.assessment.mapper.PersonnelSettingsMapper;
import cn.jantd.modules.assessment.model.PersonnelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.service.impl
 * @ClassName: personnelSettingsService
 * @Author: admin
 * @Description:
 * @Date: 2020/5/20 13:50
 * @Version: 1.0
 */
@Service
public class PersonnelSettingsService {
    /**
     * @Author:     风中的那朵云
     * @Description:  初始化界面
     * @Date:    2020/5/6
     * @Version:    1.0
     */
    @Autowired
    PersonnelSettingsMapper personnelSettingsMapper;
    public PersonnelResult queryPageList(int pageNo,String staffGroup,String  name){
        int pageSize = (pageNo-1) * 10;
        List<PersonnelSettings> personnelSettingsList= personnelSettingsMapper.queryPageList(pageSize,staffGroup,"%"+ name + "%");
        int total= personnelSettingsMapper.queryPageTotal(staffGroup,"%"+ name + "%");
        PersonnelResult personnelResult = new PersonnelResult();
        personnelResult.setPersonnelList(personnelSettingsList);
        personnelResult.setTotal(total);
        return personnelResult;
    }
    /**
     * @Author:     风中的那朵云
     * @Description: 添加人员
     * @Date:    2020/5/6
     * @Version:    1.0
     */
    public void addPersonnel(PersonnelSettings personnelSettings){
//        personnelSettingsMapper.addPersonnel(personnelSettings.getName(),personnelSettings.getStaffGroup(),personnelSettings.getPassword());

        if(personnelSettings.getStaffGroup().equals("教学管理人员")){
            personnelSettings.setStaffType("1");
        }else if(personnelSettings.getStaffGroup().equals("学员")){
            personnelSettings.setStaffType("2");
        }
        personnelSettingsMapper.insert(personnelSettings);
    }


    /**
     * @Author:     风中的那朵云
     * @Description: 查询
     * @Date:    2020/5/6
     * @Version:    1.0
     */
    public PersonnelResult searchPersonnel(PersonnelSettings personnelSettings){

        List personnelSettingsList;
        int total;
        if ( personnelSettings.getStaffGroup()==null || personnelSettings.getStaffGroup().equals("全部") ){
            personnelSettingsList = personnelSettingsMapper.searchPersonnelName("%" + personnelSettings.getName().toString() + "%");
            total= personnelSettingsMapper.querySearchPageTotalName("%" + personnelSettings.getName().toString() + "%");
        }else {
            personnelSettingsList = personnelSettingsMapper.searchPersonnel("%" + personnelSettings.getName().toString() + "%",personnelSettings.getStaffGroup());
            total= personnelSettingsMapper.querySearchPageTotal("%" + personnelSettings.getName().toString() + "%",personnelSettings.getStaffGroup());
        }
        PersonnelResult personnelResult = new PersonnelResult();
        personnelResult.setPersonnelList(personnelSettingsList);
        personnelResult.setTotal(total);
        return personnelResult;
    }

    public String stringArray2Strin(String[] str) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            sb.append("'").append(str[i]).append("'").append(",");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    public void deletePersonnel(String personnelId){
        String[]  listIds = personnelId.split(",");
        List listId = new ArrayList();
        for(int i = 0;i<listIds.length;i++){
            listId.add(listIds[i]);
        }
        personnelSettingsMapper.deletePersonnel(listId);
    }

    /**
     * @Author:     风中的那朵云
     * @Description:  编辑
     * @Date:    2020/5/6
     * @Version:    1.0
     */

    public void editPersonnel(String personnelId,String personnelRole,String personnelPassword){
        personnelSettingsMapper.editPersonnel(personnelId,personnelRole,personnelPassword);
    }
}
