package christmas.domain.order;

import christmas.exception.ExceptionMessage;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Orders {
    private final Set<Order> orders;

    public Orders(String input) {
        OrderFormatValidate(input);

        this.orders = new HashSet<>();
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
