package io.travel.exception.notfound;

public class CityNotFoundException extends NotFoundException {

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException() {
        this("도시를 찾을 수 없습니다.");
    }

}
