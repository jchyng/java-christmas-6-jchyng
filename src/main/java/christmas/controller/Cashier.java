package christmas.controller;

import christmas.domain.Visit;
import christmas.domain.order.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.function.Supplier;

public class Cashier {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void runAsManual() {
        Visit visit = getVisitDate();
        Orders orders = takeOrders();

        outputView.printMenu(orders);
    }

    private Visit getVisitDate() {
        return doWorkUntilComplete(() -> {
            int date = inputView.readDate();
            return new Visit(date);
        });
    }

    private Orders takeOrders() {
        return doWorkUntilComplete(() -> {
            String input = inputView.readOrder();
            return new Orders(input);
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
