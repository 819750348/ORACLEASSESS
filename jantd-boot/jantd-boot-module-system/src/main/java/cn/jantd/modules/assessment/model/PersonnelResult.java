package cn.jantd.modules.assessment.model;

import lombok.Data;

import java.util.List;

/**
 * @ProjectName: jantd-boot-parent
 * @Package: cn.jantd.modules.assessment.model
 * @ClassName: Personnel
 * @Author: admin
 * @Description:
 * @Date: 2020/5/20 16:26
 * @Version: 1.0
 */
@Data
public class PersonnelResult {
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getPersonnelList() {
        return personnelList;
    }

    public void setPersonnelList(List personnelList) {
        this.personnelList = personnelList;
    }

    public int total = 0;

    public List personnelList;
}
