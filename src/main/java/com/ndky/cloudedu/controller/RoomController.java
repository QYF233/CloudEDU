package com.ndky.cloudedu.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.common.api.CommonPage;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.common.utils.Util;
import com.ndky.cloudedu.entity.Room;
import com.ndky.cloudedu.entity.RoomClass;
import com.ndky.cloudedu.service.RoomClassService;
import com.ndky.cloudedu.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kiko
 * @since 2021-10-30
 */
@Api(tags = "教室管理")
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomClassService roomClassService;

    @ApiOperation("根据分页获取教室列表，关键词搜索")
    @PostMapping(value = "/list")
    @ResponseBody
    public ReturnMsg list(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "state", defaultValue = "1") Integer state) {
        Page<Room> roomList = roomService.list(keyword, pageSize, pageNum, state);
        return ReturnMsg.success(CommonPage.restPage(roomList));
    }

    @ApiOperation("查询公开课")
    @PostMapping(value = "/publicRoom")
    @ResponseBody
    public ReturnMsg publicRoom(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<Room> roomList = roomService.list("", pageSize, pageNum, 1);
        return ReturnMsg.success(CommonPage.restPage(roomList));
    }

    @CrossOrigin
    @ApiOperation("添加教室（直播间）")
    @PostMapping(value = "/create")
    @ResponseBody
    public ReturnMsg create(@RequestParam("name") String name,
                            @RequestParam("note") String note,
                            @RequestParam("classes") String classes,
                            @RequestParam("teacherId") Long teacherId) {
        String ip = Util.getIp();
        if (Objects.equals(ip, "error")) {
            ReturnMsg.failed("获取IP失败！");
        }
        Long id = System.nanoTime();
        String liveUrl = "webrtc://" + ip + "/live/" + id;
        String socketUrl = "ws://" + ip + ":8088/websocket/" + id;
        System.out.println(id);
        Room room = new Room(id, name, note, 1, liveUrl, socketUrl, teacherId, new Date());
        boolean success = roomService.save(room);
        if (success) {
            String[] arr = classes.split(",");
            for (String a : arr) {
                Long classId = Long.valueOf(a);
                System.out.println(classId);
                Long roomId = room.getId();
                boolean flag = roomClassService.saveOrUpdate(new RoomClass(roomId, classId));
                if (flag) {
                    return ReturnMsg.success()
                            .add("liveUrl", room.getLiveUrl())
                            .add("socketUrl", socketUrl)
                            .add("roomId", room.getId());
                } else {
                    return ReturnMsg.failed("绑定上课班级失败！");
                }
            }
            return ReturnMsg.failed("上课班级查询失败！");
        }
        return ReturnMsg.failed("教室创建失败！");
    }

    @CrossOrigin
    @ApiOperation("关闭教室（直播间）")
    @PostMapping(value = "/close")
    @ResponseBody
    public ReturnMsg close(@RequestParam("roomId") String roomId) {
        UpdateWrapper<Room> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("state", 0).eq("id", roomId);
        boolean update = roomService.update(updateWrapper);
        if (update) {
            return ReturnMsg.success("教室关闭成功！");
        }
        return ReturnMsg.failed("教室关闭失败！");
    }

    @CrossOrigin
    @ApiOperation("查询教室信息")
    @PostMapping(value = "/findRoomById")
    @ResponseBody
    public ReturnMsg findRoomById(@RequestParam("roomId") String roomId) {
        Room byId = roomService.getById(roomId);
        if (byId != null) {
            return ReturnMsg.success().add("room", byId);
        }
        return ReturnMsg.failed();
    }
}

