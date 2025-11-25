package com.harmonyapp.backend.common;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMsg("Success");
        response.setData(data);
        return response;
    }

    public static <T> ApiResponse<T> success(String msg, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static ApiResponse<Void> success(String msg) {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setCode(200);
        response.setMsg(msg);
        return response;
    }

    public static <T> ApiResponse<T> error(int code, String msg) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
}
