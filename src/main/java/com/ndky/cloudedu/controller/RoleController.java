package com.ndky.cloudedu.controller;


import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.entity.Role;
import com.ndky.cloudedu.entity.Speciality;
import com.ndky.cloudedu.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 前端控制器
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    public RoleService roleService;


    @ApiOperation("获取所有角色")
    @GetMapping(value = "/listAll")
    @ResponseBody
    public ReturnMsg listAll() {
        List<Role> list = roleService.list();
        for (Role r : list) {
            System.out.println(r);
        }
        return ReturnMsg.success(list);
    }
}

