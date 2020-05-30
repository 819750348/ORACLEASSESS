package cn.jantd.modules.assessment.mapper;

import cn.jantd.modules.assessment.entity.PersonnelSettings;
import cn.jantd.modules.assessment.entity.TBusiExamProblemTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.mapper
 * @ClassName: TestManagementMapper
 * @Author: admin
 * @Description:
 * @Date: 2020/5/29 13:07
 * @Version: 1.0
 */
public interface TestManagementMapper extends BaseMapper<PersonnelSettings> {
    List<TBusiExamProblemTemp> queryPageList(int pageNo, @Param(value="equipPosition") String equipPosition, @Param(value="problemTitle") String problemTitle);
    int queryPageTotal( @Param(value="equipPosition") String equipPosition,@Param(value="problemTitle") String problemTitle);


    void editTestManagement(String testManagementId,String testManagementEquipPosition);

    void deleteTestManagement(@org.apache.ibatis.annotations.Param("objs") List listIds);
}
