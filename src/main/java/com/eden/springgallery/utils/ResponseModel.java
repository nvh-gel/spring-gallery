package com.eden.springgallery.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Reponse data wrapper
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseModel {

    private String code;
    private String message;
    private Object data;

    /**
     * Success model
     *
     * @param data response data
     * @return response model
     */
    public static ResponseModel success(Object data) {
        return new ResponseModel("200", "Request successfully", data);
    }

    /**
     * Accepted model
     *
     * @param data response data
     * @return response model
     */
    public static ResponseModel accepted(Object data) {
        return new ResponseModel("202", "Request accepted", data);
    }

    /**
     * Data not found model
     *
     * @return response model
     */
    public static ResponseModel notFound() {
        return new ResponseModel("404", "Not found", null);
    }

    /**
     * Request unaccepted model
     *
     * @return response model
     */
    public static ResponseModel unaccepted() {
        return new ResponseModel("406", "Not accepted", null);
    }
}
