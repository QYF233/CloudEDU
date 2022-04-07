package com.ndky.cloudedu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ndky.cloudedu.common.lang.ReturnMsg;
import com.ndky.cloudedu.entity.User;
import com.ndky.cloudedu.mapper.UserMapper;
import com.ndky.cloudedu.service.UserService;
import com.ndky.cloudedu.vo.MsgVo;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{roomId}/{uid}")
@Slf4j
@Component
public class WebSocketServer {

    //concurrentHashMap分段锁，用来存放每个客户端对应的Websocket对象。

    /**
     * 房间号 -> 组成员信息
     */
    private static ConcurrentHashMap<String, List<Session>> rooms = new ConcurrentHashMap<>();
    /**
     * 房间号 -> 在线人数
     */
    private static ConcurrentHashMap<String, Set<String>> onlineUserMap = new ConcurrentHashMap<>();

    private static UserService userService; //静态注入

    @Autowired  //手动set
    public void setUserService(UserService userService) {
        WebSocketServer.userService = userService;
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param roomId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("roomId") String roomId, @PathParam("uid") String uid, Session session) {
        List<Session> sessionList = rooms.computeIfAbsent(roomId, k -> new ArrayList<>());
        System.out.println("open");
        Set<String> onlineUserList = onlineUserMap.computeIfAbsent(roomId, k -> new HashSet<>());
        onlineUserList.add(uid);
        sessionList.add(session);
        User user = userService.getUserById(Long.valueOf(uid));
        // 发送上线通知
        onMessage(roomId, uid, user.getUsername() + "进入教室");
//        sendInfo(roomId, uid, onlineUserList.size(), userById.getUsername() + "进入教室");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("roomId") String roomId, @PathParam("uid") String uid, Session session) {
        List<Session> sessionList = rooms.get(roomId);
        sessionList.remove(session);
        Set<String> onlineUserList = onlineUserMap.get(roomId);
        onlineUserList.remove(uid);
        User user = userService.getUserById(Long.valueOf(uid));
        // 发送离线通知
        onMessage(roomId, uid, user.getUsername() + "离开教室");
        //        sendInfo(roomId, uid, onlineUserList.size(), "");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     */
    @OnMessage
    public void onMessage(@PathParam("roomId") String roomId, @PathParam("uid") String uid, String message) {
        List<Session> sessionList = rooms.get(roomId);
        Set<String> onlineUserList = onlineUserMap.get(roomId);
        // 先一个群组内的成员发送消息
        sessionList.forEach(item -> {
            try {
                // json字符串转对象
//                MsgVo msg = JSONObject.parseObject(message, MsgVo.class);
                MsgVo msg = new MsgVo();
                msg.setMsg(message);
                msg.setCount(onlineUserList.size());
//                 json对象转字符串
                String text = JSONObject.toJSONString(msg);
                item.getBasicRemote().sendText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WebSocket发生错误：" + throwable.getMessage());
    }

    public static ReturnMsg sendMessage(String roomId, String message) {
        if (roomId != null) {
            List<Session> sessionList = rooms.get(roomId);
            Set<String> onlineUserList = onlineUserMap.get(roomId);
            // 先一个群组内的成员发送消息
            if (sessionList != null && sessionList.size() > 0) {
                sessionList.forEach(item -> {
                    synchronized (item) {
                        try {
                            // json字符串转对象
                            MsgVo msg = new MsgVo();
                            msg.setMsg(message);
                            msg.setCount(onlineUserList.size());
                            // json对象转字符串
                            String text = JSONObject.toJSONString(msg);
                            item.getBasicRemote().sendText(text);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return ReturnMsg.success("群发成功！");
            }
            return ReturnMsg.failed("房间号不存在" + sessionList);
        } else {
            return ReturnMsg.failed();
        }

    }

    public static Integer getOnlineCount(String roomId) {
        Set<String> onlineUserList = onlineUserMap.get(roomId);
        return onlineUserList.size();
    }

    /**
     * 群发自定义消息
     */
    public void sendInfo(String roomId, String uid, Integer onlineSum, String info) {
        onMessage(roomId, uid, info);
    }

}
