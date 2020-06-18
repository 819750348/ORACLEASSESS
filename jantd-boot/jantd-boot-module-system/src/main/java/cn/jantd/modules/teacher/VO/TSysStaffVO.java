package cn.jantd.modules.teacher.VO;

import cn.jantd.modules.teacher.util.DictUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class TSysStaffVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private String id;
    /**
     * 人员名称
     */
    private String name;
    /**
     * 登录名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 人员分组
     */
    private String staffGroup;
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
     * 人员类型：1教学管理员2学员3管理员
     */
    private String staffType;
    /**
     * 试卷id
     */
    private String examId;
    /**
     * 发送状态1发送0未发送
     */
    private String sendStatus;

    private String sendStatusStr;
    public String getSendStatusStr() {
        if ("1".equals(this.sendStatus)){
            return "已发送";
        }else if("0".equals(this.sendStatus)){
            return "未发送";
        }else{
            return "";
        }
    }
    /**
     * 人员类型名称
     */
    private String staffTypeStr;

    public String getStaffTypeStr() {
        if ("1".equals(this.staffType)){
            return "教学管理员";
        }else if ("2".equals(this.staffType)){
            return "学员";
        }else if("3".equals(this.staffType)){
            return "管理员";
        }else{
            return "";
        }
    }

    /**
     * 课程进度
     */
    private int stepProgree;
    /**
     * 课程总数
     */
    private int courseCount;
    /**
     * 课程列表
     */
    private List<String> stepInfo;
    /**
     * 学员名称(教学进度用属性)
     */
    private String studentName;
    /**
     * 教学管理员id(学员专用属性)
     */
    private String teacherId;
    /**
     * 试卷id
     */
    private String examName;
    /**
     * 试卷总分
     */
    private int totalScores;
    /**
     * 前端复选框字段
     */
    private boolean visibleCheck;
    /**
     * 下发试卷数量
     */
    private int examCount;
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
