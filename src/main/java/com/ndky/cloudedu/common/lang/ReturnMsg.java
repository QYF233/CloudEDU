package com.ndky.cloudedu.common.lang;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装Json数据和请求的状态
 *
 * @author kiko
 */

@Data
public class ReturnMsg {

    private Integer code;   //状态码 200-成功 500-失败 50008：非法令牌； 50012：其他客户端已登录； 50014：令牌已过期；
    private String message;     //提示消息
    private String target;  //连接地址
    private Object data;

    //封装返回的数据
    private Map<String, Object> map = new HashMap<String, Object>();

    public static ReturnMsg success() {
        ReturnMsg result = new ReturnMsg();
        result.setCode(200);
        result.setMessage("OK");
        return result;
    }

    public static ReturnMsg success(String msg) {
        ReturnMsg result = new ReturnMsg();
        result.setCode(200);
        result.setMessage(msg);
        return result;
    }

    public static ReturnMsg success(Object data) {
        ReturnMsg result = new ReturnMsg();
        result.setCode(200);
        result.setData(data);
        result.setMessage("OK");
        return result;
    }

    public static ReturnMsg success(String msg, Object data) {
        ReturnMsg result = new ReturnMsg();
        result.setCode(200);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    public static ReturnMsg failed() {
        ReturnMsg result = new ReturnMsg();
        result.setCode(500);
        result.setMessage("ERROR");
        return result;
    }

    public static ReturnMsg failed(String msg) {
        ReturnMsg result = new ReturnMsg();
        result.setCode(500);
        result.setMessage(msg);
        return result;
    }

    public static ReturnMsg failed(Integer code, String msg) {
        ReturnMsg result = new ReturnMsg();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

//
    public ReturnMsg add(String key, Object value) {
        this.map.put(key, value);
        this.data = map;
        return this;
    }

}
