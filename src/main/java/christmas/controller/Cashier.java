package christmas.controller;

import christmas.domain.Visit;
import christmas.view.InputView;
import java.util.function.Supplier;

public class Cashier {
    private final InputView inputView = new InputView();

    public void runAsManual() {
        Visit visit = getVisitDate();
        String order = inputView.readOrder();
    }

    private Visit getVisitDate() {
        return doWorkUntilComplete(() -> {
            int date = inputView.readDate();
            return new Visit(date);
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
