package com.ndky.cloudedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.entity.Role;
import com.ndky.cloudedu.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ndky.cloudedu.vo.UserVo;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
public interface UserService extends IService<User> {
    /**
     * 注册
     *
     * @param user 新用户
     * @return 新用户
     */
    User register(User user);

    /**
     * 添加角色绑定
     *
     * @param uid 用户id
     * @param rid 角色id
     * @return
     */
    boolean addRole(Long uid, Long rid);

    /**
     * 根据用户名或昵称分页查询用户
     *
     * @param keyword  关键词
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @return 用户页面
     */
    Page<User> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 获取学生及班级信息
     *
     * @param keyword  关键词
     * @param pageSize 页面大小
     * @param pageNum  页码
     * @return 用户页面
     */
    Page<UserVo> getUserAll(String keyword, Integer pageSize, Integer pageNum);


    Role getUserRole(Long uid);
}
