package service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StockDAO;
import vo.UserVO;

public class AdminStockUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("login");
		int storeno = user.getNo();
		int foodno = Integer.parseInt(request.getParameter("foodno"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		StockDAO dao = new StockDAO();
		dao.updateStock(foodno, quantity, storeno);
		forward.setPath("/admin/stockContent.do");
		return forward; 
	}

}
