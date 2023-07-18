package service;

import dao.OrderDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import vo.OrderListVO;
import vo.OrderVO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class KioskOrderService implements Action{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("login");
        int storeno = user.getNo();
        String place = request.getParameter("place");
        int price = Integer.parseInt(request.getParameter("price"));
        String[] menus = request.getParameterValues("menuNum");
        String[] quantities = request.getParameterValues("cnt");

        OrderDAO dao = new OrderDAO();
        OrderVO order = dao.insertOrder(price, storeno);
        order.setPlace(place);
        ArrayList<OrderListVO> list = new ArrayList<>();
        for (int i=0; i<menus.length; i++) {
            dao.insertOrderList(order.getNo(), Integer.parseInt(menus[i]), Integer.parseInt(quantities[i]));
            OrderListVO vo = new OrderListVO();
            vo.setMenuNo(Integer.parseInt(menus[i]));
            vo.setQuantity(Integer.parseInt(quantities[i]));
            list.add(vo);
        }
        order.setMenuList(list);
        AlarmCenter.getInstance().send(storeno, order);
        return null;
    }
}
