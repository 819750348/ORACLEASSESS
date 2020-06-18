package cn.jantd.modules.teacher.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 答案表
 * @Author qiji
 * @Date:  2020-03-03
 */
@Data
public class TBusiProblemAnswerVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 题目id
     */
    private String problemId;
    /**
     * 试卷id
     */
    private String examId;
    /**
     * 选项内容
     */
    private String answerStr;
    /**
     * 选项排序
     */
    private int answerOrder;
    /**
     * 是否正确答案，1是0否
     */
    private String isRight;

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

}
