package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockOrderDAO;

public class SuperStockUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		int no = Integer.parseInt(request.getParameter("no"));
		StockOrderDAO dao = new StockOrderDAO();
		 dao.confrimStockOder(no);
		forward.setPath("/super/stockContent.do");
		forward.setRedirect(false);

		return forward; /* 본사발주조회실행 */
	}

}
