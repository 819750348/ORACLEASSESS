package cn.jantd.modules.system.controller;

import cn.jantd.core.api.vo.Result;
import cn.jantd.core.util.PasswordUtil;
import cn.jantd.core.util.oConvertUtils;
import cn.jantd.modules.system.entity.SysUser;
import cn.jantd.modules.system.service.impl.RegisterServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/sys")
@Api(tags = "用户注册")
@Slf4j
public class RegisterController {
    @Autowired
    RegisterServiceImpl registerService;
    @PostMapping(value = "/register")
    @ApiOperation("登录接口")
    public void register(@RequestBody JSONObject jsonObject){
        Result<SysUser> result = new Result<>();
        try {
            SysUser user = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
            // 设置创建时间
            user.setCreateTime(new Date());
            String salt = oConvertUtils.randomGen(8);
            user.setSalt(salt);
            String passwordEncode = PasswordUtil.encrypt(user.getUsername(), user.getPassword(), salt);
            user.setPassword(passwordEncode);
            user.setStatus(1);
            user.setDelFlag("0");
            registerService.save(user);
            result.success("添加成功！");
        } catch (Exception e) {
            result.success("添加失败！");
            e.printStackTrace();
        }
    }
}
