package servlet;

import service.Action;
import service.ActionForward;
import service.SalePageService;

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

        String user = url_Command[1];
        String cmd = url_Command[2];
        ActionForward forward = new ActionForward();

        if (user.equals("admin")) {
            if (cmd.equals("sale")) {
                forward.setRedirect(true);
                forward.setPath("/salepage.html");
            }
            else if (cmd.equals("emp")) {
                forward.setRedirect(true);
                forward.setPath("/emppage.html");
            }
        }

        if (forward.isRedirect()) {
            response.sendRedirect(forward.getPath());
        } else {
            RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
            dis.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        this.doGet(request, response);
    }
}