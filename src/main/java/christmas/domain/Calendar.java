package christmas.domain;

import christmas.constant.Event;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Calendar {
    private static Calendar instance;

    public static final int YEAR = 2023;
    public static final int MONTH = 12;
    public static final int START_DATE = 1;
    public static final int END_DATE = 31;
    public static final int CHRISTMAS = 25;

    private final Map<Integer, List<Event>> calendar;

    public Calendar() {
        this.calendar = createCalendar();
    }

    public static Calendar getInstance() {
        if (instance == null) {
            instance = new Calendar();
        }
        return instance;
    }

    public Map<Integer, List<Event>> getCalendar() {
        return calendar;
    }

    public List<Event> getEventsByDate(int date) {
        return calendar.get(date);
    }

    private Map<Integer, List<Event>> createCalendar() {
        Map<Integer, List<Event>> map = new HashMap<>();

        for (int date = START_DATE; date < END_DATE; date++) {
            List<Event> events = getEventsOnDate(date);

            if (events.size() > 0) {
                map.put(date, events);
            }
        }
        return map;
    }

    private List<Event> getEventsOnDate(int date) {
        List<Event> events = new ArrayList<>();
        DayOfWeek dayOfWeek = getDayOfWeekByDate(date);

        getChristmasEvent(date).ifPresent(events::add);
        getPresentEvent(date).ifPresent(events::add);
        getWeekendEvent(dayOfWeek, date).ifPresent(events::add);
        getWeekdayEvent(dayOfWeek, date).ifPresent(events::add);
        getSpecialEvent(dayOfWeek, date).ifPresent(events::add);

        return events;
    }

    private DayOfWeek getDayOfWeekByDate(int date) {
        LocalDate localDate = LocalDate.of(YEAR, MONTH, date);
        return localDate.getDayOfWeek();
    }

    private Optional<Event> getChristmasEvent(int date) {
        if (isChristmasEvent(date)) {
            return Optional.of(Event.크리스마스_디데이);
        }
        return Optional.empty();
    }

    private Optional<Event> getPresentEvent(int date) {
        if (isPresentEvent(date)) {
            return Optional.of(Event.증정_이벤트);
        }
        return Optional.empty();
    }

    private Optional<Event> getWeekendEvent(DayOfWeek dayOfWeek, int date) {
        if (isWeekendEvent(dayOfWeek, date)) {
            return Optional.of(Event.주말_할인);
        }
        return Optional.empty();
    }

    private Optional<Event> getWeekdayEvent(DayOfWeek dayOfWeek, int date) {
        if (isWeekdayEvent(dayOfWeek, date)) {
            return Optional.of(Event.평일_할인);
        }
        return Optional.empty();
    }

    private Optional<Event> getSpecialEvent(DayOfWeek dayOfWeek, int date) {
        if (isSpecialEvent(dayOfWeek, date)) {
            return Optional.of(Event.특별_할인);
        }
        return Optional.empty();
    }

    private boolean isChristmasEvent(int date) {
        if (!Event.크리스마스_디데이.isValid(date)) {
            return false;
        }
        return true;
    }

    private boolean isPresentEvent(int date) {
        if (Event.증정_이벤트.isValid(date)) {
            return true;
        }
        return false;
    }

    private boolean isWeekendEvent(DayOfWeek dayOfWeek, int date) {
        if (Event.주말_할인.isValid(date) && isWeekend(dayOfWeek)) {
            return true;
        }
        return false;
    }

    private boolean isWeekdayEvent(DayOfWeek dayOfWeek, int date) {
        if (Event.평일_할인.isValid(date) && !isWeekend(dayOfWeek)) {
            return true;
        }
        return false;
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        if (dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY)) {
            return true;
        }
        return false;
    }

    private boolean isSpecialEvent(DayOfWeek dayOfWeek, int date) {
        if (Event.특별_할인.isValid(date) &&
                (dayOfWeek.equals(DayOfWeek.SUNDAY) || date == CHRISTMAS)) {
            return true;
        }
        return false;
    }

}
