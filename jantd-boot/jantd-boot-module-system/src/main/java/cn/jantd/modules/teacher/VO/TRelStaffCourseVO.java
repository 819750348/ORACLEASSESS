package cn.jantd.modules.teacher.VO;

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
 * @Description: 人员课程关联表VO
 * @Author qiji
 * @Date:  2020-03-03
 * @Version: V1.0
 */
@Data
public class TRelStaffCourseVO implements Serializable {
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
     * 人员名称
     */
    private String staffName;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 项目id
     */
    private String studyId;
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
     * 课程状态 1完成0未完成
     */
    private String courseStatus;

    /**
     * 总课程数量
     */
    private Integer courseNum;
    /**
     * 已完成课程数
     */
    private Integer courseCompleteNum;
    /**
     * 进度百分比
     */
    private String percentage;

    /**
     * 排序
     */
    private int courseOrder;

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
