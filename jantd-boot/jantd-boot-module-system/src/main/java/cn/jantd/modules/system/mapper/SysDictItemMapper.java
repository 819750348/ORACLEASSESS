package cn.jantd.modules.system.mapper;

import cn.jantd.modules.system.entity.SysDictItem;
import cn.jantd.modules.system.vo.SysDictItemVO;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @Author xiagf
 * @date 2019-07-04
 */
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {
    /**
     * 查询字典属相
     * @param mainId
     * @return
     */
    @Select("SELECT * FROM SYS_DICT_ITEM WHERE DICT_ID = #{mainId}")
    public List<SysDictItem> selectItemsByMainId(String mainId);

    /**
     * 询四种分类标签(装备岗位专业分组)
     * @return
     */
    @Select("select t.*,d.dict_code,d.id dict_id from sys_dict_item t left join sys_dict d on d.id = t.dict_id where d.dict_code='equip' or d.dict_code='position' or d.dict_code='specialities' or d.dict_code='staff_group'")
    public List<SysDictItemVO> queryEPSG();


}
