package servlet;

import service.ActionForward;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SuperServlet", value = "/super/*")
public class SuperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        ActionForward forward = new ActionForward();
        if (uri.equals("/super/storeContent.do")) {
            forward.setPath("/WEB-INF/component/super/storeContent.jsp");
        }
        if (uri.equals("/super/saleContent.do")) {
            forward.setPath("/WEB-INF/component/super/saleContent.jsp");
        }
        if (uri.equals("/super/menuContent.do")) {
            forward.setPath("/WEB-INF/component/super/menuContent.jsp");
        }
        if (uri.equals("/super/stockContent.do")) {
            forward.setPath("/WEB-INF/component/super/stockContent.jsp");
        }
        if (uri.equals("/super/postContent.do")) {
            forward.setPath("/WEB-INF/component/super/postContent.jsp");
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