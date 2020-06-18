package cn.jantd.modules.assessment.mapper;

import cn.jantd.modules.teacher.entity.TSysStaff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.mapper
 * @ClassName: StaffingManagementMapper
 * @Author: admin
 * @Description:
 * @Date: 2020/5/21 19:44
 * @Version: 1.0
 */
public interface StaffingManagementMapper extends BaseMapper<TSysStaff> {
    List<TSysStaff> queryPageList(int pageNo, @Param(value="equipPosition") String equipPosition);
    int queryPageTotal(String equipPosition);
    void editPersonnel(String id,String personnelEquipPosition);
}
