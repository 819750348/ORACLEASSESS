package cn.jantd.modules.teacher.entity;

import cn.jantd.core.annotation.Dict;
import cn.jantd.core.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Transient;
import java.io.Serializable;

/**
 * @Description: 人员表
 * @Author qiji
 * @Date:  2020-03-03
 * @Version: V1.0
 */
@Data
@TableName("t_sys_staff")
public class TSysStaff implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 人员名称
     */
    private String name;
    /**
     * 登录名
     */
    private String userName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 装备
     */
    private String equip;
    /**
     * 岗位
     */
    private String position;
    /**
     * 人员分组
     */
    private String staffGroup;
    /**
     * 装备岗位，原装备和岗位两个字段合并
     */
    private String equipPosition;
    /**
     * 人员类型：1教学管理员2学员3管理员
     */
    private String staffType;
    /**
     * 删除状态（0，正常，1已删除）
     */
    @Excel(name = "删除状态", width = 15,dicCode="del_flag")
    private String delFlag = "0";
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;


}
