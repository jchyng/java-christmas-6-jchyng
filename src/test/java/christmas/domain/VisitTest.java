package christmas.domain;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitTest {

    @DisplayName("방문 날짜 생성 시 날짜가 1~31 사이의 숫자가 아니라면 예외 발생")
    @ValueSource(strings = {"0", "32"})
    @ParameterizedTest
    void createVisitByOutOfRange(int date) {
        assertThatThrownBy(() -> new Visit(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}