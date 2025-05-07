package vn.vietinbank.evolve.dto.response;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public ApiResponse() {}
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static<T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(200, "success", data);
    }

    public static<T> ApiResponse<T> success() {
        return new ApiResponse<T>(200, "success", null);
    }

    public static<T> ApiResponse<T> error(String message) {
        return new ApiResponse<T>(500, message, null);
    }

    public static<T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<T>(code, message, null);
    }

}
