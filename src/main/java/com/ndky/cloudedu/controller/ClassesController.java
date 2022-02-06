package com.ndky.cloudedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.entity.Classes;
import com.ndky.cloudedu.entity.Colleges;
import com.ndky.cloudedu.entity.Room;
import com.ndky.cloudedu.entity.Speciality;
import com.ndky.cloudedu.service.ClassesService;
import com.ndky.cloudedu.service.CollegesService;
import com.ndky.cloudedu.service.SpecialityService;
import com.ndky.cloudedu.vo.CollegesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kiko
 * @since 2022-02-05
 */
@Api(tags = "班级管理")
@RestController
@RequestMapping("/classes")
public class ClassesController {
    @Autowired
    ClassesService classesService;

    @Autowired
    CollegesService collegesService;

    @Autowired
    SpecialityService specialityService;

    @ApiOperation("获取所有班级")
    @GetMapping(value = "/listAll")
    @ResponseBody
    public ReturnMsg listAll() {
        List<CollegesVo> allClassesOption = collegesService.getAllClassesOption();
        return ReturnMsg.success(allClassesOption);
    }


    @ApiOperation("添加班级")
    @PostMapping(value = "/create")
    @ResponseBody
    public ReturnMsg create(@RequestBody Classes classes) {
        boolean success = classesService.save(classes);
        if (success) {
            return ReturnMsg.success();
        }
        return ReturnMsg.failed();
    }


}

