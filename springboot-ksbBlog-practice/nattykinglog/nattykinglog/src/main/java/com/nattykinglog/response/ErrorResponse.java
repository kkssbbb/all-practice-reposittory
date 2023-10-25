package com.nattykinglog.response;
/*
* {
*   "code" : "400",
*    "message" : "잘못된 요청입니다.",
*    "validation" : {
*           "title": "값을 입력해주세요"
*       }
* }
*
*
* */

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String code;
    private final String message;

    private  Map<String, String> validation = new HashMap<>();

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
