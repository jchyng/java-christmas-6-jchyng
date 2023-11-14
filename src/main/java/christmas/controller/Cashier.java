package christmas.controller;

import christmas.view.InputView;
import java.util.function.Supplier;

public class Cashier {
    private final InputView inputView = new InputView();

    public void runAsManual() {
        int date = doWorkUntilComplete(() -> {
            return inputView.readDate();
        });
    }

    private <T> T doWorkUntilComplete(Supplier<T> work) {
        while (true) {
            try {
                return work.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
