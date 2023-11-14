package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrdersTest {

    @DisplayName("입력받은 주문의 양식이 다르다면 예외 발생")
    @ValueSource(strings = {"해산물파스타-a", "해산물파스타*2", "해산물파스타-2-레드와인-1"})
    @ParameterizedTest
    void inputOrderByMismatchFormat(String input) {
        assertThatThrownBy(() -> new Orders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}