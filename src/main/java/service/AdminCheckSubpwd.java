package service;

import dao.StoreDAO;
import vo.StoreVO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminCheckSubpwd implements Action{
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("login");
        String subpwd = request.getParameter("subpwd");
        StoreDAO dao = new StoreDAO();
        StoreVO store = dao.selectByNo(user.getNo());
        if (store != null && store.getSubpwd().equals(subpwd)) {
            response.setStatus(200);
        }
        else {
            try {
                response.sendError(403);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
