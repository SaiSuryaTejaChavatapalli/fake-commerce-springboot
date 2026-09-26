package com.sst.FakeCommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sst.FakeCommerce.utils.ApiResponse;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException( ResourceNotFoundException exception){
        ApiResponse<Void> apiResponse = ApiResponse.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAllGeneralExceptions(Exception exception){
        
        ApiResponse<Void> apiResponse = ApiResponse.error("Something went wrong");
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(apiResponse);

    }
}
