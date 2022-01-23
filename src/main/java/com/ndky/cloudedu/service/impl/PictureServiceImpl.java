package com.ndky.cloudedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ndky.cloudedu.entity.Picture;
import com.ndky.cloudedu.mapper.PictureMapper;
import com.ndky.cloudedu.service.PictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kiko
 * @since 2021-10-24
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {
    @Autowired
    PictureMapper pictureMapper;

    @Override
    public List<Picture> selectPicture(Integer number) {
        QueryWrapper<Picture> wrapper = new QueryWrapper<>();
        wrapper.last("limit " + number);
        return pictureMapper.selectList(wrapper);
    }
}
