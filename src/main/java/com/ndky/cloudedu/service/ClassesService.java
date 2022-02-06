package com.ndky.cloudedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ndky.cloudedu.entity.Classes;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kiko
 * @since 2022-02-05
 */
public interface ClassesService extends IService<Classes> {
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
    Page<Classes> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 添加角色
     */
    boolean create(Classes clazz);
}
