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

    @Test
    void test() {
        List<CollegesVo> allColleges = collegesMapper.getAllColleges();
        for (CollegesVo colleges: allColleges){
            System.out.println(colleges);
        }

    }

    @Test
    void test2() {
        List<SpecialityVo> allColleges = specialityMapper.selectAllSpecialities(1L);
        for (SpecialityVo speciality: allColleges){
            System.out.println(speciality);
        }

    }

    @Test
    void test3() {
        List<Classes> allColleges = classesMapper.selectAllClasses(1L);
        for (Classes clazz: allColleges){
            System.out.println(clazz);
        }

    }
    @Test
    void test4() {
        System.out.println(Util.NowTime());
        InetAddress ia = null;
        try {
            ia = ia.getLocalHost();
            String localname = ia.getHostName();
            String localip = ia.getHostAddress();
            System.out.println("本机名称是：" + localname);
            System.out.println("本机的ip是 ：" + localip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long l = System.nanoTime();
        System.out.println(l);
        long l1 = System.currentTimeMillis();
        System.out.println(l1);
    }

}
