package com.example.exceptionHandling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Iterator;

@ControllerAdvice
public class GlobalExceptionHandler {

    //возвращает на фрот абсолютно все что хранит webRequest, включая вложенное в тело атрибуты, прикольно но бесполезно
    // нашёл способ получать тело запроса в обработчик исключений,  нашёл способа сделать это уневерсально для всех классов
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleException(IllegalArgumentException e, WebRequest webRequest) {

        Iterator<String> response = webRequest.getParameterNames();
        StringBuilder stringBuilder = new StringBuilder();
        while (response.hasNext()) {
            String n=response.next();
            stringBuilder.append( n + ": " + webRequest.getParameter(n) + "\n");
        }

        return new ResponseEntity<>(e.getMessage() + "\n" + stringBuilder.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
