package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.StockAddService;
import service.StockConfrimService;
import service.StockOrderSearchService;
import service.StockOrderService;
import service.StoreAddService;
import service.StoreStockSearchService;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	Action action = null;
    	String forward = null;
    	
    	if(url_Command.equals("/StockList.do")) { //재고조회
    		System.out.println("요까지는 왔음");
    		String storeno = request.getParameter("storeno");
    		System.out.println(storeno);
    		action = new StoreStockSearchService();
    		forward = action.execute(request, response);
    		RequestDispatcher dis  = request.getRequestDispatcher(forward);
			dis.forward(request, response);
    		
    	}else if(url_Command.equals("/StockAdd.do")) { //재고수량변경
    		action = new StockAddService();
    		forward = action.execute(request, response);
    		RequestDispatcher dis  = request.getRequestDispatcher(forward);
			dis.forward(request, response);
			
    	}else if(url_Command.equals("/StockOrderList.do")) { //발주요청조회
    		action = new StockOrderSearchService();
    		forward = action.execute(request, response);
    		RequestDispatcher dis  = request.getRequestDispatcher(forward);
			dis.forward(request, response);
    	
    	}else if(url_Command.equals("/StockOrderConprim.do")) { //본사발주요청확인
    		
    		action = new StockConfrimService();
    		forward = action.execute(request, response);
    		RequestDispatcher dis  = request.getRequestDispatcher(forward);
			dis.forward(request, response);
    	
    	}else if(url_Command.equals("/StockOrderAdd.do")) { //가맹점발주요청
    		
    		action = new StockOrderService();
    		forward = action.execute(request, response);
    		RequestDispatcher dis  = request.getRequestDispatcher(forward);
			dis.forward(request, response);
    	
    	}else if(url_Command.equals("/StoreAdd.do")) { //가맹점추가
    		
    		action = new StoreAddService();
    		forward = action.execute(request, response);
    		RequestDispatcher dis  = request.getRequestDispatcher(forward);
			dis.forward(request, response);
    	
    	}
	
    	
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
