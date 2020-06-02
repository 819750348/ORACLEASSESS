package cn.jantd.modules.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.entity
 * @ClassName: TBusiExamProblemTemp
 * @Author: admin
 * @Description:
 * @Date: 2020/5/29 13:23
 * @Version: 1.0
 */
@Data
@TableName("t_busi_exam_problem_temp")
public class TBusiExamProblemTemp {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEquipPosition() {
        return equipPosition;
    }

    public void setEquipPosition(String equipPosition) {
        this.equipPosition = equipPosition;
    }

    public String getScoresCount() {
        return scoresCount;
    }

    public void setScoresCount(String scoresCount) {
        this.scoresCount = scoresCount;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }



    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }



    public String getProblemClassify() {
        return problemClassify;
    }

    public void setProblemClassify(String problemClassify) {
        this.problemClassify = problemClassify;
    }

    public String getAnswerStr() {
        return answerStr;
    }

    public void setAnswerStr(String answerStr) {
        this.answerStr = answerStr;
    }

    public String getSuccessAnswer() {
        return successAnswer;
    }

    public void setSuccessAnswer(String successAnswer) {
        this.successAnswer = successAnswer;
    }

    @TableId(type = IdType.UUID)
    private String id;

    private String problemTitle;
    private String problemType;
    private String equip;
    private String position;
    private String equipPosition;
    private String scoresCount;
    private String delFlag;
    private String createBy;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String problemClassify;
    private String answerStr;
    private String successAnswer;
}
