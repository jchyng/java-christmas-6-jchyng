package christmas.controller;

import christmas.view.InputView;

public class Cashier {
    private final InputView inputView = new InputView();

    public void runAsManual() {
        int date = inputView.readDate();
    }
}
