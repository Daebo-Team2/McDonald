package service;

import java.awt.PageAttributes.OrientationRequestedType;
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
		
		//페이징 처리
		//전체 페이지 수 계산
		int pageSize = 10; //한 화면에 출력할 게시물 수
		int blockPage = 5; //한 화면에 출력할 페이지 번호의 개수
		int totalCount = storelist.size(); //전체 리스트 수
		int totalPage = (int)Math.ceil((double)totalCount/pageSize); //전체 페이지 수
		
		//현재 페이지 확인
		int pageCurrent = 1; //기본값
		String pageno = request.getParameter("pageNo"); //pageNo 받아오기
		if ( pageno != null && !pageno.equals("") ) {
			pageCurrent = Integer.parseInt(pageno); //요청 받은 페이지로 수정
		}
		
		//5개씩 보여줄때 첫번째 페이지 수
		int pageStart = ((pageCurrent-1) / blockPage) * blockPage + 1;
		//5개씩 보여줄때 마지막 페이지 수
		int pageEnd = Math.min( pageStart+blockPage-1,  totalPage );
		
		//목록에 출력할 게시물 범위 계산
		int start = (pageCurrent - 1) * pageSize + 1; //첫 게시물 번호
		int end = Math.min((pageCurrent * pageSize), totalCount); //마지막 게시물 번호
		
		//페이징 된 storelist
		List<StoreVO> pagingStore = storelist.subList(start-1, end);
	
		request.setAttribute("list", pagingStore);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageStart", pageStart);
		request.setAttribute("pageEnd", pageEnd);
		request.setAttribute("pageCurrent", pageCurrent);
		forward.setPath("/WEB-INF/component/super/storeContent.jsp");
		return forward;
	}

}
