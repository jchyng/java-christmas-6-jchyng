package christmas.view;

import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.text.DecimalFormat;

public class OutputView {
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,##0원");
    

    public void printMenu(Orders orders) {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<주문 메뉴>");

        for (Order order : orders.getOrders()) {
            System.out.println(order.getMenu().getName() + " " + order.getCount() + "개");
        }
        System.out.println();
    }

    public void printAmountBeforeDiscount(int amount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(PRICE_FORMAT.format(amount));
        System.out.println();
    }
}
