package service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockOrderDAO;

public class StockOrderService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setCharacterEncoding("UTF-8");
			System.out.println("StockOrderService까지는 들어왔다!");
			String foodname = request.getParameter("foodname");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int storeno = Integer.parseInt(request.getParameter("storeno"));
			System.out.println(foodname + ", " + storeno + ", " + quantity);


			StockOrderDAO dao = new StockOrderDAO();
			int result = dao.insertStockOrder(foodname, storeno, quantity);

			System.out.println("result : " + result);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return "index.html";
	}

}
