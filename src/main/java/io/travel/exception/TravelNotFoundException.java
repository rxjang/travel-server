package io.travel.exception;

public class TravelNotFoundException extends RuntimeException {

    public TravelNotFoundException(String message) {
        super(message);
    }

    public TravelNotFoundException() {
        this("여행을 찾을 수 없습니다.");
    }
}
