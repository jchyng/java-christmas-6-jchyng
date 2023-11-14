package christmas.domain;

import christmas.exception.ExceptionMessage;

public class Visit {
    private final int date;
    public Visit(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date) {
        if (!isWithinRange(date)) {
            throw new IllegalArgumentException(ExceptionMessage.VISIT_FORMAT.getMessage());
        }
    }

    private boolean isWithinRange(int date) {
        if (date >= Calendar.START_DATE && date <= Calendar.END_DATE) {
            return true;
        }
        return false;
    }

}
