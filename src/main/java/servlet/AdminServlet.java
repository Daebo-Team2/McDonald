package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.Action;
import service.ActionForward;
import service.AdminPostAddService;
import service.AdminPostListService;
import service.SuperPostAddService;
import service.SuperPostListService;
import service.AdminStockOderService;
import service.AdminStockService;
import service.AdminStockUpdateService;
import service.PostDetailService;

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
			action = new AdminPostListService(); /*문의 내역 전체 조회 서비스 */
            forward = action.execute(request, response);
        }
        if(url.equals("/admin/stockupdate.do")) { //재고수량변경
    		action = new AdminStockUpdateService();
    		forward = action.execute(request, response);
    	}
        if(url.equals("/admin/stockorder.do")) { //발주 주문
    		action = new AdminStockOderService();
    		forward = action.execute(request, response);
    	}
//
//		if(url.equals("/admin/postContent.do")){	/*가맹점 문의 페이지 첫화면 */
//			action = new AdminPostListService(); /*문의 내역 전체 조회 서비스 */
//			forward = action.execute(request, response);  	
//    	
//		}else 
			if(url.equals("/admin/postadd.do")){ /*문의글 작성 */    	    		
    		action = new AdminPostAddService();
    		forward = action.execute(request, response);

    	}else if(url.equals("/admin/postmodal.do")){ // 특정 글의 상세내역을 담은 모달을 반환해야함.
    	    action = new PostDetailService();
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
    
