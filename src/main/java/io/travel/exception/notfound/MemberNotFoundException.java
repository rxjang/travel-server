package io.travel.exception.notfound;

public class MemberNotFoundException extends NotFoundException {

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException() {
        this("회원을 찾을 수 없습니다.");
    }
}
