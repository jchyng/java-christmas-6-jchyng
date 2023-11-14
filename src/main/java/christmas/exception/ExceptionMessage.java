package christmas.exception;

public enum ExceptionMessage {
    VISIT_FORMAT("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String prefix = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message) {
        this.message = prefix + message;
    }

    public String getMessage() {
        return message;
    }
}
