package christmas.view;

import christmas.constant.Gift;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Optional;

public class OutputView {
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#,##0원");
    private static final DecimalFormat NEGATIVE_PRICE_FORMAT = new DecimalFormat("-#,##0원");

    private static final String EMPTY = "없음";


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

    public void printGift(Optional<Gift> gift) {
        System.out.println("<증정 메뉴>");

        gift.ifPresentOrElse(
                p -> System.out.println(p.getMenu().getName() + " 1개"),
                () -> System.out.println(EMPTY)
        );
        System.out.println();
    }

    public void printBenefitInfo(Map<String, Integer> benefits) {
        System.out.println("<혜택 내역>");

        if (benefits.isEmpty()) {
            System.out.println(EMPTY);
            return;
        }
        for (Map.Entry<String, Integer> entry : benefits.entrySet()) {
            System.out.println(entry.getKey() + ": " + NEGATIVE_PRICE_FORMAT.format(entry.getValue()));
        }
        System.out.println();
    }
}
