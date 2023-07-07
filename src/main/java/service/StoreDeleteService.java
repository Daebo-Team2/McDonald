package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDAO;

public class StoreDeleteService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		int no = Integer.parseInt(request.getParameter("no"));
		StoreDAO dao = new StoreDAO();
		int su = dao.storeDelete(no);
		System.out.println("su : " + su);
		
		
		return "index.html"; /*"가맹점조회 페이지";*/
	}

}
