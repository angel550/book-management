package com.angeldev.bookmanagement.exception;

import com.angeldev.bookmanagement.dto.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    private ResponseEntity<ApiErrorResponse> handleObjectNotFoundException(ObjectNotFoundException objectNotFoundException,
                                                                           HttpServletRequest request) {
        int httpStatus = HttpStatus.NOT_FOUND.value();

        ApiErrorResponse apiErrorResponse =  new ApiErrorResponse(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "The requested information could not be found.",
                objectNotFoundException.getMessage(),
                null
        );

        return ResponseEntity.status(httpStatus).body(apiErrorResponse);
    }

    @ExceptionHandler(DuplicateObjectException.class)
    private ResponseEntity<ApiErrorResponse> handleDuplicateObjectException(DuplicateObjectException duplicateObjectException,
                                                                           HttpServletRequest request) {
        int httpStatus = HttpStatus.CONFLICT.value();

        ApiErrorResponse apiErrorResponse =  new ApiErrorResponse(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Failed to create the object",
                duplicateObjectException.getMessage(),
                null
        );

        return ResponseEntity.status(httpStatus).body(apiErrorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // @Valid @RequestBody - exception
    private ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                                   HttpServletRequest request) {

        int httpStatus = HttpStatus.BAD_REQUEST.value();
        Map<String, String> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
        );

        ApiErrorResponse apiErrorResponse =  new ApiErrorResponse(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Oops! Some information you entered needs a little adjustment",
                methodArgumentNotValidException.getMessage(),
                errors
        );

        return ResponseEntity.status(httpStatus).body(apiErrorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ResponseEntity<ApiErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
                                                                      HttpServletRequest request) {
        int httpStatus = HttpStatus.METHOD_NOT_ALLOWED.value();

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Invalid request, method not supported",
                httpRequestMethodNotSupportedException.getMessage(),
                null
        );

        return ResponseEntity.status(httpStatus).body(apiErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiErrorResponse> handleException(Exception exception, HttpServletRequest request) {

        int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

        ApiErrorResponse apiError = new ApiErrorResponse(
                httpStatus,
                request.getRequestURL().toString(),
                request.getMethod(),
                "Oops! Something went wrong on our server. Please try again later.",
                exception.getMessage(),
                null
        );

        return ResponseEntity.status(httpStatus).body(apiError);
    }
}
