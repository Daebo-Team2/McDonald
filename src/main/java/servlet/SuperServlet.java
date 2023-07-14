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
import service.SuperStockUpdate;
import service.SuperStockService;
import service.StoreAddService;
import service.StoreDeleteService;
import service.StoreService;
import service.StoreUpdateService;
import vo.UserVO;
import service.SuperPostAddService;
import service.SuperPostListService;
import service.SuperPostReplyUpdateService;
import javax.servlet.annotation.*;

@WebServlet(name = "SuperServlet", value = "/super/*")
public class SuperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestURI.substring(contextPath.length());

        ActionForward forward = new ActionForward();
		Action action = null;
    
    	if(url.equals("/super/postpage.do")){/*본사 문의페이지 첫 화면 */
    		action = new SuperPostListService(); /*status에 따라 게시글글 출력 하는 서비스*/
    		forward = action.execute(request, response);  	   	

    	}else if(url.equals("/super/postreply.do")){ /*status2인 상태로 답글 작성해 주는 서비스  */
    		action = new SuperPostAddService();
    		forward = action.execute(request, response);
    		
    	}else if(url.equals("/super/postmodal.do")){ /*문의내역 상세 정보 */	

    		forward = action.execute(request, response);
    	 
    	}

        if (url.equals("/super/storeContent.do")) {
            action = new StoreService();
            forward = action.execute(request, response);
        }
        if (url.equals("/super/saleContent.do")) {
            forward.setPath("/WEB-INF/component/super/saleContent.jsp");
        }
        if (url.equals("/super/menuContent.do")) {
            forward.setPath("/WEB-INF/component/super/menuContent.jsp");
        }
        if (url.equals("/super/stockContent.do")) {
            action = new SuperStockService();
            forward = action.execute(request, response);
            forward.setPath("/WEB-INF/component/super/stockContent.jsp");
        }
        if (url.equals("/super/postContent.do")) {
            forward.setPath("/WEB-INF/component/super/postContent.jsp");
        }

        if(url.equals("/super/stockupdate.do")) { //발주 주문 확인
    		action = new SuperStockUpdate();
    		forward = action.execute(request, response);
    	}else if(url.equals("/super/storeadd.do")) { //가맹점등록
    		action = new StoreAddService();
    		forward = action.execute(request, response);
    	}else if(url.equals("/super/storeupdate.do")) { //가맹점 정보 수정
    		action = new StoreUpdateService();
    		forward = action.execute(request, response);
    	}else if(url.equals("/super/storedelete.do")) { //가맹점 삭제
    		action = new StoreDeleteService();
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