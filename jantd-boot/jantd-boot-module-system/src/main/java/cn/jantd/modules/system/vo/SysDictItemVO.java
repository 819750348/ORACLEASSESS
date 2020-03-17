package cn.jantd.modules.system.vo;

import cn.jantd.core.annotation.Dict;
import cn.jantd.core.poi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @Author qiji
 * @date 2020-03-03
 */
@Data
public class SysDictItemVO {
    /**
     * id
     */
    private String id;

    /**
     * 字典id
     */
    private String dictId;
    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典项文本
     */
    private String itemText;

    /**
     * 字典项值
     */
    private String itemValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态（1启用 0不启用）
     */
    private Integer status;


    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

}
