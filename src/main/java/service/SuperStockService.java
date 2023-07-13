package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockOrderDAO;
import vo.StockOrderListVO;
import vo.StockOrderVO;

public class SuperStockService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) { //본사 : 발주요청확인
		ActionForward forward = new ActionForward();

		System.out.println("SuperStockService !!");


		//1. stockorder 조회 -> no, storeno
		//2. store 조회 -> stroename
		//3. stockorderlist 조회 -> foodno
		//4. food 조회 -> foodname
		List <StockOrderVO> stockorderlist = new ArrayList();
		StockOrderDAO dao = new StockOrderDAO();
		stockorderlist = dao.selectAllStockOrder();

		List<StockOrderListVO> stockorderlistlist = new ArrayList();
		for (StockOrderVO vo : stockorderlist) {
			
			int stockorderno = vo.getNo();
			vo.setList(dao.selectAllStockOrderList(stockorderno));
		}
		
		
		
		//페이징 처리
		//전체 페이지 수 계산
		int pageSize = 10; //한 페이지에 출력할 게시물 수 post_per_page
		int blockPage = 5; //한 화면에 출력할 페이지 번호의 개수 pages_per_block
		int totalCount = stockorderlist.size(); //전체 리스트 수
		int totalPage = (int)Math.ceil( (double)totalCount / pageSize); //전체 페이지 수
		
		//현재 페이지 확인
		int pageCurrent = 1; //기본값
		String pageno = request.getParameter("pageNo"); //jsp에서 받아오기
		if ( pageno != null && !pageno.equals("") ) {
			pageCurrent = Integer.parseInt(pageno); //요청받은 페이지로 수정
		}
		// pageNum 현재 페이지 번호
		// totalPage 게시물총갯수를 구해서 전체페이지
		// 화면에 5개씩 보여주고 싶다.
		// pageNum 7 -> 6,  17 -> 16
		int pageStart = ((pageCurrent -1) / blockPage) * blockPage + 1;
		int pageEnd = Math.min(pageStart + blockPage - 1, totalPage);
		//출력하고자 하는 마지막페이지수와 전체페이지수 중 작은 수를 마지막 페이지로 출력 하겠다
		System.out.println(pageno);
		System.out.println(pageStart);
		
		//목록에 출력할 계시물 범위 계산
		int start = (pageCurrent -1) * pageSize + 1; //첫 게시물 번호
		//int start = Math.max((pageNum - 1) * pageSize, 0); //첫 게시물 번호
		int end = Math.min((pageCurrent * pageSize), totalCount); //마지막 게시물 번호
		//int end = Math.min(pageNum * pageSize, totalCount); //마지막 게시물 번호
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		
		List<StockOrderVO> pagingStockOrder = stockorderlist.subList(start-1, end);
		//페이징 된 stockorderlist
		
		//request.setAttribute("list", stockorderlist);
		request.setAttribute("list", pagingStockOrder);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageStart", pageStart);
		request.setAttribute("pageEnd", pageEnd);
		request.setAttribute("pageCurrent", pageCurrent);


		//forward.setPath("super/stockpage.do");
		forward.setPath("/stock.jsp");

		return forward;
	}

}


/*
for (StockVO vo : stocklist) {
int foodno = vo.getFoodno();
int storeno = vo.getStoreno();
FoodDAO fdao = new FoodDAO();
StoreDAO sdao = new StoreDAO();

vo.setFoodName(fdao.selectFoodName(foodno));
vo.setStoreName(sdao.selectStoreName(storeno));
}
 */
