package com.aquilesleite.todosimple.configExceptions;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ErrorResponse {
    private final int status;
    private final String message;
    private String stackTrace;
    private List<ValiadationError> errors;

@Getter
@Setter
@RequiredArgsConstructor

    private static class ValiadationError {

    private final String field;
    private final String message;    
    }

    public void addValidationError(String field, String message){
        if(Objects.isNull(errors)){
            this.errors = new ArrayList<>();
        }
        this.errors.add(new ValiadationError(field, message));
    }

    public String toJson() {
        return "{\"status\": " + getStatus() + ", " +
                "\"message\": \"" + getMessage() + "\"}";
    }

}
