package io.travel.exception.invalidrequest;

public class CannotDeleteCityException extends InvalidRequestException {

    public CannotDeleteCityException() {
        this("진행 중인 여행이 있으므로 도시 삭제가 불가능합니다.");
    }

    public CannotDeleteCityException(String message) {
        super(message);
    }
}
