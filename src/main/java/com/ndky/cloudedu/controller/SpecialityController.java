package com.ndky.cloudedu.controller;


import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.entity.Classes;
import com.ndky.cloudedu.entity.Speciality;
import com.ndky.cloudedu.service.SpecialityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kiko
 * @since 2022-02-05
 */
@RestController
@RequestMapping("/speciality")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @ApiOperation("获取所有专业")
    @GetMapping(value = "/listAll")
    @ResponseBody
    public ReturnMsg listAll() {
        List<Speciality> list = specialityService.list();
        for (Speciality speciality : list) {
            System.out.println(speciality);
        }
        return ReturnMsg.success(list);
    }
}

