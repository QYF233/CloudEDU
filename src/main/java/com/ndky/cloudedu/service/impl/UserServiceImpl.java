package com.ndky.cloudedu.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.entity.Role;
import com.ndky.cloudedu.entity.User;
import com.ndky.cloudedu.entity.UserRole;
import com.ndky.cloudedu.mapper.RoleMapper;
import com.ndky.cloudedu.mapper.UserMapper;
import com.ndky.cloudedu.mapper.UserRoleMapper;
import com.ndky.cloudedu.service.RoleService;
import com.ndky.cloudedu.service.UserRoleService;
import com.ndky.cloudedu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ndky.cloudedu.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public boolean addRole(Long uid, Long rid) {
        UserRole userRole = new UserRole();
        userRole.setUid(uid);
        userRole.setRid(rid);

        return userRoleMapper.insert(userRole) == 1;
    }

    @Override
    public User register(User user) {
        //查询是否有相同用户名的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUsername, user.getUsername());
        List<User> umsAdminList = list(wrapper);
        if (umsAdminList.size() > 0) {
            return null;
        }
        user.setCrateTime(LocalDateTime.now());
        user.setStatus(1);
//        user.setNote("注册用户");
        /*设置默认头像*/
        user.setAvatar("1.jpg");
        //将密码进行加密操作
        user.setPassword(SecureUtil.md5(user.getPassword()));
        baseMapper.insert(user);
        return user;
    }

    @Override
    public Page<User> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<User> lambda = wrapper.lambda();
        if (StrUtil.isNotEmpty(keyword)) {
            lambda.like(User::getUsername, keyword);
            lambda.or().like(User::getUsername, keyword);
        }
        return page(page, wrapper);
    }

    @Override
    public Page<UserVo> getUserAll(String keyword, Integer pageSize, Integer pageNum) {
        Page<UserVo> page = new Page<>(pageNum, pageSize);
        return userMapper.getUserAll(page, keyword);
    }

    @Override
    public Role getUserRole(Long uid) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        UserRole userRole = userRoleService.getOne(queryWrapper);
        Role role = roleService.getById(userRole.getRid());
        return role;
    }


}
