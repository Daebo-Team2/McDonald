package servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import service.Action;
import service.ActionForward;
import service.PostAddService;
import service.PostDetailService;
import service.PostListService;
import service.PostReplyService;

import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SuperServlet", value = "/super/*")
public class SuperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());	
    
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(url_Command.equals("/super/postpage.do")){/*본사 문의페이지 첫 화면 */
			/* action = new PostUpdateStatusService(); */
    		action = new PostListService(); /*전체 조회 */ 
    		forward = action.execute(request, response);  	   	

    	}else if(url_Command.equals("/super/postreply.do")){ /*본사에 답글이 달리면 status 상태 2 업데이트 */
    		action = new PostReplyService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/super/postmodal.do")){/*reno 가 null이 아닐때는 문의내역 조회 + 답변 작성  */
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

    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}