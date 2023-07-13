package servlet;

//<<<<<<< main
import service.ActionForward;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
//=======
//>>>>>>> main
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Action;
import service.ActionForward;
import service.AdminStockOderService;
import service.AdminStockService;
import service.AdminStockUpdateService;
import vo.UserVO;

@WebServlet(name = "AdminServlet", value = "/admin/*")
public class AdminServlet extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//<<<<<<< main
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        ActionForward forward = new ActionForward();
        if (uri.equals("/admin/saleContent.do")) {
            forward.setPath("/WEB-INF/component/admin/saleContent.jsp");
        }
        if (uri.equals("/admin/empContent.do")) {
            forward.setPath("/WEB-INF/component/admin/empContent.jsp");
        }
        if (uri.equals("/admin/stockContent.do")) {
            forward.setPath("/WEB-INF/component/admin/stockContent.jsp");
        }
        if (uri.equals("/admin/postContent.do")) {
            forward.setPath("/WEB-INF/component/admin/postContent.jsp");
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
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	System.out.println(url_Command);
    	
    	Action action = null;
    	ActionForward forward = null;
    	HttpSession session = request.getSession();
    	if(session.getAttribute("login") != null) {
    		UserVO vo = (UserVO)session.getAttribute("login");
    		System.out.println(vo.getName());
    		System.out.println(vo.getNo());
    	}
    	
    	if(url_Command.equals("/admin/stockpage")) { //재고관리페이지 (전체조회)
    		action = new AdminStockService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/admin/stockupdate.do")) { //재고수량변경
    		action = new AdminStockUpdateService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/admin/stockorder.do")) { //발주 주문
    		action = new AdminStockOderService();
    		forward = action.execute(request, response);
    	
    	}
    	
    	
     	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false
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