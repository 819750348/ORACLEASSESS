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
 * @Description: 试卷下发人员关联表
 * @Author qiji
 * @Date:  2020-03-03
 * @Version: V1.0
 */
@Data
@TableName("t_rel_exam_staff")
public class TRelExamStaff implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 指定学员id
     */
    private String studentId;
    /**
     * 试卷id
     */
    private String examId;
    /**
     * 批次(uuid)
     */
    private String batchId;
    /**
     * 下发状态 1发送0未发送
     */
    private String sendStatus;
    /**
     * 下发人员id
     */
    private String sendBy;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 删除状态（0，正常，1已删除）
     */
    @Excel(name = "删除状态", width = 15,dicCode="del_flag")
    private String delFlag = "0";
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
