package com.ndky.cloudedu;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.common.utils.Util;
import com.ndky.cloudedu.entity.Classes;
import com.ndky.cloudedu.mapper.ClassesMapper;
import com.ndky.cloudedu.mapper.CollegesMapper;
import com.ndky.cloudedu.mapper.SpecialityMapper;
import com.ndky.cloudedu.mapper.UserMapper;
import com.ndky.cloudedu.service.ClassesService;
import com.ndky.cloudedu.vo.CollegesVo;
import com.ndky.cloudedu.vo.SpecialityVo;
import com.ndky.cloudedu.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudEduApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClassesMapper classesMapper;

    @Autowired
    ClassesService classesService;

    @Autowired
    public CollegesMapper collegesMapper;
    @Autowired
    public SpecialityMapper specialityMapper;


}
