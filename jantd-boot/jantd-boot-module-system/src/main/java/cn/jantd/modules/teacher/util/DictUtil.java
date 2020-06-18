package cn.jantd.modules.teacher.util;

import cn.jantd.modules.teacher.VO.*;
import cn.jantd.modules.teacher.entity.TBusiExamProblemTemp;
import cn.jantd.modules.teacher.entity.TBusiStudyTemp;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class DictUtil {
    private static final String[] EQUIP_POSTION = {"武器装备_总体","指控车_指挥","指控车_操作",
            "武器装备_维修","发射车_操作","发射车_维修","雷达车_操作","雷达车_维修"};

    /**
     * 装备岗位根据下标查询字符(多选)
     * @param equipPositionIndex
     * @return
     */
    public static String getEquipPositionStr(String equipPositionIndex){
        StringBuffer sb = new StringBuffer();
        if (null == equipPositionIndex || "".equals(equipPositionIndex)) {
            return "";
        } else {
            String[] epArray = equipPositionIndex.split(",");
            for (String ep : epArray) {
                if (null == ep || "".equals(ep)) {
                    continue;
                }
                for (int j = 0; j < EQUIP_POSTION.length; j++) {
                    Integer num = Integer.parseInt(ep) - 1;
                    if (j == num) {
                        if (sb.length() > 0) {
                            sb.append(",");
                        }
                        sb.append(EQUIP_POSTION[j]);
                        break;
                    }
                }
            }
            String str = sb.length()>0?sb.toString():"";
            return str;
        }
    }

    /**
     * 装备岗位根据字符查询下标(多选)
     * @param equipPositionStr
     * @return
     */
    public static String getEquipPositionIndex(String equipPositionStr){
        StringBuffer sb = new StringBuffer();
        if (null == equipPositionStr || "".equals(equipPositionStr)) {
            return "";
        } else {
            String[] epArray = equipPositionStr.split(",");
            for (String ep : epArray) {
                if (null == ep || "".equals(ep)) {
                    continue;
                }
                for (int j = 0; j < EQUIP_POSTION.length; j++) {
                    if (EQUIP_POSTION[j].equals(ep)) {
                        if (sb.length() > 0) {
                            sb.append(",");
                        }
                        sb.append(String.valueOf((j+1)));
                        break;
                    }
                }
            }
            String str = sb.length()>0?sb.toString():"";
            return str;
        }
    }

}
