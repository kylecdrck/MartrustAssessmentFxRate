package com.martrust.fxratesapi.advisor;

import com.martrust.fxratesapi.model.APIResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvisor {

    @ExceptionHandler(BindException.class)
    public APIResponse<?> globalBindExceptionHandler(BindException e){
        StringBuilder sb = new StringBuilder("Failed. ");
        FieldError fe = e.getBindingResult().getFieldErrors().get(0);
        sb.append(fe.getDefaultMessage());
        sb.append(" (" ).append(fe.getRejectedValue()).append(").");
        return new APIResponse<>(sb.toString(), null);
    }

    @ExceptionHandler(Exception.class)
    public APIResponse<?> globalExceptionHandler(Exception e){
        return new APIResponse<>(e.getMessage(), null);
    }
}
