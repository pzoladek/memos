package io.github.pzoladek.memos.exception;

import org.springframework.http.HttpStatus;

public class FolderException extends HttpException {

    private FolderException(final String message, final HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public static FolderException notFound(final String name, final String author) {
        return new FolderException(
                String.format("Folder with name '%s' was not found for user '%s'.", name, author),
                HttpStatus.NOT_FOUND
        );
    }

    public static FolderException alreadyExists(final String name, final String author) {
        return new FolderException(
                String.format("Folder with name '%s' already exists for user '%s'.", name, author),
                HttpStatus.BAD_REQUEST
        );
    }
}
