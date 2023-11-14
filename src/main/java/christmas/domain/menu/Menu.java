package christmas.domain.menu;

import christmas.constant.MenuCategory;

public class Menu {
    private final String name;
    private final int price;
    private final MenuCategory category;

    public Menu(String name, int price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
