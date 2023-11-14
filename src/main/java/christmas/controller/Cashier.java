package christmas.controller;

import christmas.constant.Gift;
import christmas.domain.Visit;
import christmas.domain.order.Orders;
import christmas.service.EventApplicant;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class Cashier {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private final EventApplicant eventApplicant;

    public Cashier(EventApplicant eventApplicant) {
        this.eventApplicant = eventApplicant;
    }

    public void runAsManual() {
        Visit visit = getVisitDate();
        Orders orders = takeOrders();
        int amount = orders.getAmount();

        outputView.printMenu(orders);

        outputView.printAmount(amount);

        Optional<Gift> gift = eventApplicant.getGift(orders);
        outputView.printGift(gift);

        Map<String, Integer> benefits = eventApplicant.getBenefitInfo(orders, visit);
        outputView.printBenefitInfo(benefits);

        int benefitAmount = eventApplicant.getBenefitAmount(benefits);
        outputView.printBenefitAmount(benefitAmount);

        int discount = eventApplicant.getTotalDiscount(benefits, benefitAmount);
        outputView.printFinalPayment(eventApplicant.getFinalPayment(amount, discount));
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
