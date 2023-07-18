package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodDAO;
import dao.MenuDAO;
import vo.FoodVO;
import vo.MenuVO;

public class MenuServie implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		MenuDAO dao = new MenuDAO();
		List<MenuVO> menulist = dao.selectAllMenu();
		
		FoodDAO fdao = new FoodDAO();
		List<FoodVO> foodlist = fdao.selectAll();
		
		request.setAttribute("list", menulist);
		request.setAttribute("foodlist", foodlist);
		forward.setPath("/WEB-INF/component/super/menuContent.jsp");
		return forward;
	}

}
