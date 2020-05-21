package cn.jantd.modules.assessment.service.impl;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import cn.jantd.modules.assessment.mapper.PersonnelSettingsMapper;
import cn.jantd.modules.assessment.model.PersonnelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PersonnelResult queryPageList(int pageNo){
        int pageSize = (pageNo-1) * 10;
        List<PersonnelSettings> personnelSettingsList= personnelSettingsMapper.queryPageList(pageSize);
        int total= personnelSettingsMapper.queryPageTotal();

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
        personnelSettingsMapper.addPersonnel(personnelSettings.getName(),personnelSettings.getStaffGroup(),personnelSettings.getPassword());
    }
}
