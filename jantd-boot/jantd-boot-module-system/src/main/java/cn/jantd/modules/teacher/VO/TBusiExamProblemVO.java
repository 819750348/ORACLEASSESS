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
import java.util.List;

/**
 * @Author qiji
 * @Date:  2020-03-03
 */
@Data
public class TBusiExamProblemVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 标题
     */
    private String problemTitle;
    /**
     * 类型,1单选,2复选,3判断
     */
    private String problemType;
    /**
     * 试题分类,理论、使用操作、维护操作
     */
    private String problemClassify;
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
     * 答案json串
     */
    private String answerStr;
    /**
     * 正确答案
     */
    private String successAnswer;
    /**
     * 分数
     */
    private int scoresCount;
    /**
     * 原表id
     */
    private String oldId;
    /**
     * 题目排序
     */
    private Integer problemOrder;
    /**
     * 学生答案
     */
    private String problemAnswer;
    /**
     * 装备岗位str
     */
    private String equipPositionStr;
    public String getEquipPositionStr(){
        this.equipPositionStr = DictUtil.getEquipPositionStr(this.equipPosition);
        return this.equipPositionStr;
    }
    /**
     * 装备名称
     */
    private String equipStr;
    /**
     * 岗位名称
     */
    private String positionStr;
    /**
     * 前端复选框字段
     */
    private boolean visibleCheck;
    /**
     * 正确答案序号集合
     */
    private String isRightStr;

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
