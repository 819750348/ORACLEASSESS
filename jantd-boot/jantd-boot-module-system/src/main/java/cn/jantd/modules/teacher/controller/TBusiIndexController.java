package cn.jantd.modules.teacher.controller;

import cn.jantd.core.api.vo.Result;
import cn.jantd.core.system.query.QueryGenerator;
import cn.jantd.modules.system.entity.SysDictItem;
import cn.jantd.modules.system.service.ISysDictItemService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author qiji
 * @date 2020-03-03
 */
@RestController
@RequestMapping("/teacher/index")
@Api(tags = "教师端首页")
@Slf4j
public class TBusiIndexController {

    @Autowired
    private ISysDictItemService sysDictItemService;

    /**
     * 考试分析
     * @return
     */
    //@AutoLog(value = "考试分析")
    @ApiOperation(value = "考试分析")
    @GetMapping(value = "/analysis")
    public JSONArray analysis() {
        JSONArray ja = new JSONArray();
        JSONObject jo1 = new JSONObject();
        jo1.put("value","65");
        jo1.put("name","张骁");
        ja.add(jo1);
        JSONObject jo2 = new JSONObject();
        jo2.put("value","74");
        jo2.put("name","蒲卉子");
        ja.add(jo2);
        JSONObject jo3 = new JSONObject();
        jo3.put("value","81");
        jo3.put("name","彭雅琪");
        ja.add(jo3);
        JSONObject jo4 = new JSONObject();
        jo4.put("value","69");
        jo4.put("name","罗秋蒙");
        ja.add(jo4);
        JSONObject jo5 = new JSONObject();
        jo5.put("value","91");
        jo5.put("name","邹诗雨");
        ja.add(jo5);
        JSONObject jo6 = new JSONObject();
        jo6.put("value","77");
        jo6.put("name","徐牧");
        ja.add(jo6);
        return ja;
    }

    /**
     * 查询四种分类标签(装备岗位专业分组,去掉分组or d.dict_code='staff_group')
     * @return
     *//*
    //@AutoLog(value = "查询分类标签")
    @ApiOperation(value = "查询分类标签")
    @GetMapping(value = "/queryEPSG")
    public JSONArray queryEPSG() {
        List<SysDictItemVO> dilist = sysDictItemService.queryEPSG();
        JSONArray result = new JSONArray();
        StringBuffer str = new StringBuffer();
        str.append("[");
        for (SysDictItemVO vo : dilist){
            str.append("{\"id\":\""+vo.getId()+"\",\"dictCode\":\""+vo.getDictCode()+"\",\"itemText\":\""+vo.getItemText()+"\",\"itemValue\":\""+vo.getItemValue()+"\"}");
        }
        str.append("]");
        result = JSON.parseArray(str.toString());
        return result;
    }*/

}
