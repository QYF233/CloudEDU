package com.ndky.cloudedu.service;

import com.ndky.cloudedu.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kiko
 * @since 2021-10-24
 */
public interface PictureService extends IService<Picture> {
    /**
     * 获取跑马灯数据
     * @param number 查询数
     * @return
     */
    List<Picture> selectPicture(Integer number);
}
