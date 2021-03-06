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
 * @Description: 题目copy表
 * @Author qiji
 * @Date:  2020-03-03
 * @Version: V1.0
 */
@Data
@TableName("t_busi_exam_problem_copy")
public class TBusiExamProblemCopy implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 原表id
     */
    private String oldId;
    /**
     * 标题
     */
    private String problemTitle;
    /**
     * 题目排序
     */
    private Integer problemOrder;
    /**
     * 类型,单选,多选,判断
     */
    private String problemType;
    /**
     * 试题分类,理论、使用操作、维护操作
     */
    private String problemClassify;
    /**
     * 学生答案
     */
    private String problemAnswer;
    /**
     * 答案json串
     */
    private String answerStr;
    /**
     * 正确答案
     */
    private String successAnswer;
    /**
     * 装备,多选,逗号分割
     */
    private String equip;
    /**
     * 岗位,多选,逗号分割
     */
    private String position;
    /**
     * 装备岗位，原装备和岗位两个字段合并
     */
    private String equipPosition;
    /**
     * 分数
     */
    private int scoresCount;
    /**
     * 得分,每道题的得分
     */
    private int scores;
    /**
     * 试卷copy表id
     */
    private String examId;
    /**
     * 人员id
     */
    private String staffId;
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
