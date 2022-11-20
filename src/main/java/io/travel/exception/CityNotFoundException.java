package io.travel.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException() {
        this("도시를 찾을 수 없습니다.");
    }

}
