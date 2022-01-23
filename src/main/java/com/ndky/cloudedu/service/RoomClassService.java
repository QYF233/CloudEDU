package com.ndky.cloudedu.service;

import com.ndky.cloudedu.entity.RoomClass;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kiko
 * @since 2021-10-30
 */
public interface RoomClassService extends IService<RoomClass> {

    void setRoomClass(Long rid, Long cid);
}
