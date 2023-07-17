package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StockDAO;
import dao.StockOrderDAO;
import vo.StockOrderListVO;

public class SuperStockUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();

		int no = Integer.parseInt(request.getParameter("no"));
		int storeno = Integer.parseInt(request.getParameter("storeno"));
		int orderquantity = 0;
		StockOrderDAO dao = new StockOrderDAO();
		 dao.confrimStockOder(no);
		 List<StockOrderListVO> list = dao.selectAllStockOrderList(no); //주문번호로 list 조회
		 for (StockOrderListVO stockOrderListVO : list) { //foodno의 주문한 수량 얻기
			 int foodno = stockOrderListVO.getFoodno();
			 orderquantity = stockOrderListVO.getQuantity();
			 
			 StockDAO sdao = new StockDAO();
			 int quantity = sdao.selectstockquantity(foodno, storeno); //foodno, storeno에 해당하는 기존 수량 찾기
			 int sumquantity = orderquantity + quantity; //기존 수량과 주문 수량 더하기
			 sdao.updateStock(foodno, sumquantity, storeno); //더한 수량으로 업데이트 해주기
		}
		forward.setPath("/super/stockContent.do");

		return forward; /* 본사발주조회실행 */
	}

}
