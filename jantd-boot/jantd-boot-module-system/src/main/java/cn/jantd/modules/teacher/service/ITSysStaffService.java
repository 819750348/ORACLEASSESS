package cn.jantd.modules.teacher.service;

import cn.jantd.modules.teacher.VO.TSysStaffVO;
import cn.jantd.modules.teacher.entity.TSysStaff;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  人员表 服务类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
public interface ITSysStaffService extends IService<TSysStaff> {

    /**
     * 根据type查询所有人员,1教学管理员2学员3管理员,不传是所有
     */
    List<TSysStaffVO> selectAll(String type);

    /**
     * 查询所有学员教学进度
     */
    JSONArray getStudentsCourse(String dictCode, String itemValue);


    /**
     * 根据Id查询人员信息
     */
    TSysStaffVO getStaffById(String id);
    /**
     * 根据试卷查询试卷指定人员下发列表
     */
    JSONArray getStaffByExamid(String id);

    /**
     * 分页查询人员
     */
    Map<String,Object> searchStaffPage(Map<String, Object> pageParam);

    /**
     * excel导入人员信息
     */
    Map<String,String> importExcelToStaff(InputStream file) throws Exception;

    /**
     * 唯一性验证
     */
    boolean checkUser(String id, String username);

    /**
     * 分页查询学员
     */
    Map<String,Object> searchStudentPage(Map<String, Object> pageParam);
    /**
     * 教学管理员分配学员列表
     */
    Map<String,Object> studentConfigList(Map<String, Object> pageParam);
    /**
     * 删除人员和关联表(教学管理员学员关系表)
     */
    boolean removeStaffAndRelById(String id, String staffType) throws Exception;

    /**
     * 批量删除人员和关联表(教学管理员学员关系表)
     */
    void removeBatchStaffAndRelByIds(List<String> ids) throws Exception;

    /**
     * FD2000登录时查询登录用户
     */
    List<TSysStaffVO> getStudentsByLogin(Map<String, Object> pageParam);
}