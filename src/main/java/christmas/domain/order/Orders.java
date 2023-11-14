package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuBoard;
import christmas.exception.ExceptionMessage;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Orders {
    private final Set<Order> orders;

    public Orders(String input) {
        OrderFormatValidate(input);
        this.orders = createOrders(input);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    private Set<Order> createOrders(String input) {
        Set<Order> orders = new HashSet<>();
        String[] inputOrders = input.split(",");

        for (String inputOrder : inputOrders) {
            String[] menuAndCount = inputOrder.split("-");
            Menu menu = MenuBoard.getMenu(menuAndCount[0]);
            int count = Integer.parseInt(menuAndCount[1]);

            orders.add(new Order(menu, count));
        }
        return orders;
    }

    private void OrderFormatValidate(String input) {
        if (!isMatchOrderFormat(input)) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_ORDER.getMessage());
        }
    }

    private boolean isMatchOrderFormat(String input) {
        String regex = "^([ㄱ-ㅎ가-힣]+-\\d+)(?:,[ㄱ-ㅎ가-힣]+-\\d+)*$";   //메뉴-개수(1~20)..., 메뉴-개수
        return Pattern.matches(regex, input);
    }

}
