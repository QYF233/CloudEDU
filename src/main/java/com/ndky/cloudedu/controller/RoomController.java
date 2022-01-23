package com.ndky.cloudedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndky.cloudedu.common.api.CommonPage;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.entity.Class;
import com.ndky.cloudedu.entity.Room;
import com.ndky.cloudedu.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("根据分页获取教室列表，关键词搜索")
    @GetMapping(value = "/list")
    @ResponseBody
    public ReturnMsg list(@RequestParam(value = "keyword", required = false) String keyword,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "state", defaultValue = "1") Integer state) {
        Page<Room> roomList = roomService.list(keyword, pageSize, pageNum, state);
        return ReturnMsg.success(CommonPage.restPage(roomList));
    }

    @ApiOperation("添加班级")
    @PostMapping(value = "/create")
    @ResponseBody
    public ReturnMsg create(@RequestBody Room room) {
        boolean success = roomService.save(room);
        if (success) {
            return ReturnMsg.success();
        }
        return ReturnMsg.failed();
    }
}

