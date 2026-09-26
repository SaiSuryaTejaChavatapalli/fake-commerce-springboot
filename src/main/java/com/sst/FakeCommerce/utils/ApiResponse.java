package com.sst.FakeCommerce.utils;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class ApiResponse<T>{

    private boolean success;

    private String message;

    private T data;

    private LocalDateTime timestamp;


    public static <T> ApiResponse<T> success(T data, String message){
        return ApiResponse.<T>builder()
            .success(true)
            .message(message)
            .data(data)
            .timestamp(LocalDateTime.now())
            .build();
    }

    public  static <T> ApiResponse<T> error(String message){
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

}

//-----------------------------------------------
// Success scenraio
// {
//   "success": true,
//   "message": "User fetched successfully",
//   "data": {
//     "id": 1,
//     "name": "Alice",
//     "email": "alice@example.com"
//   },
//   "timestamp": "2026-09-26T19:44:00"
// }

//-----------------------------------------------
// Error scenario

// {
//   "success": false,
//   "message": "User with ID 999 not found",
//   "data": null,
//   "timestamp": "2026-09-26T19:45:12"
// }
