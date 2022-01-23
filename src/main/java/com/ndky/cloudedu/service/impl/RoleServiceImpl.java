package com.ndky.cloudedu.service.impl;

import com.ndky.cloudedu.entity.Role;
import com.ndky.cloudedu.mapper.RoleMapper;
import com.ndky.cloudedu.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
