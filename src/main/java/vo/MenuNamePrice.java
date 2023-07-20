package vo;

import dao.MenuDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MenuNamePrice {
    static HashMap<Integer, MenuVO> menuMap = null;
    static public boolean isUpdate = false;

    static String getMenuName(int no) {
        if (menuMap == null || isUpdate) {
            MenuDAO dao = new MenuDAO();
            List<MenuVO> list = dao.selectAll();
            menuMap = new HashMap<>();
            for (MenuVO menu : list) {
                menuMap.put(menu.getNo(), menu);
            }
            isUpdate = false;
        }

        MenuVO menu = menuMap.get(no);
        return menu == null ? null : menu.getName();
    }
    static int getMenuPrice(int no) {
        if (menuMap == null || isUpdate) {
            MenuDAO dao = new MenuDAO();
            List<MenuVO> list = dao.selectAll();
            menuMap = new HashMap<>();
            for (MenuVO menu : list) {
                menuMap.put(menu.getNo(), menu);
            }
            isUpdate = false;
        }

        MenuVO menu = menuMap.get(no);
        return menu == null ? 0 : menu.getPrice();
    }
}