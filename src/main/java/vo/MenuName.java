package vo;

import dao.MenuDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuName {
    static private HashMap<Integer, MenuVO> menuMap = null;
    static private HashMap<String, ArrayList<MenuVO>> classifiedMap = null;
    static boolean isUpdate = false;

    public static String getName(int no) {
        if (menuMap == null || isUpdate) {
            updateData();
        }
        return menuMap.get(no).getName();
    }

    public static ArrayList<MenuVO> getCategoryList(String category) {
        if (classifiedMap == null || isUpdate) {
            updateData();
        }
        return classifiedMap.get(category);
    }

    static void updateData() {
        MenuDAO dao = new MenuDAO();
        List<MenuVO> list = dao.selectAll();
        menuMap = new HashMap<>();
        classifiedMap = new HashMap<>();

        for (MenuVO menu : list) {
            menuMap.put(menu.getNo(), menu);
            if (!classifiedMap.containsKey(menu.getCategory())) {
                classifiedMap.put(menu.getCategory(), new ArrayList<>());
            }
            classifiedMap.get(menu.getCategory()).add(menu);
        }
        isUpdate = false;
        return;
    }
}
