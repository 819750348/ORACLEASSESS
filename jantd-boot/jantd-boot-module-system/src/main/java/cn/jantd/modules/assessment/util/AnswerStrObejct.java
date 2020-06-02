package cn.jantd.modules.assessment.util;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.util
 * @ClassName: AnswerStrObejct
 * @Author: admin
 * @Description:
 * @Date: 2020/5/31 23:12
 * @Version: 1.0
 */
public class AnswerStrObejct {
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getAnswerStr() {
        return answerStr;
    }

    public void setAnswerStr(String answerStr) {
        this.answerStr = answerStr;
    }

    private String order;
    private String No;
    private String answerStr;
}
