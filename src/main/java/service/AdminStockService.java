package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodDAO;
import dao.StockDAO;
import dao.StoreDAO;
import vo.StockVO;

public class AdminStockService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) { //재고 조회
		
		ActionForward forward = new ActionForward();
		
		System.out.println("AdminStockService !!");
		
		int no = Integer.parseInt(request.getParameter("storeno"));
		List <StockVO> stocklist = new ArrayList<>();
		StockDAO dao = new StockDAO();
		stocklist = dao.selectAllStoreStock(no);
		
		
		//페이징 처리
		//전체 페이지 수 계산
		int pageSize = 10; //한 화면에 출력할 게시물 수
		int blockPage = 5; //한 화면에 출력할 페이지 번호의 개수
		int totalCount = stocklist.size(); //전체 리스트 수
		int totalPage = (int)Math.ceil( (double)totalCount / pageSize ); //전체 페이지 수
		
		//현재 페이지 확인
		int pageCurrent = 1; //기본값
		String pageno = request.getParameter("pageNo");
		if ( pageno != null && !pageno.equals("") ) {
			pageCurrent = Integer.parseInt(pageno);
		} //받아온 pageno가 null이 아니고 빈칸이 아니면 parseInt 해준다
		
		int pageStart = ((pageCurrent - 1)/blockPage) * blockPage + 1; //페이징 시작 페이지 수 계산
		int pageEnd = Math.min(pageStart + blockPage -1, totalPage); //페이징 마지막 페이지 수 계산
		
		//목록에 출력할 게시물 범위 계산
		int start = (pageCurrent - 1) * pageSize + 1; //첫 게시물 번호
		int end = Math.min((pageCurrent * pageSize), totalCount); //마지막 게시물 번호
		
		List<StockVO> pagingStock = stocklist.subList(start-1, end);
		//페이징 된 stocklist
		
		request.setAttribute("list", pagingStock);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageStart", pageStart);
		request.setAttribute("pageStart", pageStart);
		request.setAttribute("pageEnd", pageEnd);
		request.setAttribute("pageCurrent", pageCurrent);
		
		
		//forward.setPath("/admin/stockpage.do");
		forward.setPath("/stocktest1.jsp");
		
		
		
		
		return forward;
	}

}
