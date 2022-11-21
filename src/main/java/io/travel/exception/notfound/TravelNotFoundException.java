package io.travel.exception.notfound;

public class TravelNotFoundException extends NotFoundException {

    public TravelNotFoundException(String message) {
        super(message);
    }

    public TravelNotFoundException() {
        this("여행을 찾을 수 없습니다.");
    }
}
