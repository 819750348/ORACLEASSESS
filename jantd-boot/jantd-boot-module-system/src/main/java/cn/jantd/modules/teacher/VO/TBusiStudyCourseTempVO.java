package cn.jantd.modules.teacher.VO;

import cn.jantd.core.poi.excel.annotation.Excel;
import cn.jantd.modules.teacher.util.DictUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Author qiji
 * @Date:  2020-03-03
 * @Version: V1.0
 */
@Data
public class TBusiStudyCourseTempVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 学习和训练项目id
     */
    private String studyId;
    /**
     * 学习和训练项目名称
     */
    private String studyName;
    /**
     * 创建人id(教学管理员)
     */
    private String teacherId;
    /**
     * 学习类型，1理论学习2使用操作训练3维护维修操作4训练
     */
    private String studyType;
    /**
     * 学习类型str
     */
    private String studyTypeStr;
    public String getStudyTypeStr(){
        if(this.studyType != null && !"".equals(this.studyType)){
            if(this.studyType.equals("1")){
                this.studyTypeStr = "理论学习";
            }else if(this.studyType.equals("2")){
                this.studyTypeStr = "使用操作训练";
            }else if(this.studyType.equals("3")){
                this.studyTypeStr = "维护维修操作";
            }else if(this.studyType.equals("4")){
                this.studyTypeStr = "训练";
            }else{
                this.studyTypeStr = "";
            }
        }else{
            this.studyTypeStr = "";
        }
        return this.studyTypeStr;
    }
    /**
     * 装备，废弃
     */
    private String equip;
    /**
     * 岗位，多选，逗号分割，废弃
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
        System.out.println(this.equipPosition);
        this.equipPositionStr = DictUtil.getEquipPositionStr(this.equipPosition);
        System.out.println(this.equipPositionStr);
        return this.equipPositionStr;
    }
    /**
     * 课时，数字
     */
    private Integer studyTime;
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
