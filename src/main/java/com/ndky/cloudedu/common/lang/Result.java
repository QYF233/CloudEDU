package com.ndky.cloudedu.common.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kiko
 */
@Data
public class Result implements Serializable {

    private int code;
    private String message;
    private Object data;

    public static Result success() {
        return success(200, "成功", null);
    }

    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result success(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    public static Result failed() {
        return failed(50008, "出错", null);
    }

    public static Result failed(String msg) {
        return failed(50008, msg, null);
    }

    public static Result failed(String msg, Object data) {
        return failed(50008, msg, data);
    }

    public static Result failed(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

}
