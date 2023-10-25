package com.nattykinglog.controller;


import com.nattykinglog.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody //json 형태로 응답하기 위해
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
      /*  FieldError fieldError = e.getFieldError();
        String field = fieldError.getField();
        String message = fieldError.getDefaultMessage();

        Map<String, String> response = new HashMap<>();
        response.put(field, message);
        return response;*/


        ErrorResponse errorResponse = new ErrorResponse("400", "잘못된 요청입니다 선생님");

        for (FieldError fieldError : e.getFieldErrors()) {
            errorResponse.addValidation(fieldError.getField(), fieldError.getDefaultMessage());

        }

        return errorResponse;
        //
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public void exception(Exception e) {

        log.info("exception 예외에 걸리셨습니다 선생님",e);
        //System.out.println("예외 처리 좀 돼 라");
    }
}
