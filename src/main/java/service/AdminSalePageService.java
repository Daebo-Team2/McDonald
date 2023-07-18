package service;

import dao.SaleDAO;
import vo.OrderListVO;
import vo.OrderVO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminSalePageService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("login");
        int storeNo = user.getNo();

        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String menuName = request.getParameter("menuName");
        if (start == null || end == null || menuName == null) {
            forward = new ActionForward();
            forward.setPath("/WEB-INF/component/admin/saleContent.jsp");
            return forward;
        }

        String reqPageNo = request.getParameter("pageNo");
        int pageNo = 1;
        if (reqPageNo != null && !reqPageNo.equals("")) {
            pageNo = Integer.parseInt(reqPageNo);
        }

        try {
            SaleDAO dao = new SaleDAO();
            int totalCount = 0;
            int totalPrice = 0;
            boolean isMenuName = false;
            if (menuName.equals("")) {
                totalCount = dao.orderCount(start.equals("") ? "2000-01-01" : start,
                        end.equals("") ? "2099-12-31" : end, storeNo);
                totalPrice = dao.getOrderSelectTotalPrice(start.equals("") ? "2000-01-01" : start,
                        end.equals("") ? "2099-12-31" : end, storeNo);
            } else {
                totalCount = dao.menuCount(start.equals("") ? "2000-01-01" : start,
                        end.equals("") ? "2099-12-31" : end, menuName, storeNo);
                totalPrice = dao.getMenuSelectTotalPrice(start.equals("") ? "2000-01-01" : start,
                        end.equals("") ? "2099-12-31" : end, menuName, storeNo);
                isMenuName = true;
            }

            int pageSize = 10;
            int blockPage = 3;
            int totalPage = (int) Math.ceil((double) totalCount / pageSize);
            int pageStart = ((pageNo - 1) / blockPage) * blockPage + 1;
            int pageEnd = Math.min(pageStart - 1 + blockPage, totalPage);
            int pStart = (pageNo - 1) * pageSize + 1;
            int pEnd = Math.min((pageNo * pageSize), totalCount);

            List<OrderVO> orders = null;
            List<OrderListVO> orderList = null;
            if (!isMenuName) {
                orders = dao.orderSelect(start.equals("") ? "2000-01-01" : start,
                        end.equals("") ? "2099-12-31" : end, storeNo, pStart, pEnd);
                for (OrderVO order : orders) {
                    orderList = dao.orderListSelect(order.getNo());
                    order.setMenuList(orderList);
                }
            } else {
                orders = dao.menuSelect(start.equals("") ? "2000-01-01" : start,
                        end.equals("") ? "2099-12-31" : end, menuName, storeNo, pStart, pEnd);
            }

            request.setAttribute("list", orders);
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageStart", pageStart);
            request.setAttribute("pageEnd", pageEnd);
            request.setAttribute("pageCurrent", pageNo);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/component/admin/saleContent.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
