package com.example.springgallery.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseModel {

    private String code;
    private String message;
    private Object data;

    public static ResponseModel success(Object data){
        return new ResponseModel("200", "Request successfully", data);
    }

    public static ResponseModel accepted(Object data) {
        return new ResponseModel("202", "Request accepted", data);
    }

    public static ResponseModel notFound() {
        return new ResponseModel("404", "Not found", null);
    }

    public static ResponseModel unaccepted() {
        return new ResponseModel("406", "Not accepted", null);
    }
}
