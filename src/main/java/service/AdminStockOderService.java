package service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockOrderDAO;

public class AdminStockOderService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {
			request.setCharacterEncoding("UTF-8");
			//System.out.println("AdminStockOderService !!");
			
			int storeno = Integer.parseInt(request.getParameter("storeno"));
			//int foodno = Integer.parseInt(request.getParameter("foodno"));
			//int quantity = Integer.parseInt(request.getParameter("quantity"));
			//System.out.println(foodno + ", " + storeno + ", " + quantity);
			String[] foodnos = request.getParameterValues("foodno");
			String[] quantitys = request.getParameterValues("quantity");
			System.out.println(foodnos);
			System.out.println(quantitys);
			

			StockOrderDAO dao = new StockOrderDAO();
			
			int stockorderno = dao.insertStockOrder(storeno);
			System.out.println("insertStockOrder : " + stockorderno);
			
//			int stockorderno = dao.selectAllStockOrder(storeno);
//			System.out.println("selectAllStockOrder : " + stockorderno);
			
			for (int i = 0; i < foodnos.length; i++ ) {
				int foodno = Integer.parseInt(foodnos[i]);
				int quantity = Integer.parseInt(quantitys[i]);
				int su = dao.insertStockOrderList(stockorderno, foodno, quantity);
				System.out.println("insertStockOrderList : " + su);
			}
			
			//forward.setPath("admin/stockpage.do");
			forward.setPath("admin/stockpage.do");
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return forward;
	}

}
