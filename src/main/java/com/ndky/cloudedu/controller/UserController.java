package com.ndky.cloudedu.controller;


import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.common.api.CommonPage;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.entity.Room;
import com.ndky.cloudedu.entity.RoomClass;
import com.ndky.cloudedu.entity.User;
import com.ndky.cloudedu.service.RoomClassService;
import com.ndky.cloudedu.service.RoomService;
import com.ndky.cloudedu.service.UserRoleService;
import com.ndky.cloudedu.service.UserService;
import com.ndky.cloudedu.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kiko
 * @since 2021-10-20
 */
@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoomClassService roomClassService;
    @Autowired
    private RoomService roomService;

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping("/list")
    @ResponseBody
    public ReturnMsg list(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<User> adminList = userService.list(keyword, pageSize, pageNum);
        return ReturnMsg.success(CommonPage.restPage(adminList));
    }

    @ApiOperation("获取学生及班级信息")
    @GetMapping("/getUserAll")
    @ResponseBody
    public ReturnMsg getUserAll(@RequestParam(value = "keyword", required = false) String keyword,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<UserVo> adminList = userService.getUserAll(keyword, pageSize, pageNum);
        return ReturnMsg.success(CommonPage.restPage(adminList));
    }

    @ApiOperation(value = "新建学生信息")
    @PostMapping(value = "/addUser")
    @ResponseBody
    public ReturnMsg addUser(@Validated @RequestBody User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", user.getId());
        if (userService.getOne(wrapper) != null) {
            return ReturnMsg.failed("学生已存在！");
        }
        user.setPassword(SecureUtil.md5(user.getPassword()));
        User newUser = userService.register(user);
        if (newUser == null) {
            return ReturnMsg.failed();
        } else {
            userRoleService.addRole(user.getId(), Convert.toLong(1));
            return ReturnMsg.success("添加成功！");
        }
    }

    @ApiOperation(value = "查询学生加入的教室")// 按学号查找学生班级，按班级查找教室
    @PostMapping(value = "/findJoinRoom")
    @ResponseBody
    public ReturnMsg stuFindJoinRoom(@RequestParam("uid") String uid) {
        User user = userService.getById(uid);
        QueryWrapper<RoomClass> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", user.getCid());
        List<RoomClass> list = roomClassService.list(wrapper);
        List<Room> joinRoomList = new ArrayList<>();
        for (RoomClass roomclass : list) {
            Long rid = roomclass.getRid();
            joinRoomList.add(roomService.getById(rid));
        }
        if (joinRoomList.isEmpty()) {
            return ReturnMsg.failed("不存在加入的教室");
        } else {
            return ReturnMsg.success().add("joinRoomList", joinRoomList);
        }
    }
    @ApiOperation(value = "查询老师创建的教室")// 按学号查找学生班级，按班级查找教室
    @PostMapping(value = "/findCreateRoom")
    @ResponseBody
    public ReturnMsg teaFndCreateRoom(@RequestParam("uid") String uid) {
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", uid);
        List<Room> list = roomService.list(wrapper);
        if (list.isEmpty()) {
            return ReturnMsg.failed("不存在创建的教室");
        } else {
            return ReturnMsg.success().add("joinRoomList", list);
        }
    }
}

