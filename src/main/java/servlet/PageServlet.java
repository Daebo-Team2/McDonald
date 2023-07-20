package servlet;

import service.*;
import javax.servlet.*;
import javax.servlet.http.*;
import vo.UserVO;
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
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("login");

        if (url.equals("/page/login")){
            if (user != null && user.getNo() == 0) {
                response.sendRedirect("/page/super");
                return;
            }
            if (user != null && user.getNo() != 0) {
                response.sendRedirect("/page/store");
                return;
            }
            forward.setPath("/WEB-INF/pages/loginPage.jsp");
            RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
            dis.forward(request, response);
            return;
        }


        if (user == null) {
            response.sendRedirect("/page/login");
            return;
        }

        if (url.equals("/page/admin")) {
            if (user.getNo() == 0) {
                response.sendRedirect("/page/super");
                return;
            }
            action = new EmpPageService();
            forward = action.execute(request, response);
            forward.setPath("/WEB-INF/pages/adminPage.jsp");
        }
        if (url.equals("/page/super")) {
            if (user.getNo() != 0) {
                response.sendRedirect("/page/store");
                return;
            }
            action = new StoreService();
            forward = action.execute(request, response);
            forward.setPath("/WEB-INF/pages/superPage.jsp");
        }

        if (url.equals("/page/store")) {
            if (user.getNo() == 0) {
                response.sendRedirect("/page/super");
                return;
            }
            forward.setPath("/WEB-INF/pages/storePage.jsp");
        }
        if (url.equals("/page/enterkiosk")) {
            if (user.getNo() == 0) {
                response.sendRedirect("/page/super");
                return;
            }
            forward.setPath("/WEB-INF/pages/enterkioskPage.jsp");
        }
        if (url.equals("/page/kiosk")) {
            if (user.getNo() == 0) {
                response.sendRedirect("/page/super");
                return;
            }
            KioskPageService kioskPageService = new KioskPageService();
            forward = kioskPageService.execute(request, response);
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