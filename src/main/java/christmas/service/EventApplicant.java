package christmas.service;

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

    public int getAllWeekdayDiscount(Orders orders) {
        int discount = 0;
        for (Order order : orders.getOrders()) {
            discount += order.getWeekdayDiscount();
        }
        return discount;
    }
}
