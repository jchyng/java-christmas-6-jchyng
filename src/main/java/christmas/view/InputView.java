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

    private int convertToInt(String input){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
