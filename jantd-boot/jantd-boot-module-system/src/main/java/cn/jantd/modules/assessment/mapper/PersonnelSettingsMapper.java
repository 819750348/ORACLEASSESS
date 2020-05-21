package cn.jantd.modules.assessment.mapper;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
    List<PersonnelSettings> queryPageList(int pageNo,String staffGroup,String name);

    int queryPageTotal(String staffGroup,String name);

    void addPersonnel(String name,String staffGroup,String password);

    List<PersonnelSettings> searchPersonnel(String  name,String staffGroup);

    int querySearchPageTotal(String  name,String staffGroup);



    List<PersonnelSettings> searchPersonnelName(String  name);
    int querySearchPageTotalName(String  name);

    void deletePersonnel(@Param("objs") List listIds);

    void editPersonnel(String id,String staffGroup,String  password);
}
