package service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StockOrderDAO;
import vo.UserVO;

public class AdminStockOderService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("login");
		int storeno = user.getNo();
		String[] foodnos = request.getParameterValues("foodno");
		String[] quantitys = request.getParameterValues("quantity");

		StockOrderDAO dao = new StockOrderDAO();

		int stockorderno = dao.insertStockOrder(storeno);
		for (int i = 0; i < foodnos.length; i++ ) {
			int foodno = Integer.parseInt(foodnos[i]);
			int quantity = Integer.parseInt(quantitys[i]);
			dao.insertStockOrderList(stockorderno, foodno, quantity);
		}
		forward.setPath("/admin/stockContent.do");
		return forward;
	}

}
