package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TSysStaffVO;
import cn.jantd.modules.teacher.entity.TSysStaff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  人员表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Repository
public interface TSysStaffMapper extends BaseMapper<TSysStaff>  {

    /**
     * 根据type查询所有人员,1教学管理员2学员3管理员,不传是所有
     * @return List
     */
    public List<TSysStaffVO> selectStaffAll(@Param("type") String type, @Param("dictCode") String dictCode, @Param("itemValue") String itemValue);

    /**
     * 根据人员id查询人员信息
     * @param staffid
     * @return TSysStaff
     */
    public TSysStaffVO getStaffbyId(@Param("staffid") String staffid);

    /**
     * 查询所有学员教学进度
     * @return List
     */
    public List<TSysStaffVO> getStudentsCourse();

    /**
     * 根据人员id查询人员信息
     * @param staffid
     * @return List
     */
    public List<TSysStaffVO> getStaffbyTeacherid(@Param("staffid") String staffid);

    /**
     * 根据试卷查询试卷指定人员下发列表
     * @param examid
     * @return List
     */
    public List<TSysStaffVO> getStaffByExamid(@Param("examid") String examid);
    /**
     * 分页人员列表
     * @param param 页面分页参数,数字型(pageSize,pageNo),其他参数,字符型(包括人员类型,排序等)
     * @return List
     */
    public List<TSysStaffVO> searchStaffPage(Map<String, Object> param);
    /**
     * 分页人员列表mysql
     * @param param 页面分页参数,数字型(pageSize,pageNo),其他参数,字符型(包括人员类型,排序等)
     * @return List
     */
    public List<TSysStaffVO> searchStaffPageForMysql(Map<String, Object> param);
    /**
     * 人员总数
     * @param param 参数,字符型(人员类型必须)
     * @return List
     */
    public Integer searchStaffTotal(Map<String, Object> param);

    /**
     * 唯一性验证
     * @param id
     * @param username
     * @return List
     */
    public List<TSysStaff> checkUser(@Param("id") String id, @Param("username") String username);

    /**
     * 查询教学管理员名下所有学员
     * @param teacherid 字符型(教学管理员id必须)
     * @return List
     */
    public List<TSysStaffVO> searchStudentByTeacher(@Param("teacherid") String teacherid);


    List<TSysStaff> getSutdentByPosition(@Param("strings") String[] strings);

    public List<TSysStaffVO> searchStaffByLogin(Map<String, Object> param);
}
