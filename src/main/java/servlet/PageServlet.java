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

        if (url.equals("/page/admin")) {
            action = new EmpPageService();
            forward = action.execute(request, response);
            forward.setPath("/WEB-INF/pages/adminPage.jsp");
        }
        if (url.equals("/page/super")) {
            HttpSession session = request.getSession();
            UserVO vo = (UserVO)session.getAttribute("login");
            if (vo == null || vo.getNo() != 0) {
                response.sendRedirect("/page/login");
                return;
            }
            action = new StoreService();
            forward = action.execute(request, response);
            forward.setPath("/WEB-INF/pages/superPage.jsp");
        }
        if (url.equals("/page/login")){
            forward.setPath("/WEB-INF/pages/loginPage.jsp");
        }
        if (url.equals("/page/store")) {
            forward.setPath("/WEB-INF/pages/storePage.jsp");
        }
        if (url.equals("/page/enterkiosk")) {
            forward.setPath("/WEB-INF/pages/enterkioskPage.jsp");
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