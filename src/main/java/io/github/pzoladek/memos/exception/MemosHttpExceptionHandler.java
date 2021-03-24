package io.github.pzoladek.memos.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MemosHttpExceptionHandler {

    @ExceptionHandler({ HttpException.class })
    public ResponseEntity<Map<String, String>> handleException(final HttpException ex) {
        return ex.getHttpResponse();
    }

}