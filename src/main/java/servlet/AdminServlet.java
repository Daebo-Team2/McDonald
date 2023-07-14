package servlet;

import service.ActionForward;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.Action;
import service.ActionForward;
import service.AdminStockOderService;
import service.AdminStockService;
import service.AdminStockUpdateService;

@WebServlet(name = "AdminServlet", value = "/admin/*")
public class AdminServlet extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url = requestURI.substring(contextPath.length());
        ActionForward forward = new ActionForward();
        Action action = null;

        if (url.equals("/admin/saleContent.do")) {
            forward.setPath("/WEB-INF/component/admin/saleContent.jsp");
        }
        if (url.equals("/admin/empContent.do")) {
            forward.setPath("/WEB-INF/component/admin/empContent.jsp");
        }
        if (url.equals("/admin/stockContent.do")) {
            action = new AdminStockService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/postContent.do")) {
            // 조회 서비스
            forward.setPath("/WEB-INF/component/admin/postContent.jsp");
        }
        if(url.equals("/admin/stockupdate.do")) { //재고수량변경
    		action = new AdminStockUpdateService();
    		forward = action.execute(request, response);
    	}
        if(url.equals("/admin/stockorder.do")) { //발주 주문
    		action = new AdminStockOderService();
    		forward = action.execute(request, response);
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doGet(request, response);
    }
}