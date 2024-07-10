package com.practice.api.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseResponse {

    @JsonProperty("isSuccess")
    boolean isSuccess;

    String message;

    Object data;
}
