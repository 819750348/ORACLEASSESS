package cn.jantd.modules.teacher.VO;


import cn.jantd.modules.teacher.entity.TSysStaff;
import cn.jantd.modules.teacher.util.DictUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

/**
 * @Author qiji
 * @date 2020-03-03
 */
@Data
public class TBusiExamStaffVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 标题
     */
    private String name;
    /**
     * 试卷类型标签,多选,逗号分割
     */
    private String examType;
    /**
     * 总分
     */
    private int totalScores;
    /**
     * 批次(uuid)
     */
    private String batchId;
    /**
     * 考试时间,分钟
     */
    private int examTime;
    /**
     * 装备,多选,逗号分割
     */
    private String examEquip;
    /**
     * 岗位,多选,逗号分割
     */
    private String examPosition;
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
     * 题目数量
     */
    private int problemCount;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private java.util.Date updateTime;
    /**
     * 对应的指定学员集合
     */
    private List<TSysStaffVO> studentList;
    /**
     * 对应的指定学员名称字符串
     */
    private String students;
    /**
     * 发送状态
     */
    private String sendStatusStr;

}
