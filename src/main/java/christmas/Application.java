package christmas;

import christmas.controller.Cashier;
import christmas.service.EventApplicant;

public class Application {
    public static void main(String[] args) {
        Cashier cashier = new Cashier(new EventApplicant());

        cashier.runAsManual();
    }
}
