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
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String tel = request.getParameter("tel");
			String owner = request.getParameter("owner");
			String address = request.getParameter("address");

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
