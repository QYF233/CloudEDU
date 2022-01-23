package com.ndky.cloudedu.service.impl;

import com.ndky.cloudedu.entity.RoomClass;
import com.ndky.cloudedu.mapper.RoomClassMapper;
import com.ndky.cloudedu.service.RoomClassService;
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
public class RoomClassServiceImpl extends ServiceImpl<RoomClassMapper, RoomClass> implements RoomClassService {
    @Autowired
    RoomClassMapper roomClassMapper;


    @Override
    public void setRoomClass(Long rid, Long cid) {

    }
}
