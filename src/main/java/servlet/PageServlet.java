package servlet;

import service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "PageServlet", value = "/page/*")
public class PageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String[] url_Command = requestURI.substring(contextPath.length() + 1).split("/");
        System.out.println(Arrays.toString(url_Command));

        Action action;
        ActionForward forward = new ActionForward();
        forward.setRedirect(true);

        response.sendRedirect(forward.getPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}