package com.ndky.cloudedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.entity.Class;
import com.ndky.cloudedu.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author kiko
 * @since 2021-10-30
 */
public interface RoomService extends IService<Room> {
    /**
     * 获取公共教室
     *
     * @return
     */
    Page<Room> list(String keyword, Integer pageSize, Integer pageNum, Integer state);

}
