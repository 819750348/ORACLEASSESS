package cn.jantd.modules.teacher.service;

import cn.jantd.modules.teacher.entity.TBusiStudyTemp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  学习和训练项目表 服务类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-05
 */
public interface ITBusiStudyTempService extends IService<TBusiStudyTemp> {

    /**
     * 分页查询
     */
    Map<String, Object> searchPage(Map<String, Object> pageParam);

    Map<String, Object> getStudySelect(Map<String, Object> pageParam);
}