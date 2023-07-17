package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MenuDAO;
import vo.MenuVO;

public class MenuServie implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		MenuDAO dao = new MenuDAO();
		List<MenuVO> menulist = dao.selectAllMenu();
		request.setAttribute("list", menulist);
		forward.setPath("/WEB-INF/component/super/menuContent.jsp");
		return forward;
	}

}
