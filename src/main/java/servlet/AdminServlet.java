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

@WebServlet(name = "AdminServlet", value = "/admin/*")
public class AdminServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	System.out.println(url_Command);
    
    	Action action = null;
    	ActionForward forward = null;
    	
    	if(url_Command.equals("/admin/postpage.do")){	/*가맹점 문의 페이지 첫화면 */
			/* action = new PostUpdateStatusService(); */
    		action = new PostListService(); /*문의 내역 전체 조회 서비스 */
    		forward = action.execute(request, response);  	   	

    	}else if(url_Command.equals("/admin/postadd.do")){ /*문의글 작성 */    	    		
    		action = new PostAddService();
    		forward = action.execute(request, response);
    	    	
    	}else if(url_Command.equals("/admin/postmodal.do")){ /*문의글 상세 조회 (답변글 전체 확인) */
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
    	this.doGet(request, response);
    }
}