package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockOrderDAO;

public class SuperStockUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		System.out.println("SuperStockUpdate !!");
		
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(no);
		/* int no = 2; //test용 */
		
		StockOrderDAO dao = new StockOrderDAO();
		int su = dao.confrimStockOder(no);
		System.out.println("su : " + su);
		
		forward.setPath("super/stockpage.do");
		forward.setRedirect(false);
		
		
		
		return forward; /* 본사발주조회실행 */
	}

}
