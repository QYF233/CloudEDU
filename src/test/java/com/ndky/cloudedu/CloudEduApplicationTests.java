package com.ndky.cloudedu;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.mapper.UserMapper;
import com.ndky.cloudedu.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudEduApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        IPage<UserVo> iPage = new Page<>(1, 5);
        Page<UserVo> test = userMapper.getUserAll(iPage, "小");

        System.out.println(test);

    }

}
