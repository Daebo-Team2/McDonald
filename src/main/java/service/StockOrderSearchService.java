package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockDAO;
import dao.StockOrderDAO;
import vo.StockOrderVO;
import vo.StockVO;

public class StockOrderSearchService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("서비스까지옴");
		List <StockOrderVO> stockorderlist = new ArrayList();
		StockOrderDAO dao = new StockOrderDAO();
		
		stockorderlist = dao.selectAllStockOrder();
		
		request.setAttribute("list", stockorderlist);
		
		return "stocktest2.jsp"; /* 본사발주조회.jsp */
	}

}
