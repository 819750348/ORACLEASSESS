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
 * @Description: 试卷copy表
 * @Author qiji
 * @Date:  2020-03-03
 * @Version: V1.0
 */
@Data
public class TBusiExamCopyVO implements Serializable {
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
     * 人员name
     */
    private String staffName;
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
    private Integer totalScores;
    /**
     * 及格分数
     */
    private Integer pass;
    /**
     * 考试时间
     */
    private java.util.Date examTime;
    /**
     * 考试计时,分钟
     */
    private Integer timing;
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
     * 装备岗位str
     */
    private String equipPositionStr;
    public String getEquipPositionStr(){
        this.equipPositionStr = DictUtil.getEquipPositionStr(this.equipPosition);
        return this.equipPositionStr;
    }
    /**
     * 原表id
     */
    private String oldId;
    /**
     * 题目数量
     */
    private Integer problemCount;
    /**
     * 总得分,实际得分
     */
    private Integer scoresCount;
    /**
     * 总得分str,页面需要根据成绩拼接通过未通过
     */
    private String scoresStr;

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
