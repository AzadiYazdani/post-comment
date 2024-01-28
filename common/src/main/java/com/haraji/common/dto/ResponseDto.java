package com.haraji.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResponseDto<T> {

    @JsonProperty("response")
    private T response = null;

    public static <T> ResponseDto success(T t) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse(t);
        return responseDto;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

}

