package com.angeldev.bookmanagement.exception;

import com.angeldev.bookmanagement.dto.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    private ResponseEntity<ApiErrorResponse> handleObjectNotFoundException(ObjectNotFoundException objectNotFoundException,
                                                                           HttpServletRequest request, HttpServletResponse response) {
        int httpStatus = HttpStatus.NOT_FOUND.value();

        ApiErrorResponse apiErrorResponse =  new ApiErrorResponse(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "The requested information could not be found.",
                objectNotFoundException.getMessage()
        );

        return ResponseEntity.status(httpStatus).body(apiErrorResponse);
    }
}
