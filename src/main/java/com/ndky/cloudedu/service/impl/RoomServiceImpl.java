package com.ndky.cloudedu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ndky.cloudedu.entity.Room;
import com.ndky.cloudedu.mapper.RoomMapper;
import com.ndky.cloudedu.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kiko
 * @since 2021-10-30
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
@Autowired
RoomMapper roomMapper;

    @Override
    public Page<Room> list(String keyword, Integer pageSize, Integer pageNum, Integer state) {
        Page<Room> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Room> lambda = wrapper.lambda();
        lambda.like(Room::getName, keyword).eq(Room::getState, state);
        return page(page, wrapper);
    }

    @Override
    public void setStatus(int i) {

    }
}
