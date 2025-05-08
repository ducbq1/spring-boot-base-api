package vn.vietinbank.evolve.common;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public class ApiResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private T data;
    private Map<String, String> errors;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public ApiResponse() {}
    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(int code, String message, T data, Map<String, String> errors) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.errors = errors;
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

    public static<T> ApiResponse<T> error(int code, String message, Map<String, String> errors) {
        return new ApiResponse<T>(code, message, null, errors);
    }

}
