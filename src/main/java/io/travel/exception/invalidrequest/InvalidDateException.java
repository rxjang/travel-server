package io.travel.exception.invalidrequest;

public class InvalidDateException extends InvalidRequestException {
    public InvalidDateException(String message) {
        super(message);
    }

    public InvalidDateException() {
        this("여행 종료일은 현재 또는 시작일보다 미래여야 합니다.");
    }
}
