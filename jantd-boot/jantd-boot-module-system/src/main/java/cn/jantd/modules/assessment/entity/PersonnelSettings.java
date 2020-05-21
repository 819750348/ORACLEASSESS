package cn.jantd.modules.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.entity
 * @ClassName: PersonnelSettings
 * @Author: admin
 * @Description:
 * @Date: 2020/5/20 13:57
 * @Version: 1.0
 */
@Data
@TableName("t_sys_staff")
public class PersonnelSettings implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * @Author:     风中的那朵云
     * @Description:  用户设置
     * @Date:    2020/5/6
     * @Version:    1.0
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    public String getStaffGroup() {
        return staffGroup;
    }

    public void setStaffGroup(String staffGroup) {
        this.staffGroup = staffGroup;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 主键id
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 姓名
     */
    private String name;


    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 装备
     */
    private String equip;
    /**
     * 岗位
     */
    private String position;
    /**
     * 专业
     */
    private String specialities;

    /**
     * 分组
     */
    private String staffGroup;
    /**
     * 类型
     */
    private String staffType;

    /**
     * 用户名称
     */
    private String UserName;

    /**
     * 密码
     */
    private String password;
}
