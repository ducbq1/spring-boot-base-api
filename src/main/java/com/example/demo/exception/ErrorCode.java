package com.example.demo.exception;

public enum ErrorCode {
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Resource Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    UNAUTHORIZED(401, "Unauthorized");

    public final int code;
    public final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
