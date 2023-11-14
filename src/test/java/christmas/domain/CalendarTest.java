package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Event;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalendarTest {
    private Calendar calendar = Calendar.getInstance();

    @DisplayName("날짜가 12월 1일~25일이라면 크리스마스 디데이 이벤트가 해당된다.")
    @ValueSource(ints = {1, 25})
    @ParameterizedTest
    void validChristmasEvent(int date) {
        //Given
        List<Event> events = calendar.getEventsByDate(date);
        //When
        boolean isContain = events.contains(Event.크리스마스_디데이);
        //Then
        assertThat(isContain).isTrue();
    }

    @DisplayName("날짜가 12월 1일~25일이 아니라면 크리스마스 디데이 이벤트가 해당되지 않는다.")
    @Test
    void invalidChristmasEvent() {
        //Given
        int date = 26;
        List<Event> events = calendar.getEventsByDate(date);
        //When
        boolean isContain = events.contains(Event.크리스마스_디데이);
        //Then
        assertThat(isContain).isFalse();
    }

    @DisplayName("금요일과 토요일에는 주말 이벤트가 적용된다.")
    @ValueSource(ints = {1, 30})
    @ParameterizedTest
    void validWeekendEvent(int date) {
        //Given
        List<Event> events = calendar.getEventsByDate(date);
        //When
        boolean isContain = events.contains(Event.주말_할인);
        //Then
        assertThat(isContain).isTrue();
    }

    @DisplayName("금요일과 토요일 외에는 주말 이벤트가 적용되지 않는다.")
    @ValueSource(ints = {3, 28})
    @ParameterizedTest
    void invalidWeekendEvent(int date) {
        //Given
        List<Event> events = calendar.getEventsByDate(date);
        //When
        boolean isContain = events.contains(Event.주말_할인);
        //Then
        assertThat(isContain).isFalse();
    }
}