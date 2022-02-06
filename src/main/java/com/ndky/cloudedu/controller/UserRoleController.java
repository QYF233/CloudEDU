package com.ndky.cloudedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.entity.RoomClass;
import com.ndky.cloudedu.entity.UserRole;
import com.ndky.cloudedu.service.UserRoleService;
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
@Api(tags = "用户权限管理")
@RestController
@RequestMapping("/user-role")
public class UserRoleController {
    @Autowired
    public UserRoleService userRoleService;

    @ApiOperation("获取用户的权限")
    @GetMapping(value = "/getUserRole")
    @ResponseBody
    public ReturnMsg getUserRole(@RequestParam("uid") String uid) {
        UserRole byId = userRoleService.getById(uid);
        return ReturnMsg.success(byId.getRid());
    }
}

