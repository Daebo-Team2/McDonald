package service;

import dao.StockDAO;
import dao.StockOrderDAO;
import vo.StockOrderListVO;
import vo.StockOrderVO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminStockOrderListService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("login");
        int storeNo = user.getNo();

        StockOrderDAO dao = new StockOrderDAO();
        List<StockOrderVO> stockOrders = dao.selectAdminStockOrder(storeNo);
        for (StockOrderVO stockOrder : stockOrders) {
            List<StockOrderListVO> stockOrderList = dao.selectAllStockOrderList(stockOrder.getNo());
            stockOrder.setList(stockOrderList);
        }

        int pageSize = 10; //한 페이지에 출력할 게시물 수 post_per_page
        int blockPage = 5; //한 화면에 출력할 페이지 번호의 개수 pages_per_block
        int totalCount = stockOrders.size(); //전체 리스트 수
        int totalPage = (int)Math.ceil( (double)totalCount / pageSize); //전체 페이지 수

        int pageCurrent = 1; //기본값
        String pageno = request.getParameter("pageNo"); //jsp에서 받아오기
        if ( pageno != null && !pageno.equals("") ) {
            pageCurrent = Integer.parseInt(pageno); //요청받은 페이지로 수정
        }

        int pageStart = ((pageCurrent -1) / blockPage) * blockPage + 1;
        int pageEnd = Math.min(pageStart + blockPage - 1, totalPage);

        int start = (pageCurrent -1) * pageSize + 1; //첫 게시물 번호
        int end = Math.min((pageCurrent * pageSize), totalCount); //마지막 게시물 번호

        List<StockOrderVO> pagingStockOrder = stockOrders.subList(start-1, end);

        request.setAttribute("list", pagingStockOrder);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageStart", pageStart);
        request.setAttribute("pageEnd", pageEnd);
        request.setAttribute("pageCurrent", pageCurrent);
        forward.setPath("/WEB-INF/component/admin/stockOrderContent.jsp");

        return forward;
    }
}
