package com.ndky.cloudedu.service.impl;

import com.ndky.cloudedu.entity.UserRole;
import com.ndky.cloudedu.mapper.UserRoleMapper;
import com.ndky.cloudedu.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public boolean addRole(Long uid, Long rid) {
        UserRole userRole = new UserRole();
        userRole.setUid(uid);
        userRole.setRid(rid);
        return userRoleMapper.insert(userRole) == 1;
    }

}
