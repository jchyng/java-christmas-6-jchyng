package christmas.service;


import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Event;
import christmas.constant.Gift;
import christmas.domain.Visit;
import christmas.domain.order.Orders;
import java.util.Map;
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

    @DisplayName("크리스마스 디데이 이벤트의 할인 금액을 계산")
    @Test
    void calcChristmasEvent() {
        //Given
        int date = 25;
        //When
        int discount = eventApplicant.getChristmasDiscount(date);
        //Then
        assertThat(discount).isEqualTo(3_400);
    }

    @DisplayName("주말 이벤트 총 할인 금액 계산")
    @Test
    void calcWeekendEvent() {
        //Given
        Orders orders = new Orders("티본스테이크-2");
        //When
        int discount = eventApplicant.getWeekendDiscount(orders);
        //Then
        assertThat(discount).isEqualTo(Event.주말_할인.discount() * 2);
    }

    @DisplayName("평일 이벤트 총 할인 금액 계산")
    @Test
    void calcWeekdayEvent() {
        //Given
        Orders orders = new Orders("아이스크림-2");
        //When
        int discount = eventApplicant.getWeekdayDiscount(orders);
        //Then
        assertThat(discount).isEqualTo(Event.평일_할인.discount() * 2);
    }

    @DisplayName("모든 이벤트를 적용한 혜택 내역을 계산한다.")
    @Test
    void calcBenefitInfo() {
        //Given
        Visit visit = new Visit(3);
        Orders orders = new Orders("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        //When
        Map<String, Integer> benefits = eventApplicant.getBenefitInfo(orders, visit);
        //Then
        assertThat(benefits.get(Event.크리스마스_디데이.getName())).isEqualTo(1_200);
        assertThat(benefits.get(Event.평일_할인.getName())).isEqualTo(4_046);
        assertThat(benefits.get(Event.특별_할인.getName())).isEqualTo(1_000);
        assertThat(benefits.get(Event.증정_이벤트.getName())).isEqualTo(25_000);
    }

    @DisplayName("모든 이벤트 적용 시 총 혜택 금액 계산")
    @Test
    void calcBenefitAmount() {
        //Given
        Visit visit = new Visit(3);
        Orders orders = new Orders("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Map<String, Integer> benefits = eventApplicant.getBenefitInfo(orders, visit);
        //When
        int benefitAmount = eventApplicant.getBenefitAmount(benefits);
        //Then
        assertThat(benefitAmount).isEqualTo(31_246);
    }

    @DisplayName("총 할인 받은 금액 계산")
    @Test
    void calcTotalDiscount() {
        //Given
        Visit visit = new Visit(3);
        Orders orders = new Orders("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Map<String, Integer> benefits = eventApplicant.getBenefitInfo(orders, visit);
        int benefitAmount = eventApplicant.getBenefitAmount(benefits);
        //When
        int totalDiscount = eventApplicant.getTotalDiscount(benefits, benefitAmount);
        //Then
        assertThat(totalDiscount).isEqualTo(6_246);
    }

    @DisplayName("최종 결재 금액 계산")
    @Test
    void calcFinalPayment() {
        //Given
        int amount = 11_000;
        int totalDiscount = 1_000;
        //When
        int finalPayment = eventApplicant.getFinalPayment(amount, totalDiscount);
        //Then
        assertThat(finalPayment).isEqualTo(10_000);
    }
}