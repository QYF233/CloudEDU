package com.ndky.cloudedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.entity.Class;
import com.ndky.cloudedu.entity.Room;
import com.ndky.cloudedu.entity.RoomClass;
import com.ndky.cloudedu.service.ClassService;
import com.ndky.cloudedu.service.RoomClassService;
import com.ndky.cloudedu.service.RoomService;
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
 * @since 2021-10-30
 */
@Api(tags = "教室班级管理")
@RestController
@RequestMapping("/room-class")
public class RoomClassController {

    @Autowired
    RoomClassService roomClassService;
    @Autowired
    RoomService roomService;
    @Autowired
    ClassService classService;

    @ApiOperation("设置教室上课班级")
    @PostMapping(value = "/setRoomClass")
    @ResponseBody
    public ReturnMsg setRoomClass(@RequestParam("rid") Long rid, @RequestParam("cid") Long cid) {
        Room rItem = roomService.getById(rid);
        Class cItem = classService.getById(cid);
        if (rItem != null && cItem != null) {
            boolean flag = roomClassService.saveOrUpdate(new RoomClass(rid, cid));
            if (flag) {
                return ReturnMsg.success();
            } else {
                return ReturnMsg.failed();
            }
        } else {
            return ReturnMsg.failed("班级id或教室id错误！");
        }
    }

    @ApiOperation("按教室查找班级")
    @GetMapping(value = "/getClassByRoom")
    @ResponseBody
    public ReturnMsg getClassByRoom(@RequestParam("rid") Long rid) {
        QueryWrapper<RoomClass> wrapper = new QueryWrapper<>();
        wrapper.eq("rid", rid);
        List<RoomClass> list = roomClassService.list(wrapper);
        if (list != null) {
            return ReturnMsg.success(list);
        } else {
            return ReturnMsg.failed();
        }
    }

    @ApiOperation("按班级查找教室")
    @GetMapping(value = "/getRoomByClass")
    @ResponseBody
    public ReturnMsg getRoomByClass(@RequestParam("cid") Long cid) {
        QueryWrapper<RoomClass> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", cid);
        List<RoomClass> list = roomClassService.list(wrapper);
        if (!list.isEmpty()) {
            Room room = roomService.getById(list.get(0).getRid());
            return ReturnMsg.success(room);
        } else {
            return ReturnMsg.failed();
        }
    }
}

