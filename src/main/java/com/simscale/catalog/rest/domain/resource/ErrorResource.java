package com.simscale.catalog.rest.domain.resource;

import org.springframework.http.HttpStatus;

public class ErrorResource {

    private String code;

    private String error;

    public ErrorResource(String code, String error) {
        this.code = code;
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static ErrorResource notFound(String message){
        return new ErrorResource(HttpStatus.NOT_FOUND.name(), message);
    }
}
