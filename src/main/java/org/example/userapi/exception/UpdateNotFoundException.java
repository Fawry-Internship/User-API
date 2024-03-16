package org.example.userapi.exception;

public class UpdateNotFoundException extends RuntimeException{
    public UpdateNotFoundException() {
        super();
    }

    public UpdateNotFoundException(String message) {
        super(message);
    }
}
