package com.example.room.entity.dto;

public class ApiResponse<T> {
    private String message;
    private int out;
    private T data;

    public ApiResponse(String message, int out, T data) {
        this.message = message;
        this.out = out;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getOut() {
        return out;
    }

    public T getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public void setData(T data) {
        this.data = data;
    }
}

