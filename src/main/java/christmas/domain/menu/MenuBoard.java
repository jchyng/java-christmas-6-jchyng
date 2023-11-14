package christmas.domain.menu;

import christmas.constant.MenuCategory;
import christmas.exception.ExceptionMessage;
import java.util.HashMap;
import java.util.Map;

public enum MenuBoard {
    양송이수프(new Menu("양송이수프", 6_000, MenuCategory.애피타이저)),
    타파스(new Menu("타파스", 5_500, MenuCategory.애피타이저)),
    시저샐러드(new Menu("시저샐러드", 8_000, MenuCategory.애피타이저)),

    티본스테이크(new Menu("티본스테이크", 55_000, MenuCategory.메인)),
    바비큐립(new Menu("바비큐립", 54_000, MenuCategory.메인)),
    해산물파스타(new Menu("해산물파스타", 35_000, MenuCategory.메인)),
    크리스마스파스타(new Menu("크리스마스파스타", 25_000, MenuCategory.메인)),

    초코케이크(new Menu("초코케이크", 15_000, MenuCategory.디저트)),
    아이스크림(new Menu("아이스크림", 5_000, MenuCategory.디저트)),

    제로콜라(new Menu("제로콜라", 3_000, MenuCategory.음료)),
    레드와인(new Menu("레드와인", 60_000, MenuCategory.음료)),
    샴페인(new Menu("샴페인", 25_000, MenuCategory.음료));

    private final Menu menu;

    private static final Map<String, Menu> menus = new HashMap<>();

    static {
        for (MenuBoard menuBoard : MenuBoard.values()) {
            menus.put(menuBoard.menu.getName(), menuBoard.menu);
        }
    }

    MenuBoard(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    public static Menu getMenu(String name) {
        Menu menu = menus.get(name);
        validate(menu);
        return menu;
    }

    private static void validate(Menu menu){
        if(menu == null){
            throw new IllegalArgumentException(ExceptionMessage.INPUT_ORDER.getMessage());
        }
    }
}
