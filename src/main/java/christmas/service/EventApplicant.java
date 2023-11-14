package christmas.service;

import static christmas.constant.Event.CHRISTMAS_D_DAY_DISCOUNT_INCREMENT;

import christmas.constant.Event;
import christmas.constant.Gift;
import christmas.domain.Calendar;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.util.Optional;

public class EventApplicant {
    private final Calendar calendar = Calendar.getInstance();

    public Optional<Gift> getGift(Orders orders) {
        if (orders.getAmount() >= Gift.get().getTargetAmount()) {
            return Optional.of(Gift.get());
        }
        return Optional.empty();
    }

    public int getChristmasDiscount(int date) {
        return Event.크리스마스_디데이.getDiscount() + (date - 1) * Event.CHRISTMAS_D_DAY_DISCOUNT_INCREMENT;
    }

    public int getWeekendDiscount(Orders orders) {
        int discount = 0;
        for (Order order : orders.getOrders()) {
            discount += order.getWeekendDiscount();
        }
        return discount;
    }

    public int getWeekdayDiscount(Orders orders) {
        int discount = 0;
        for (Order order : orders.getOrders()) {
            discount += order.getWeekdayDiscount();
        }
        return discount;
    }
}
