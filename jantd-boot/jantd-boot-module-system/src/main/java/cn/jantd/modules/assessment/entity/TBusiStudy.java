package cn.jantd.modules.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.entity
 * @ClassName: TBusiStudy
 * @Author: admin
 * @Description:
 * @Date: 2020/5/28 15:08
 * @Version: 1.0
 */
@Data
@TableName("t_busi_study_temp")
public class TBusiStudy  implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.UUID)
    private String id;
    private String name;
    private String studyType;
    private String equip;
    private String position;
    private String StudyTime;
    private String equipPosition;
}
