package servlet;

import service.ActionForward;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/admin/*")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}