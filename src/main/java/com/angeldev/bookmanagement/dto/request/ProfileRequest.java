package com.angeldev.bookmanagement.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProfileRequest(

        @NotBlank(message = "Name must not be blank")
        @Size(min=2, max = 255, message = "Field must have between {min} and {max}")
        String name,

        @NotBlank(message = "Name must not be blank")
        @Size(min = 10, max = 255, message = "Field must have between {min} and {max}")
        String description
) { }
