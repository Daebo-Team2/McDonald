package servlet;

import service.ActionForward;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PageServlet", value = "/page/*")
public class PageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        ActionForward forward = new ActionForward();
        if (uri.equals("/page/admin")) {
            forward.setPath("/WEB-INF/pages/adminPage.jsp");
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