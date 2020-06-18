package cn.jantd.modules.teacher.VO;

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
 * @Description: 试卷\表VO
 * @Author qiji
 * @Date:  2020-03-03
 * @Version: V1.0
 */
@Data
public class TBusiExamVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
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
     * 及格分数
     */
    private int pass;
    /**
     * 考试时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date examTime;
    /**
     * 考试计时,分钟
     */
    private int timing;
    /**
     * 装备
     */
    private String equip;

    /**
     * 装备名称
     */
    private String equipStr;
    /**
     * 岗位
     */
    private String position;
    /**
     * 岗位名称
     */
    private String positionStr;
    /**
     * 装备岗位，原装备和岗位两个字段合并
     */
    private String equipPosition;
    /**
     * 装备岗位str
     */
    private String equipPositionStr;
    public String getEquipPositionStr(){
        this.equipPositionStr = DictUtil.getEquipPositionStr(this.equipPosition);
        return this.equipPositionStr;
    }
    /**
     * 项目id
     */
    private String studyId;
    /**
     * 项目id
     */
    private String studyStr;
    /**
     * 计划id
     */
    private String studyCourseId;
    /**
     * 计划id
     */
    private String studyCourseStr;
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
    private String delFlag = "0";
    /**
     * 批次(uuid)
     */
    private String batchId;
    /**
     * 试卷状态（0，未完成，1已完成）
     */
    private String examStatus = "0";
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
