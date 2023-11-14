package christmas.constant;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuBoard;

public enum Gift {
    샴페인(MenuBoard.샴페인.getMenu(), 120_000);

    private final Menu menu;
    private final int targetAmount;

    Gift(Menu menu, int targetAmount) {
        this.menu = menu;
        this.targetAmount = targetAmount;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getTargetAmount() {
        return targetAmount;
    }

    public static Gift get(){
        return 샴페인;
    }
}
