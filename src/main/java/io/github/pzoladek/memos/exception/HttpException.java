package io.github.pzoladek.memos.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class HttpException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final Map<String, String> jsonMessages;

    public HttpException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.jsonMessages = Map.of("message", message);
        this.httpStatus = httpStatus;
    }

    public ResponseEntity<Map<String, String>> getHttpResponse() {
        return new ResponseEntity<>(jsonMessages, httpStatus);
    }

}