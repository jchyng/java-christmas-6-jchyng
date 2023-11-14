package christmas.service;


import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Event;
import christmas.constant.Gift;
import christmas.domain.order.Orders;
import org.junit.jupiter.api.DisplayName;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class EventApplicantTest {
    private EventApplicant eventApplicant = new EventApplicant();

    @DisplayName("총 구매 금액이 120,000원을 넘는다면 증정품을 준다.")
    @Test
    void getGiftIfOverTargetAmount() {
        //Given
        Orders orders = new Orders("티본스테이크-3");
        //When
        Optional<Gift> gift = eventApplicant.getGift(orders);
        //Then
        assertThat(gift).isPresent();
    }

    @DisplayName("총 구매 금액이 120,000원 이하라면 증정품을 주지않는다.")
    @Test
    void getGiftIfUnderTargetAmount() {
        //Given
        Orders orders = new Orders("티본스테이크-1");
        //When
        Optional<Gift> gift = eventApplicant.getGift(orders);
        //Then
        assertThat(gift).isEmpty();
    }

    @DisplayName("평일 이벤트 총 할인 금액 계산")
    @Test
    void calcWeekdayEvent() {
        //Given
        Orders orders = new Orders("아이스크림-2");
        //When
        int discount = eventApplicant.getAllWeekdayDiscount(orders);
        //Then
        assertThat(discount).isEqualTo(Event.평일_할인.discount() * 2);
    }
}