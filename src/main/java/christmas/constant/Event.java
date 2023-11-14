package christmas.constant;

import christmas.domain.Calendar;

public enum Event {

    크리스마스_디데이(1_000, Calendar.START_DATE, Calendar.CHRISTMAS),
    평일_할인(2_023, Calendar.START_DATE, Calendar.END_DATE),
    주말_할인(2_023, Calendar.START_DATE, Calendar.END_DATE),
    특별_할인(1_000, Calendar.START_DATE, Calendar.END_DATE),
    증정_이벤트(0, Calendar.START_DATE, Calendar.END_DATE);

    private final static int CHRISTMAS_D_DAY_DISCOUNT_INCREMENT = 100;
    private final int discount;
    private final int startDate;
    private final int endDate;

    Event(int discount, int startDate, int endDate) {
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static int getChristmasDiscount(int date) {
        return Event.크리스마스_디데이.discount + (date - 1) * CHRISTMAS_D_DAY_DISCOUNT_INCREMENT;
    }

    public boolean isValid(int date) {
        if (date >= startDate && date <= endDate) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name().replace("_", " ");
    }

    public int discount() {
        return discount;
    }
}