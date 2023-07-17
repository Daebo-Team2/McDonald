package service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDAO;

public class StoreUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			request.setCharacterEncoding("UTF-8");
			int no = Integer.parseInt(request.getParameter("no"));
			String id = request.getParameter("id").trim();
			String owner = request.getParameter("owner").trim();
			String tel = request.getParameter("tel").trim();
			String address = request.getParameter("address").trim();
			
			
			StoreDAO dao = new StoreDAO();
			int su = dao.storeUpdate(id, tel, owner, address);
			System.out.println("su : " + su);
			
			forward.setPath("/super/storepage.do");
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
