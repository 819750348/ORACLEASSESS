package cn.jantd.modules.system.service.impl;

import cn.jantd.modules.system.entity.SysUser;
import cn.jantd.modules.system.mapper.SysUserMapper;
import cn.jantd.modules.system.service.IRegisterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements IRegisterService {
    @Override
    public void register(SysUser user) {
       this.save(user);
    }
}
