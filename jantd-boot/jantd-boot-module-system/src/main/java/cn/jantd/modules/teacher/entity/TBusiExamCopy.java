package cn.jantd.modules.teacher.entity;

import cn.jantd.core.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 试卷copy表
 * @Author qiji
 * @Date:  2020-03-03
 * @Version: V1.0
 */
@Data
@TableName("t_busi_exam_copy")
public class TBusiExamCopy implements Serializable {
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
     * 标题
     */
    private String name;
    /**
     * 类型标签
     */
    private String examType;
    /**
     * 总分
     */
    private int totalScores;
    /**
     * 及格分数
     */
    private int passScores;
    /**
     * 考试时间
     */
    private java.util.Date examTime;
    /**
     * 考试计时,分钟
     */
    private int timing;
    /**
     * 装备,多选,逗号分割
     */
    private String equip;
    /**
     * 岗位,多选,逗号分割
     */
    private String position;
    /**
     * 装备岗位
     */
    private String equipPosition;
    /**
     * 项目id
     */
    private String studyId;
    /**
     * 计划id
     */
    private String studyCourseId;
    /**
     * 原表id
     */
    private String oldId;
    /**
     * 题目数量
     */
    private int problemCount;
    /**
     * 总得分,实际得分
     */
    private int scoresCount;

    /**
     * 下发时保存下发的教学管理员
     */
    private String sendBy;
    /**
     * 删除状态（0，正常，1已删除）
     */
    @Excel(name = "删除状态", width = 15,dicCode="del_flag")
    private String delFlag = "0";
    /**
     * 试卷状态（0，未完成，1已完成）
     */
    private String examStatus = "0";
    /**
     * 批次(uuid)
     */
    private String batchId;
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
