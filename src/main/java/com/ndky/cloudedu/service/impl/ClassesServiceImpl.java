package com.ndky.cloudedu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ndky.cloudedu.entity.Classes;
import com.ndky.cloudedu.mapper.ClassesMapper;
import com.ndky.cloudedu.service.ClassesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kiko
 * @since 2022-02-05
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements ClassesService {

    @Override
    public Page<Classes> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<Classes> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Classes> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Classes> lambda = wrapper.lambda();
        if (StrUtil.isNotEmpty(keyword)) {
            lambda.like(Classes::getClassName, keyword);
        }
        return page(page, wrapper);
    }

    @Override
    public boolean create(Classes clazz) {
        return save(clazz);
    }

/*    @Override
    public Page<Class> getOpenClasses(Integer pageSize, Integer pageNum) {
        Page<Class> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Class> lambda = wrapper.lambda();
        lambda.eq(Class::getState,1);
        return page(page, wrapper);
    }*/
}
