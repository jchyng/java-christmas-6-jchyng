package christmas.domain.order;

import christmas.constant.Event;
import christmas.constant.MenuCategory;
import christmas.domain.menu.Menu;
import java.util.Objects;

public class Order {
    private final Menu menu;
    private final int count;

    public Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public int getAmount() {
        return menu.getPrice() * count;
    }

    public int getWeekendDiscount(){
        if(menu.getCategory().equals(MenuCategory.메인)){
            return Event.주말_할인.discount() * count;
        }
        return 0;
    }

    public int getWeekdayDiscount(){
        if(menu.getCategory().equals(MenuCategory.디저트)){
            return Event.평일_할인.discount() * count;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return count == order.count && Objects.equals(menu, order.menu);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }
}
