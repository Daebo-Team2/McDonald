package service;

import dao.StockOrderDAO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminStockOrderDeleteService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("login");
        int storeNo = user.getNo();
        int stockOrderNo = Integer.parseInt(request.getParameter("num"));

        StockOrderDAO dao = new StockOrderDAO();
        int result = dao.deleteAdminStockOrder(stockOrderNo, storeNo);
        request.setAttribute("result", result);
        forward.setPath("/admin/stockorderlist.do");
        return forward;
    }
}
