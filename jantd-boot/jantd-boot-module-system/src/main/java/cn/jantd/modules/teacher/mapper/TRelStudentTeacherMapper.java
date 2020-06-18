package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TSysStaffVO;
import cn.jantd.modules.teacher.entity.TRelStudentTeacher;
import cn.jantd.modules.teacher.entity.TSysStaff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  教学管理员学员关联表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-04-20
 */
@Repository
public interface TRelStudentTeacherMapper extends BaseMapper<TRelStudentTeacher>  {




}
