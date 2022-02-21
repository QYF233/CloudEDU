package com.ndky.cloudedu.controller;

import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.common.utils.Util;
import com.ndky.cloudedu.service.impl.WebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;

@Api(tags = "websocket管理")
@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    @ApiOperation("群发消息")
    @PostMapping("/sendAll")
    @ResponseBody
    public ReturnMsg sendAll(@PathParam("roomId") String roomId, @PathParam("message") String message) {
        WebSocketServer.sendMessage(roomId, message);
        return ReturnMsg.success("这是websocket群发消息");
    }

    @ApiOperation("群发自定义消息")
    @PostMapping("/send")
    @ResponseBody
    public ReturnMsg send(@PathParam("roomId") String roomId, @PathParam("userId") String userId, @PathParam("message") String message) {
        //            WebSocketServer.sendInfo(roomId, userId, message);
        //        System.out.println("2");
        return ReturnMsg.success(message);
    }

    @ApiOperation("获取websocket连接地址")
    @PostMapping("/getWebSocketUrl")
    @ResponseBody
    public ReturnMsg getWebSocketUrl(@PathParam("roomId") String roomId) {
        String ip = Util.getIp();
        String url = "ws://" + ip + ":8088/websocket/" + roomId;
        return ReturnMsg.success().add("socketUrl", url);
    }

    @ApiOperation("获取房间连接人数")
    @PostMapping("/getOnlineCount")
    @ResponseBody
    public ReturnMsg getOnlineCount(@PathParam("roomId") String roomId) {
        Integer onlineCount = WebSocketServer.getOnlineCount(roomId);

        return ReturnMsg.success().add("onlineCount", onlineCount);
    }
}


