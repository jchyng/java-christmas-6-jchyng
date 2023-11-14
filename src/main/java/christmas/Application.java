package christmas;

import christmas.controller.Cashier;

public class Application {
    public static void main(String[] args) {
        Cashier cashier = new Cashier();

        cashier.runAsManual();
    }
}
