package servlet;

import service.ActionForward;

import javax.servlet.*;
import javax.servlet.http.*;

import service.Action;
import service.ActionForward;
import service.PostAddService;
import service.PostDetailService;
import service.PostListService;
import service.PostReplyService;
import service.SuperStockUpdate;
import service.SuperStockService;
import service.StoreAddService;
import service.StoreDeleteService;
import service.StoreService;
import service.StoreUpdateService;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SuperServlet", value = "/super/*")
public class SuperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        ActionForward forward = new ActionForward();
		Action action = null;
    
    	if(uri.equals("/super/postpage.do")){/*본사 문의페이지 첫 화면 */
			/* action = new PostUpdateStatusService(); */
    		action = new PostListService(); /*전체 조회 */ 
    		forward = action.execute(request, response);  	   	

    	}else if(uri.equals("/super/postreply.do")){ /*본사에 답글이 달리면 status 상태 2 업데이트 */
    		action = new PostReplyService();
    		forward = action.execute(request, response);
    		
    	}else if(uri.equals("/super/postmodal.do")){/*reno 가 null이 아닐때는 문의내역 조회 + 답변 작성  */
    	    /*문의내역 상세보기 */	
    		action = new PostDetailService();
    		forward = action.execute(request, response);
    	 
    	}
    	
    	if(forward != null) {	
    		if(forward.isRedirect()) {	
    			response.sendRedirect(forward.getPath());
    		}else {	
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request,response);
    		}
    	}

    
//<<<<<<< main

        if (uri.equals("/super/storeContent.do")) {
            forward.setPath("/WEB-INF/component/super/storeContent.jsp");
        }
        if (uri.equals("/super/saleContent.do")) {
            forward.setPath("/WEB-INF/component/super/saleContent.jsp");
        }
        if (uri.equals("/super/menuContent.do")) {
            forward.setPath("/WEB-INF/component/super/menuContent.jsp");
        }
        if (uri.equals("/super/stockContent.do")) {
            forward.setPath("/WEB-INF/component/super/stockContent.jsp");
        }
        if (uri.equals("/super/postContent.do")) {
            forward.setPath("/WEB-INF/component/super/postContent.jsp");
        }

        if (forward.getPath() == null) {
            response.sendError(404);
            return;
        }
        if (forward.isRedirect()) {
            response.sendRedirect(forward.getPath());
        }
        else {
            RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
            dis.forward(request, response);
        }
//=======
    	
    	if(uri.equals("/super/stockpage.do")) { //재고관리페이지 (발주조회)
    		action = new SuperStockService();
    		forward = action.execute(request, response);
    	
    	}else if(uri.equals("/super/stockupdate.do")) { //발주 주문 확인
    		action = new SuperStockUpdate();
    		forward = action.execute(request, response);
    	
    	}else if(uri.equals("/super/storepage.do")) { //가맹점조회
    		action = new StoreService();
    		forward = action.execute(request, response);
    	
    	}else if(uri.equals("/super/storeadd.do")) { //가맹점등록
    		action = new StoreAddService();
    		forward = action.execute(request, response);
    	
    	}else if(uri.equals("/super/storeupdate.do")) { //가맹점 정보 수정
    		action = new StoreUpdateService();
    		forward = action.execute(request, response);
    	
    	}else if(uri.equals("/super/storedelete.do")) { //가맹점 삭제
    		action = new StoreDeleteService();
    		forward = action.execute(request, response);
    	
    	}
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    	
    	
//>>>>>>> main
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doGet(request, response);
    }
}