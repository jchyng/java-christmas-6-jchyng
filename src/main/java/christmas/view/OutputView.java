package christmas.view;

import christmas.domain.order.Order;
import christmas.domain.order.Orders;

public class OutputView {

    public void printMenu(Orders orders) {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<주문 메뉴>");

        for (Order order : orders.getOrders()) {
            System.out.println(order.getMenu().getName() + " " + order.getCount() + "개");
        }
        System.out.println();
    }

}
