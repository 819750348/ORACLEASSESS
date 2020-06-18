package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TBusiStudyTempVO;
import cn.jantd.modules.teacher.entity.TBusiStudyTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  学习和训练项目表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-04-20
 */
@Repository
public interface TBusiStudyTempMapper extends BaseMapper<TBusiStudyTemp>  {


    /**
     * 分页列表mysql
     * @param pageParam 页面分页参数,数字型(pageSize,pageNo),其他参数,字符型(包括排序等)
     * @return List
     */
    List<TBusiStudyTempVO> searchPageForMysql(Map<String, Object> pageParam);

    /**
     * 总数
     * @param pageParam 参数,字符型(查询条件)
     * @return List
     */
    Integer searchPageTotal(Map<String, Object> pageParam);

}
