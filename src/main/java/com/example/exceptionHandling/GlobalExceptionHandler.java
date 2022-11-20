package com.example.exceptionHandling;


import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.PropertyValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    //возвращает на фрот абсолютно все что хранит webRequest, включая вложенное в тело атрибуты, прикольно но бесполезно
    //я нашёл способ получать тело запроса в обработчик исключений, не не нашёл способа сделать это уневерсально для всех классов
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleException(IllegalArgumentException e, WebRequest webRequest) {

        List<String> response = Arrays.stream(webRequest.getAttributeNames(RequestAttributes.SCOPE_REQUEST)).map(n ->
               "***************\n"+n+": "+  webRequest.getAttribute(n, RequestAttributes.SCOPE_REQUEST).toString()+"\n").toList();



        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
