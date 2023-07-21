package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodDAO;
import dao.MenuDAO;
import vo.FoodVO;
import vo.MenuVO;

public class MenuServie implements Action {
	final int pageSize = 10;
	final int blockPage = 5;

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		int pageNo = 1;
		if (request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		ActionForward forward = new ActionForward();
		MenuDAO dao = new MenuDAO();
		int cnt = dao.getMenuCnt();
		int pageSize = 10;
		int blockPage = 5;
		int totalPage = (int)Math.ceil(cnt / (double)pageSize);
		int pageStart = ((pageNo - 1) / blockPage) * blockPage + 1;
		int pageEnd = Math.min(pageStart - 1 + blockPage, totalPage);
		int rowStart = (pageNo - 1) * pageSize + 1;
		int rowEnd = Math.min((pageNo * pageSize), cnt);
		List<MenuVO> menulist = dao.selectMenu(rowStart,rowEnd);

		FoodDAO fdao = new FoodDAO();
		List<FoodVO> foodlist = fdao.selectAll();
		
		request.setAttribute("list", menulist);
		request.setAttribute("foodlist", foodlist);
		request.setAttribute("pStart", pageStart);
		request.setAttribute("pEnd", pageEnd);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageNo", pageNo);
		forward.setPath("/WEB-INF/component/super/menuContent.jsp");
		return forward;
	}

}
