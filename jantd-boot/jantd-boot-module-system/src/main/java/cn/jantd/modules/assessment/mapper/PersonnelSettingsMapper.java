package cn.jantd.modules.assessment.mapper;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.mapper
 * @ClassName: PersonnelSettingsMapper
 * @Author: admin
 * @Description:
 * @Date: 2020/5/20 14:11
 * @Version: 1.0
 */
public interface PersonnelSettingsMapper extends BaseMapper<PersonnelSettings> {
    List<PersonnelSettings> queryPageList(int pageNo);
    int queryPageTotal();
    void addPersonnel(String name,String staffGroup,String password);
}
