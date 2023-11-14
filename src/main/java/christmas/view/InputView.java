package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ExceptionMessage;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        try {
            return convertToInt(input);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(ExceptionMessage.VISIT_FORMAT.getMessage());
        }
    }

    public String readOrder(){
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Console.readLine();
    }

    private int convertToInt(String input){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
