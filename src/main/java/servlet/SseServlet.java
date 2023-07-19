package servlet;

import service.AlarmCenter;
import vo.UserVO;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value="/sse", asyncSupported = true)
public class SseServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/event-stream");
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("login");

        AsyncContext asyncContext = request.startAsync(request, response);
        asyncContext.setTimeout(0);

        AlarmCenter alarmCenter = AlarmCenter.getInstance();
        alarmCenter.addStore(user.getNo(), asyncContext);
        return;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}