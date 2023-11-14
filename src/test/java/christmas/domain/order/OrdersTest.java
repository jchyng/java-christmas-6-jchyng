package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @DisplayName("메뉴판에서 이름으로 메뉴를 찾는다.")
    @Test
    void getMenuByName() {
        //Given
        String name = "해산물파스타";
        //When
        Menu menu = MenuBoard.getMenu(name);
        //Then
        assertThat(menu).isEqualTo(MenuBoard.해산물파스타.getMenu());
    }
}