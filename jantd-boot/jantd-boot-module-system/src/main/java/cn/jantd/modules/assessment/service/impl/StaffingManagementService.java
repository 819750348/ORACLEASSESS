package cn.jantd.modules.assessment.service.impl;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import cn.jantd.modules.assessment.mapper.StaffingManagementMapper;
import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.assessment.util.EquipPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.service.impl
 * @ClassName: StaffingManagementService
 * @Author: admin
 * @Description:
 * @Date: 2020/5/21 19:42
 * @Version: 1.0
 */
@Service
public class StaffingManagementService {

    @Autowired
    StaffingManagementMapper staffingManagementMapper;

    /**
     * @Author: 风中的那朵云
     * @Description: 查询
     * @Date: 2020/5/6
     * @Version: 1.0
     */
    public PersonnelResult queryPageList(int pageNo, String equipPosition) {

//        String[] equipPositionList=EquipPosition.split(equipPosition);

        int pageSize = (pageNo - 1) * 10;
        List<PersonnelSettings> personnelSettingsList = staffingManagementMapper.queryPageList(pageSize, "%" + equipPosition + "%");

        int total = staffingManagementMapper.queryPageTotal("%" + equipPosition + "%");
        PersonnelResult personnelResult = new PersonnelResult();
        personnelResult.setPersonnelList(personnelSettingsList);
        personnelResult.setTotal(total);
        return personnelResult;
    }

    /**
     * @Author: 风中的那朵云
     * @Description: 编辑
     * @Date: 2020/5/6
     * @Version: 1.0
     */
    public void editPersonnel(String personnelId,String personnelEquipPosition){
        staffingManagementMapper.editPersonnel(personnelId, personnelEquipPosition);
    }
}
