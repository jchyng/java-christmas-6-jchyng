package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuBoard;
import christmas.exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Orders {
    private final int MAX_COUNT = 20;
    private final List<Order> orders;

    public Orders(String input) {
        OrderFormatValidate(input);
        this.orders = createOrders(input);
    }

    public List<Order> getOrders() {
        return orders;
    }

    private List<Order> createOrders(String input) {
        List<Order> orders = new ArrayList<>();
        String[] inputOrders = input.split(",");

        for (String inputOrder : inputOrders) {
            String[] menuAndCount = inputOrder.split("-");
            Menu menu = MenuBoard.getMenu(menuAndCount[0]);
            int count = Integer.parseInt(menuAndCount[1]);

            orders.add(new Order(menu, count));
        }
        validate(orders);
        return orders;
    }

    private void validate(List<Order> orders) {
        if (isDuplicateOrder(orders)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATE_ORDER.getMessage());
        }
        if(isOverMAXCount(orders)){
            throw new IllegalArgumentException(ExceptionMessage.OVER_MAX_COUNT_ORDER.getMessage());
        }
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

    private boolean isDuplicateOrder(List<Order> orders) {
        Set<Menu> uniqueMenus = new HashSet<>();
        for (Order order : orders) {
            if (!uniqueMenus.add(order.getMenu())) {
                return true;
            }
        }
        return false;
    }

    private boolean isOverMAXCount(List<Order> orders){
        int count = orders.stream()
                .mapToInt(Order::getCount)
                .sum();
        if(count > MAX_COUNT){
            return true;
        }
        return false;
    }
}
