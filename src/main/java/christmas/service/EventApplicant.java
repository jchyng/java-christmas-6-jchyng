package christmas.service;

import static christmas.constant.Event.CHRISTMAS_D_DAY_DISCOUNT_INCREMENT;

import christmas.constant.Event;
import christmas.constant.Gift;
import christmas.domain.Calendar;
import christmas.domain.Visit;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EventApplicant {
    private final Calendar calendar = Calendar.getInstance();

    public Map<String, Integer> getBenefitInfo(Orders orders, Visit visit) {
        Map<String, Integer> benefits = new HashMap<>();
        List<Event> events = calendar.getEventsByDate(visit.getDate());

        applyChristmasEvent(benefits, events, visit);
        applyPresentEvent(benefits, events, orders);
        applyWeekdayEvent(benefits, events, orders);
        applyWeekendEvent(benefits, events, orders);
        applySpecialEvent(benefits, events);

        return benefits;
    }

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

    private void applyChristmasEvent(Map<String, Integer> benefits, List<Event> events, Visit visit) {
        if (events.contains(Event.크리스마스_디데이)) {
            benefits.put(Event.크리스마스_디데이.getName(), getChristmasDiscount(visit.getDate()));
        }
    }

    private void applyPresentEvent(Map<String, Integer> benefits, List<Event> events, Orders orders) {
        if (events.contains(Event.증정_이벤트)) {
            getGift(orders).ifPresent((gift) ->
                    benefits.put(Event.증정_이벤트.getName(), gift.getMenu().getPrice())
            );
        }
    }

    private void applyWeekdayEvent(Map<String, Integer> benefits, List<Event> events, Orders orders) {
        if (events.contains(Event.평일_할인)) {
            int discount = getWeekdayDiscount(orders);
            if (discount > 0) {
                benefits.put(Event.평일_할인.getName(), discount);
            }
        }
    }

    private void applyWeekendEvent(Map<String, Integer> benefits, List<Event> events, Orders orders) {
        if (events.contains(Event.주말_할인)) {
            int discount = getWeekendDiscount(orders);
            if (discount > 0) {
                benefits.put(Event.주말_할인.getName(), discount);
            }
        }
    }

    private void applySpecialEvent(Map<String, Integer> benefits, List<Event> events) {
        if (events.contains(Event.특별_할인)) {
            benefits.put(Event.특별_할인.getName(), Event.특별_할인.discount());
        }
    }
}
