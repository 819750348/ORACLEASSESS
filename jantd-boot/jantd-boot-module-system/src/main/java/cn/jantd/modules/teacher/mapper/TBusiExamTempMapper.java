package cn.jantd.modules.teacher.mapper;

import cn.jantd.modules.teacher.VO.TBusiExamVO;
import cn.jantd.modules.teacher.entity.TBusiExamTemp;
import cn.jantd.modules.teacher.entity.TSysStaff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  试卷表 Mapper 接口
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
@Repository
public interface TBusiExamTempMapper extends BaseMapper<TBusiExamTemp>  {

    /**
     * 根据教师人员id查询名下试卷列表
     */
    public List<TBusiExamTemp> getExamByStaffid(@Param("staffid") String staffid);

    /**
     * 根据id查询试卷详情
     */
    TBusiExamVO examDetail(@Param("examid") String examid);

    /**
     * 分页查询试卷列表
     */
    List<TBusiExamVO> searchExamPage(Map<String, Object> pageParam);

    /**
     * mysql分页查询试卷列表
     */
    List<TBusiExamVO> searchExamPageForMysql(Map<String, Object> pageParam);


    /**
     * 查询试卷数据总数
     */
    Integer searchExamTotal(Map<String, Object> pageParam);
}
