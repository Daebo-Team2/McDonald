package service;

import dao.OrderDAO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KioskOrderService implements Action{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("login");
        int storeno = user.getNo();
        String place = request.getParameter("place");
        int price = Integer.parseInt(request.getParameter("price"));
        String[] menus = request.getParameterValues("menuNum");
        String[] quantitys = request.getParameterValues("cnt");

        OrderDAO dao = new OrderDAO();
        int key = dao.insertOrder(price, storeno);
        for (int i=0; i<menus.length; i++) {
            dao.insertOrderList(key, Integer.parseInt(menus[i]), Integer.parseInt(quantitys[i]));
        }

        return null;
    }
}
