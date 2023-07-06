package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockDAO;
import vo.StockVO;

public class StoreStockSearchService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("여기까진 왔다");
		int no = Integer.parseInt(request.getParameter("storeno"));
		System.out.println(no);
		List <StockVO> stocklist = new ArrayList<>();
		StockDAO dao = new StockDAO();
		stocklist = dao.selectAllStoreStock(no);
		
		request.setAttribute("list", stocklist);
		
		return "stocktest1.jsp"; /* 가맹점재고조회.jsp */
	}

}
