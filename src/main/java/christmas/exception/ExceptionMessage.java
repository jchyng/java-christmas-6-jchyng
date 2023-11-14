package christmas.exception;

public enum ExceptionMessage {
    VISIT_FORMAT("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INPUT_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DUPLICATE_ORDER("같은 메뉴를 중복으로 주문할 수 없습니다. 다시 입력해 주세요."),
    ;

    private static final String prefix = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message) {
        this.message = prefix + message;
    }

    public String getMessage() {
        return message;
    }
}
