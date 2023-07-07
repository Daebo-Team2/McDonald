package service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDAO;

public class StoreUpdateService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String tel = request.getParameter("tel");
			
			
			StoreDAO dao = new StoreDAO();
			int su = dao.storeUpdate(no, name, id, pwd, tel);
			System.out.println("su : " + su);
			
			
			
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "index.html"; /*"가맹점조회 페이지";*/
	}

}
