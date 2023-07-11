package service;

import dao.SaleDAO;
import vo.OrderListVO;
import vo.OrderVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SalePageService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

        ActionForward forward = null;
        try {
            request.setCharacterEncoding("UTF-8");
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            String menuNo = request.getParameter("menuNo");
            String storeNo = request.getParameter("storeNo");
            SaleDAO dao = new SaleDAO();
            List<OrderVO> orders = dao.orderSelect(start.equals("") ? "2000-01-01" : start,
                                                   end.equals("") ? "2099-12-31" : end,
                                                   storeNo == null ? 0 : Integer.parseInt(storeNo));

            for (OrderVO order : orders) {
                List<OrderListVO> orderList = dao.orderListSelect(order.getNo());
                if (!menuNo.equals("")) {
                    orderList.removeIf(menu -> menu.getMenuNo() != Integer.parseInt(menuNo));
                }

                int orderPrice = 0;
                for (OrderListVO menu : orderList) {
                    orderPrice += menu.getPrice();
                }
                order.setPrice(orderPrice);
                order.setMenuList(orderList);
            }
            orders.removeIf(orderVO -> orderVO.getMenuList().size() == 0);

            //페이징 처리
            String reqPageNo = request.getParameter("pageNo");
            int onePageCnt = 5;
            int totalCnt = orders.size();
            int totalPageCnt = (int) Math.ceil((double) totalCnt / onePageCnt);
            int pageNo = Math.min(reqPageNo.equals("") ? 1 : Integer.parseInt(reqPageNo), totalPageCnt);
            int startCnt = (pageNo - 1) * onePageCnt;
            int endCnt = Math.min(pageNo * onePageCnt, totalCnt);

            List<OrderVO> pagingOrders = orders.subList(startCnt, endCnt);
            request.setAttribute("list", pagingOrders);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/SalePage.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
