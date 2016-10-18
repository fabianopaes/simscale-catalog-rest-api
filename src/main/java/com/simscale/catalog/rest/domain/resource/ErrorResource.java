package com.simscale.catalog.rest.domain.resource;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ErrorResource {

    private String code;

    private List<String> errors;

    public ErrorResource(String code, List<String> errors) {
        this.code = code;
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public static ErrorResource notFound(String message){
        return new ErrorResource(HttpStatus.NOT_FOUND.name(), Arrays.asList(message));
    }
    
    public static ErrorResource badRequest(String message){
        return new ErrorResource(HttpStatus.BAD_REQUEST.name(), Arrays.asList(message));        
    }
    
    public static ErrorResource badRequest(Errors errors){         
        return new ErrorResource(HttpStatus.BAD_REQUEST.name(),
            errors.getAllErrors().parallelStream()
                    .map(error -> error.getDefaultMessage())
                    .collect(toList())
        ); 
    }
}
