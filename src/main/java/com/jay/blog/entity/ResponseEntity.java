package com.jay.blog.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseEntity<T> {
    private boolean success;
    private String message;
    private int code;
    private T data;

    public ResponseEntity() {
        this(true, "success", HttpStatus.OK, null);
    }

    public ResponseEntity(boolean success, String message, HttpStatus httpStatus, T data) {
        this.success = success;
        this.message = message;
        this.code = httpStatus.value();
        this.data = data;
    }

    public ResponseEntity(boolean success, String message, T data) {
        this(success, message, HttpStatus.OK, data);
    }

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<T>(true, "success", HttpStatus.OK, data);
    }

    public static <T> ResponseEntity<T> error(String message) {
        return new ResponseEntity<T>(false, message, HttpStatus.OK, null);
    }
}
