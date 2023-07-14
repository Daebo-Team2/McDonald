package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MenuDAO;

public class MenuDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		int menuno = Integer.parseInt(request.getParameter("no"));
		MenuDAO dao = new MenuDAO();
		dao.deleteMenu(menuno);
		forward.setPath("/super/menuContent.do");
		return forward;
	}

}
