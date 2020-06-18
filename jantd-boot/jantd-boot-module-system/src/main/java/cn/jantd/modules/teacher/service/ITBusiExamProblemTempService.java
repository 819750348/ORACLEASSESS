package cn.jantd.modules.teacher.service;

import cn.jantd.modules.teacher.VO.TBusiExamProblemVO;
import cn.jantd.modules.teacher.VO.TSysStaffVO;
import cn.jantd.modules.teacher.entity.TBusiExamProblemTemp;
import cn.jantd.modules.teacher.entity.TSysStaff;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  题目表 服务类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-03
 */
public interface ITBusiExamProblemTempService extends IService<TBusiExamProblemTemp> {

    /**
     * 分页查询题目
     */
    Map<String,Object> searchProblemPage(Map<String, Object> pageParam);

    /**
     * excel导入题目信息
     */
    Map<String,String> importExcelToProblem(InputStream file) throws Exception;

    /**
     * 唯一性验证
     */
    boolean checkProblem(String username);

    /**
     * 获取详情页面
     */
    JSONObject getDetailByProblem(TBusiExamProblemTemp problem);

    /**
     * 删除题库和答案
     */
    Map<String, String> removeProblemAndAnswerByIds(List<String> ids) throws Exception;

    /**
     * 批量删除题库和答案
     */
    Map<String, String> removeProblemAndAnswerById(String id) throws Exception;

    void addProblemAndAnswer(JSONObject jsonObject);
}