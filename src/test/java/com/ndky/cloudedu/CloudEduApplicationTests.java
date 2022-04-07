package com.ndky.cloudedu;

import com.ndky.cloudedu.entity.User;
import com.ndky.cloudedu.mapper.ClassesMapper;
import com.ndky.cloudedu.mapper.CollegesMapper;
import com.ndky.cloudedu.mapper.SpecialityMapper;
import com.ndky.cloudedu.mapper.UserMapper;
import com.ndky.cloudedu.service.ClassesService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void test(){
        User user = userMapper.selectById("212821122");
        System.out.println(user.toString());
    }
}
