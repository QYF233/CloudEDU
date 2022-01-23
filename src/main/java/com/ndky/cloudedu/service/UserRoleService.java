package com.ndky.cloudedu.service;

import com.ndky.cloudedu.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
public interface UserRoleService extends IService<UserRole> {

    boolean addRole(Long id, Long l);
}
