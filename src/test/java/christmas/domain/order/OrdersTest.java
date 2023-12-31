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

    @DisplayName("주문한 메뉴 목록 생성")
    @Test
    void createOrders() {
        //Given
        String order = "해산물파스타-2,레드와인-1,초코케이크-1";
        //When
        Orders orders = new Orders(order);
        //Then
        assertThat(orders.getOrders()).contains(
                new Order(MenuBoard.getMenu("해산물파스타"), 2),
                new Order(MenuBoard.getMenu("레드와인"), 1),
                new Order(MenuBoard.getMenu("초코케이크"), 1)
        );
    }

    @DisplayName("메뉴를 중복해서 주문 시 예외 발생")
    @Test
    void createOrderByDuplicate() {
        //When & Then
        assertThatThrownBy(() -> new Orders("양송이수프-1,양송이수프-2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    }

    @DisplayName("없는 메뉴 주문 시 예외 발생")
    @Test
    void createOrderByNotExist() {
        //When & Then
        assertThatThrownBy(() -> new Orders("없는메뉴-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("총 주문 개수가 20개 이상이라면 예외 발생")
    @Test
    void createOrderByOverMinCount() {
        assertThatThrownBy(() -> new Orders("해산물파스타-20,레드와인-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴가 음료 밖에 없다면 예외 발생")
    @Test
    void createOrderByOnlyJuice() {
        assertThatThrownBy(() -> new Orders("레드와인-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    }

    @DisplayName("주문 목록의 총 금액을 계산")
    @Test
    void calcAmountForOrders() {
        //Given
        Orders orders = new Orders("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        //When
        int amount = orders.getAmount();
        //Then
        assertThat(amount).isEqualTo(142_000);
    }
}