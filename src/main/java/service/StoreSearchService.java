package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDAO;
import vo.StoreVO;

public class StoreSearchService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		StoreDAO dao = new StoreDAO();
		List<StoreVO> storelist = new ArrayList<>();
		storelist = dao.selectAllStore();
		request.setAttribute("list", storelist);
		
		
		return "storetest.jsp"; /* 가맹점조회페이지 */
	}

}
