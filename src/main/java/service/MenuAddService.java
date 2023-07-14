package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MenuDAO;

public class MenuAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		MenuDAO dao = new MenuDAO();
		
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		String[] foodnos = request.getParameterValues("foodno");
		
		int menuno = dao.addMenu(category, name, price);
		for (int i = 0; i < foodnos.length; i++ ) {
		dao.addRecipe(menuno, Integer.parseInt(foodnos[i]) );
		}
		
		String image = "";
		dao.updateMenu(menuno, image);
		
		forward.setPath("/super/menuContent.do");
		return forward;
	}

}
