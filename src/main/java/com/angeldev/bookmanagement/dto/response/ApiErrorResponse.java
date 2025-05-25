package com.angeldev.bookmanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record ApiErrorResponse(
        @JsonProperty(value = "http_code")
        int httpCode,

        String url,

        @JsonProperty(value = "http_method")
        String httpMethod,

        String message,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty(value = "backend_message")
        String backendMessage,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Map<String, String> errors
) {
}
