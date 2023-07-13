package service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockDAO;

public class AdminStockUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			request.setCharacterEncoding("UTF-8");
			System.out.println(" AdminStockUpdateService!! ");
			
			int foodno = Integer.parseInt(request.getParameter("foodno"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int storeno = Integer.parseInt(request.getParameter("storeno"));
			//System.out.println(foodno + ", " + quantity + ", " + storeno);
			
			StockDAO dao = new StockDAO();
			int su = dao.updateStock(foodno, quantity, storeno);
			System.out.println("su : " + su);
			
			forward.setPath("stockpage");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		return forward; 
	}

}
