package io.github.pzoladek.memos.exception;

import org.springframework.http.HttpStatus;

public class UserException extends HttpException {

    private UserException(final String message, final HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public static UserException alreadyExists(final String login) {
        return new UserException(
                String.format("User with login '%s' already exists.", login),
                HttpStatus.BAD_REQUEST
        );
    }
}
