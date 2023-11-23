package christmas;

import christmas.controller.Cashier;
import christmas.service.EventApplicant;

public class Application {
    public static void main(String[] args) {
        EventApplicant eventApplicant = new EventApplicant();
        Cashier cashier = new Cashier(eventApplicant);

        cashier.runAsManual();
    }
}
