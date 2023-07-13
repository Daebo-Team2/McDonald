package servlet;

import service.ActionForward;

import javax.servlet.*;
import javax.servlet.http.*;
import service.Action;
import service.ActionForward;
import service.LoginCheckService;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PageServlet", value = "/page/*")
public class PageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestURI.substring(contextPath.length());
        ActionForward forward = new ActionForward();
        Action action = null;

        if (url.equals("/page/admin")) {
            forward.setPath("/WEB-INF/pages/adminPage.jsp");
        }
        if (url.equals("/page/super")) {
            forward.setPath("/WEB-INF/pages/superPage.jsp");
        }
        if (url.equals("/page/login")){
            forward.setPath("/WEB-INF/pages/loginPage.jsp");
        }
        if (url.equals("/page/store")) {
            forward.setPath("/WEB-INF/pages/storePage.jsp");
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


//    	String requestURI = request.getRequestURI();
//    	String contextPath = request.getContextPath();
//    	String url_Command = requestURI.substring(contextPath.length());
//
//
//    	if(url_Command.equals("/page/login")) { //로그인페이지
//    		action = new LoginCheckService();
//    		forward = action.execute(request, response);
//
//    	}
//    	if(forward != null) {
//    		if(forward.isRedirect()) { //true
//    			response.sendRedirect(forward.getPath());
//    		}else { //false (모든 자원 ) 사용
//    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
//    			dis.forward(request, response);
//    		}
//    	}
//
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doGet(request, response);
    }
}