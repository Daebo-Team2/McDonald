package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockOrderDAO;

public class StockConfrimService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("서비스까지는 들어왔다!");
		
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(no);
		/* int no = 2; //test용 */
		
		StockOrderDAO dao = new StockOrderDAO();
		int su = dao.confrimStockOder(no);
		
		System.out.println("su : " + su);
		
		
		
		return "StockOrderList.do"; /* 본사발주조회실행 */
	}

}
