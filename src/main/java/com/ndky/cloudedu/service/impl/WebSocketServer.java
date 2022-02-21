package com.ndky.cloudedu.service.impl;

import com.ndky.cloudedu.common.lang.ReturnMsg;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{roomId}")
@Slf4j
@Component
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。设计为安全的
    private static int onlineCount = 0;
    //concurrentHashMap分段锁，用来存放每个客户端对应的Websocket对象。

    private static Map<String, Set<Session>> rooms = new ConcurrentHashMap<>(); //教室列表

    /**
     * 连接建立成功调用的方法
     *
     * @param roomId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("roomId") String roomId, Session session) {
        if (!rooms.containsKey(roomId)) {
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<>();
            // 添加用户
            room.add(session);
            rooms.put(roomId, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomId).add(session);
        }
//        WebSocketServer.onlineCount++;
        log.info("有一连接进入！当前在线人数为" + rooms.get(roomId).size());
        log.info("用户名：" + session.getId());
//        clients.put(username, this);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("roomId") String roomId, Session session) {
//        clients.remove(username);
        rooms.get(roomId).remove(session);
        WebSocketServer.onlineCount--;
        log.info("有一连接关闭！当前在线人数为" + onlineCount);

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     */
    @OnMessage
    public void onMessage(@PathParam("roomId") String roomId, String message, Session session) {
        System.out.println("收到客户端的消息" + message);

        sendMessage(roomId, message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WebSocket发生错误：" + throwable.getMessage());
    }

    public static ReturnMsg sendMessage(String roomId, String message) {
        // 向所有连接websocket的客户端发送消息
        // 可以修改为对某个客户端发消息
        System.out.println("当前roomId为：" + roomId);
        if (roomId != null) {
            for (Session session : rooms.get(roomId)) {
                try {
                    session.getBasicRemote().sendText(message);
                    return ReturnMsg.success();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return ReturnMsg.failed("房间号不存在");
        }
        return ReturnMsg.failed();
    }

    public static Integer getOnlineCount(String roomId) {
        return rooms.get(roomId).size();
    }
    /**
     * 群发自定义消息
     */
//    public static void sendInfo(String roomId, String userId, String message) throws IOException {
//
//        for (WebSocketServer item : rooms.get(roomId).values()) {
//            //这里可以设定只推送给这个sid的，为null则全部推送
//            if (userId == null) {
//                item.session.getAsyncRemote().sendText(message);
//            } else if (item.userId.equals(userId)) {
//                item.session.getAsyncRemote().sendText(message);
//            }
//            log.info("推送消息到窗口" + userId + "，推送内容:" + message);
//        }
//    }

}
