package servlet;

import service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "AdminServlet", value = "/admin/*")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String[] url_Command = requestURI.substring(contextPath.length() + 1).split("/");
        System.out.println(Arrays.toString(url_Command));

        Action action;
        ActionForward forward = new ActionForward();

        if (url_Command[1].equals("emppage.do")) {
            action = new EmpPageService();
            forward = action.execute(request, response);
        } else if (url_Command[1].equals("empadd.do")) {
            action = new EmpAddService();
            forward = action.execute(request, response);
        } else if (url_Command[1].equals("empmodal.do")) {
            action = new EmpModalService();
            forward = action.execute(request, response);
        } else if (url_Command[1].equals("empupdate.do")) {
            action = new EmpUpdateService();
            forward = action.execute(request, response);
        } else if (url_Command[1].equals("empinout.do")) {
            action = new EmpInOutService();
            forward = action.execute(request, response);
        } else {
            forward.setRedirect(true);
            forward.setPath("");
        }

        if (forward.isRedirect()) {
            response.sendRedirect(forward.getPath());
        } else {
            RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
            dis.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}