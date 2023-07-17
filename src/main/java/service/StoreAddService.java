package service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodDAO;
import dao.StockDAO;
import dao.StoreDAO;
import vo.FoodVO;

public class StoreAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name").trim();
			String id = request.getParameter("id").trim();
			String pwd = request.getParameter("pwd").trim();
			String tel = request.getParameter("tel").trim();
			String owner = request.getParameter("owner");
			String address1 = request.getParameter("address1").trim();
			String address2 = request.getParameter("address2").trim();
			String address3 = request.getParameter("address3").trim();
			String address4 = request.getParameter("address4").trim();
			String address = null;

			if (address3.equals("") && address4.equals("")) {
				address = "(" + address1 + ")" + " " + address2;
			} else if (address3.equals("")) {
				address = "(" + address1 + ")" + " " + address2 + " " + address4;
			} else if (address4.equals("")){
				address = "(" + address1 + ")" + " " + address2 + " " + address3;
			} else {
				address = "(" + address1 + ")" + " " + address2 + " " + address3 + " " + address4;
			}
			
			StoreDAO dao = new StoreDAO();
			StockDAO stdao = new StockDAO();
			FoodDAO fdao = new FoodDAO();
			int storeno = dao.storeAdd(name, id, pwd, tel, owner, address);
			List<FoodVO> list = fdao.selectAll();
			System.out.println(storeno);
			System.out.println(list.size());
			for (int i = 1; i <= list.size(); i++ ) {
				stdao.setStock(i, storeno);
			}
			forward.setPath("/super/storeContent.do");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
