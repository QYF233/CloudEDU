package com.ndky.cloudedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ndky.cloudedu.entity.Colleges;
import com.ndky.cloudedu.mapper.CollegesMapper;
import com.ndky.cloudedu.service.CollegesService;
import com.ndky.cloudedu.vo.CollegesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kiko
 * @since 2022-02-05
 */
@Service
public class CollegesServiceImpl extends ServiceImpl<CollegesMapper, Colleges> implements CollegesService {
    @Autowired
    public CollegesMapper collegesMapper;

    @Override
    public List<CollegesVo> getAllClassesOption() {
        return collegesMapper.getAllColleges();
    }
}
