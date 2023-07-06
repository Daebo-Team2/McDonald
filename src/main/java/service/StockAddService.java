package service;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockDAO;
import vo.StockVO;

public class StockAddService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			request.setCharacterEncoding("UTF-8");
			String foodname = request.getParameter("foodname");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int storeno = Integer.parseInt(request.getParameter("storeno"));
			System.out.println(foodname + ", " + quantity + ", " + storeno);
			
			StockDAO dao = new StockDAO();
			int su = dao.updateStock(foodname, quantity, storeno);
			System.out.println("su : " + su);
			
//			PrintWriter out = new PrintWriter(System.out);
//			
//			if ( !(su > 0) ) {
//				out.println("<script>alert('다시 시도해주세요.'); history.go(-1);</script>");
//			} 
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return "StockList.do"; 
	}

}
