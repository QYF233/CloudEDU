package com.ndky.cloudedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.common.api.CommonPage;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.entity.Class;
import com.ndky.cloudedu.service.ClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Api(tags = "班级信息管理")
@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;

    @ApiOperation("获取所有班级")
    @GetMapping(value = "/listAll")
    @ResponseBody
    public ReturnMsg listAll() {
        List<Class> list = classService.list();
        return ReturnMsg.success(list);
    }


    @ApiOperation("添加班级")
    @PostMapping(value = "/create")
    @ResponseBody
    public ReturnMsg create(@RequestBody Class clazz) {
        boolean success = classService.save(clazz);
        if (success) {
            return ReturnMsg.success();
        }
        return ReturnMsg.failed();
    }

}

