package service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDAO;

public class StoreDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		try {
			request.setCharacterEncoding("UTF-8");
			int no = Integer.parseInt(request.getParameter("no"));

			StoreDAO dao= new StoreDAO();
			int su = dao.storeDelete(no);

			forward.setPath("/super/storeContent.do");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
