package cn.jantd.modules.teacher.entity;

import cn.jantd.core.annotation.Dict;
import cn.jantd.core.poi.excel.annotation.Excel;
import cn.jantd.modules.teacher.util.DictUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 人员课程关联表
 * @Author qiji
 * @Date:  2020-03-03
 * @Version: V1.0
 */
@Data
@TableName("t_rel_staff_course")
public class TRelStaffCourse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 人员id
     */
    private String staffId;
    /**
     * 教学管理员id
     */
    private String teacherId;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 项目id
     */
    private String studyId;
    /**
     * 项目id
     */
    private String studyCourseId;
    /**
     * 课程状态 1完成0未完成
     */
    private String courseStatus;
    /**
     * 装备岗位，原装备和岗位两个字段合并
     */
    private String equipPosition;
    /**
     * 排序
     */
    private Integer courseOrder;
    /**
     * 删除标识
     */
    private String delFlag;
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
