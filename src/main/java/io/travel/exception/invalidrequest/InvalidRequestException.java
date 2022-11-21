package io.travel.exception.invalidrequest;

public abstract class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }
}
