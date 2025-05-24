package com.angeldev.bookmanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiErrorResponse(
        @JsonProperty(value = "http_code")
        int httpCode,

        String url,

        @JsonProperty(value = "http_method")
        String httpMethod,

        String message,

        @JsonProperty(value = "backend_message")
        String backendMessage
) {
}
