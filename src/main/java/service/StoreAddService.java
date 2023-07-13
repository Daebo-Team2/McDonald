package service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDAO;
import vo.StoreName;

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
			int su = dao.storeAdd(name, id, pwd, tel, owner, address);
			System.out.println("su : " + su);
			
			forward.setPath("/super/storepage.do");
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
