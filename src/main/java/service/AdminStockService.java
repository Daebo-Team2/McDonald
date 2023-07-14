package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FoodDAO;
import dao.StockDAO;
import dao.StoreDAO;
import vo.StockVO;
import vo.UserVO;

public class AdminStockService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) { //재고 조회
		
		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("login");
		int no = user.getNo();
		StockDAO dao = new StockDAO();
		List<StockVO> stocklist = dao.selectAllStoreStock(no);
		request.setAttribute("list", stocklist);
		forward.setPath("/WEB-INF/component/admin/stockContent.jsp");

		return forward;
	}

}
