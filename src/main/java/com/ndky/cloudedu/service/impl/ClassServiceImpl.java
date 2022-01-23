package com.ndky.cloudedu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.entity.Class;
import com.ndky.cloudedu.mapper.ClassMapper;
import com.ndky.cloudedu.service.ClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Override
    public Page<Class> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<Class> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Class> lambda = wrapper.lambda();
        if (StrUtil.isNotEmpty(keyword)) {
            lambda.like(Class::getClassName, keyword);
        }
        return page(page, wrapper);
    }

    @Override
    public boolean create(Class clazz) {
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
