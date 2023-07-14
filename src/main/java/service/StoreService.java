package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDAO;
import vo.StoreVO;

public class StoreService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		StoreDAO dao = new StoreDAO();
		List<StoreVO> storelist = new ArrayList<>();
		storelist = dao.selectAllStore();
		request.setAttribute("list", storelist);
		forward.setPath("/WEB-INF/component/super/storeContent.jsp");
		return forward;
	}

}
