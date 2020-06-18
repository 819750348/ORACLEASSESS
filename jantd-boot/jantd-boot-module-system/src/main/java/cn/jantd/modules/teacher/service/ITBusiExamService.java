package cn.jantd.modules.teacher.service;

import cn.jantd.modules.assessment.model.PersonnelResult;
import cn.jantd.modules.teacher.VO.TBusiExamStaffVO;
import cn.jantd.modules.teacher.VO.TBusiExamVO;
import cn.jantd.modules.teacher.entity.TBusiExamTemp;
import cn.jantd.modules.teacher.entity.TSysStaff;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  试卷表 服务类
 * </p>
 *
 * @Author qiji
 * @date 2020-03-05
 */
public interface ITBusiExamService extends IService<TBusiExamTemp> {

    /**
     * 根据教师人员id查询名下试卷列表及对应的下发人员
     */
    List<TBusiExamStaffVO> getStaffById(String id);

    /**
     * 查询学员最近的试卷,不分页
     * @param id
     * @return
     */
    List<TBusiExamVO> queryLastExamByStudent(String id);

    /**
     * 查看学员最近的试卷
     * @param examid
     * @param staffid
     * @return
     */
    JSONObject detailExamByStaff(String examid, String staffid);

    /**
     * 开始答题
     * @param examid
     * @param staffid
     * @return
     */
    JSONObject toEditByExamAndStaff(String examid, String staffid);

    /**
     * 答题提交
     * @param paper
     * @return
     */
    Map<String, Object> editExamScores(JSONObject paper);

    /**
     * 根据学员id分页查询最近的试卷
     * @param pageParam
     * @return
     */
    Map<String, Object> searchLastByStaff(Map<String, Object> pageParam);

    /**
     * 根据学员id查询最近的试卷和排名(对比同一批次的试卷)
     * @param studentid
     * @return
     */
    Map<String, Object> searchBatchByStaff(String studentid);

    /**
     * 生成试卷
     * @param teacherId
     * @return
     */
    Map<String, Object> createExam(String teacherId);

    /**
     * 查看试卷
     * @param examid
     * @return
     */
    JSONObject detailExamById(String examid);

    /**
     * 分页查询试卷列表
     * @param pageParam
     * @return
     */
    Map<String, Object> queryPageList(Map<String, Object> pageParam);

    /**
     * 批量删除试卷
     * @param ids
     * @return
     */
    Map<String, String> removeExamAndRelByIds(List<String> ids);

    /**
     * 下发试卷
     * @param examId
     * @param teacherId
     * @return
     */
    Map<String, String> sendExam(String examId,String teacherId);

    /**
     * 分页查询试卷列表
     * @param pageParam
     * @return
     */
    PersonnelResult searchExamPage(Map<String, Object> pageParam);


}