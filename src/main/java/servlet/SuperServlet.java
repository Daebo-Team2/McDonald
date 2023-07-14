package servlet;

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
import service.MenuDeleteService;
import service.MenuServie;
import service.StoreAddService;
import service.StoreDeleteService;
import service.StoreService;
import service.StoreUpdateService;
import service.SuperStockService;
import service.SuperStockUpdate;
import vo.UserVO;

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

        HttpSession session = request.getSession();
        UserVO vo = (UserVO)session.getAttribute("login");
        if (vo == null || vo.getNo() != 0) {
            response.sendRedirect("/page/login");
            return;
        }

        if (url.equals("/super/storeContent.do")) { //가맹점 관리 페이지
            action = new StoreService();
            forward = action.execute(request, response);
        }
        if (url.equals("/super/saleContent.do")) { //매출 관리 페이지
            forward.setPath("/WEB-INF/component/super/saleContent.jsp");
        }
        if (url.equals("/super/menuContent.do")) { //메뉴 관리 페이지
        	action = new MenuServie();
        	forward = action.execute(request, response);
        }
        if (url.equals("/super/stockContent.do")) { //재고 관리 페이지
            action = new SuperStockService();
            forward = action.execute(request, response);
            forward.setPath("/WEB-INF/component/super/stockContent.jsp");
        }
        if (url.equals("/super/postContent.do")) { //문의 게시판
            forward.setPath("/WEB-INF/component/super/postContent.jsp");
        }

//    	if(url.equals("/super/stockpage.do")) { //재고관리페이지 (발주조회)
//    		action = new SuperStockService();
//    		forward = action.execute(request, response);
//    	}else
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
        
        if(url.equals("/super/menudelete.do")) { //메뉴 삭제
        	action = new MenuDeleteService();
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