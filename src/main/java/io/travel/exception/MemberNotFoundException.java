package io.travel.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException() {
        this("회원을 찾을 수 없습니다.");
    }
}
