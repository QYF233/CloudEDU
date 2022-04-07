package com.ndky.cloudedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.common.api.CommonPage;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.common.utils.Util;
import com.ndky.cloudedu.entity.Room;
import com.ndky.cloudedu.entity.RoomClass;
import com.ndky.cloudedu.entity.User;
import com.ndky.cloudedu.service.RoomClassService;
import com.ndky.cloudedu.service.RoomService;
import com.ndky.cloudedu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
    @Autowired
    UserService userService;

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
                            @RequestParam(value = "note", defaultValue = "") String note,
                            @RequestParam("classes") String classes,
                            @RequestParam("state") Integer state,
                            @RequestParam("uid") Long uid) {

        String ip = Util.getIp();
        if (Objects.equals(ip, "error")) {
            ReturnMsg.failed("获取IP失败！");
        }
        Long id = System.nanoTime();
        /**
         * 开发环境 localhost
         */
        ip = "localhost";
        /**
         * 开发环境 localhost
         */
        String liveUrl = "webrtc://" + ip + "/live/" + id;
        String socketUrl = "ws://" + ip + ":8088/websocket/" + id;
        Room room = new Room(id, name, note, state, liveUrl, socketUrl, uid, new Date());
        boolean success = roomService.save(room);
        if (success) {
            String[] arr = classes.split(",");

            System.out.println(Arrays.toString(arr));
            boolean flag = true;
            for (String a : arr) {
                Long classId = Long.valueOf(a);
                System.out.println(classId);
                Long roomId = room.getId();
                boolean flag1 = roomClassService.saveOrUpdate(new RoomClass(roomId, classId));
                if (!flag1) {
                    flag = false;
                }
            }
            if (flag) {
                return ReturnMsg.success()
                        .add("liveUrl", room.getLiveUrl())
                        .add("socketUrl", socketUrl)
                        .add("roomId", room.getId());
            } else {
                return ReturnMsg.failed("绑定上课班级失败！");
            }
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

    @CrossOrigin
    @ApiOperation("检查用户是否有资格进入教室")
    @PostMapping(value = "/checkUserToRoom")
    @ResponseBody
    public ReturnMsg checkUserToRoom(@RequestParam("roomId") String roomId, @RequestParam("uid") Long uid) {
        Room room1 = roomService.getById(roomId);
        if (room1.getState() == 1) {
            return ReturnMsg.success().add("flag", true).add("roomInfo", room1);
        } else {
            if(Objects.equals(room1.getTeacherId(), uid)){
                Room room3 = roomService.getById(roomId);
                return ReturnMsg.success().add("flag", true).add("roomInfo", room3);
            }
            QueryWrapper<RoomClass> query = new QueryWrapper<RoomClass>();
            query.eq("rid", roomId);
            List<RoomClass> list = roomClassService.list(query);
            User user = userService.getById(uid);
            for (RoomClass rc : list) {
                if (rc.getCid().equals(user.getCid())) {
                    Room room2 = roomService.getById(roomId);
                    return ReturnMsg.success().add("flag", true).add("roomInfo", room2);
                }
            }
        }

        return ReturnMsg.success().add("flag", false);
    }
}

