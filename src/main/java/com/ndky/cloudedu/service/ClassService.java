package com.ndky.cloudedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
public interface ClassService extends IService<Class> {
    /**
     * 获取公共教室
     *
     * @return
     */
/*
    Page<Class> getOpenClasses(Integer pageSize, Integer pageNum);
*/

    /**
     * 分页获取角色列表
     */
    Page<Class> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 添加角色
     */
    boolean create(Class clazz);

}
