package service;

import dao.SaleDAO;
import vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SuperSalePageService implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = new ActionForward();
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String storeName = request.getParameter("storeName");
        String menuName = request.getParameter("menuName");
        int searchStoreNo = StoreNo.getStoreNo(storeName);
        if (start == null || end == null || menuName == null || storeName == null ||
                (searchStoreNo == -1 && !storeName.equals(""))) {
            forward.setPath("/WEB-INF/component/super/saleContent.jsp");
            return forward;
        }
        if (storeName.equals("")) {
            searchStoreNo = 0;
        }
        String reqPageNo = request.getParameter("pageNo");
        int pageNo = 1;
        if (reqPageNo != null && !reqPageNo.equals("")) {
            pageNo = Integer.parseInt(reqPageNo);
        }

        SaleDAO dao = new SaleDAO();
        int totalCount = 0;
        int totalPrice = 0;
        boolean isMenuName = false;
        if (menuName.equals("")) {
            totalCount = dao.orderCount(start.equals("") ? "2000-01-01" : start,
                    end.equals("") ? "2099-12-31" : end, searchStoreNo);
            totalPrice = dao.getOrderSelectTotalPrice(start.equals("") ? "2000-01-01" : start,
                    end.equals("") ? "2099-12-31" : end, searchStoreNo);
        } else {
            totalCount = dao.menuCount(start.equals("") ? "2000-01-01" : start,
                    end.equals("") ? "2099-12-31" : end, menuName, searchStoreNo);
            totalPrice = dao.getMenuSelectTotalPrice(start.equals("") ? "2000-01-01" : start,
                    end.equals("") ? "2099-12-31" : end, menuName, searchStoreNo);
            isMenuName = true;
        }

        int pageSize = 10;
        int blockPage = 5;
        int totalPage = (int) Math.ceil((double) totalCount / pageSize);
        int pageStart = ((pageNo - 1) / blockPage) * blockPage + 1;
        int pageEnd = Math.min(pageStart - 1 + blockPage, totalPage);
        int pStart = (pageNo - 1) * pageSize + 1;
        int pEnd = Math.min((pageNo * pageSize), totalCount);

        List<OrderVO> orders = null;
        List<OrderListVO> orderList = null;
        if (!isMenuName) {
            orders = dao.orderSelect(start.equals("") ? "2000-01-01" : start,
                    end.equals("") ? "2099-12-31" : end, searchStoreNo, pStart, pEnd);
            for (OrderVO order : orders) {
                orderList = dao.orderListSelect(order.getNo());
                order.setMenuList(orderList);
            }
        } else {
            orders = dao.menuSelect(start.equals("") ? "2000-01-01" : start,
                    end.equals("") ? "2099-12-31" : end, menuName, searchStoreNo, pStart, pEnd);
        }

        request.setAttribute("list", orders);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageStart", pageStart);
        request.setAttribute("pageEnd", pageEnd);
        request.setAttribute("pageCurrent", pageNo);

        forward.setRedirect(false);
        forward.setPath("/WEB-INF/component/super/saleContent.jsp");

        return forward;
    }
}
