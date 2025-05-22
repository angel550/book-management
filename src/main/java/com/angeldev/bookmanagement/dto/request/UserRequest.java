package com.angeldev.bookmanagement.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @Pattern(regexp = "[a-zA-Z0-9-_]{8,255}",
                message = "Username must consist of 8 to 255 characters and only contain letters, " +
                        "digits, and underscores.")
        String username,

        @NotBlank(message = "Name must not be blank")
        @Size(min=2, max = 255, message = "Field must have between {min} and {max}")
        String name,

        @NotBlank(message = "Name must not be blank")
        @Size(min = 10, max = 255, message = "Field must have between {min} and {max}")
        String password,

        @JsonProperty(value = "password_repeated")
        @NotBlank(message = "Name must not be blank")
        @Size(min = 10, max = 255, message = "{generic.size}")
        String passwordRepeated
) { }
