package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MenuDAO;
import vo.MenuVO;
import vo.RecipeVO;

public class MenuDetailService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		int no = Integer.parseInt(request.getParameter("no"));
		MenuDAO dao = new MenuDAO();
		
		MenuVO menuvo = dao.detailMenu(no);
		List<RecipeVO> recipevo = dao.detailRecipe(no);
		
		
		request.setAttribute("no", menuvo.getNo());
		request.setAttribute("category", menuvo.getCategory());
		request.setAttribute("name", menuvo.getName());
		request.setAttribute("image", menuvo.getImage());
		request.setAttribute("price", menuvo.getPrice());
		request.setAttribute("recipelist", recipevo);
		
		System.out.println("no : " + menuvo.getNo());
		System.out.println("category : " + menuvo.getCategory());
		System.out.println("name : " +  menuvo.getName());
		System.out.println("image : " + menuvo.getImage());
		System.out.println("price : " + menuvo.getPrice());
		System.out.println(recipevo);                
		
		
		forward.setPath("/WEB-INF/component/super/menuDetailModal.jsp");
		return forward;
	}

}
