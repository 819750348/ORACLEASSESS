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
 * @Author qiji
 * @date 2020-03-03
 */
@Data
public class TBusiStudyTempVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 学习类型，1理论学习2使用操作训练3维护维修操作4训练
     */
    private String studyType;
    /**
     * 学习类型名称
     */
    private String studyTypeStr;

    public String getStudyTypeStr() {
        if ("1".equals(this.studyType)){
            return "理论学习";
        }else if ("2".equals(this.studyType)){
            return "使用操作训练";
        }else if("3".equals(this.studyType)){
            return "维护维修操作";
        }else if("4".equals(this.studyType)){
            return "训练";
        }else{
            return "";
        }
    }
    /**
     * 装备
     */
    private String equip;
    /**
     * 岗位
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
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;

}
