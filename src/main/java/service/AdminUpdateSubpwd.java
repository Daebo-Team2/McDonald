package service;

import dao.StoreDAO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminUpdateSubpwd implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("login");
        int storeno = user.getNo();
        String pwd = request.getParameter("newPwd");

        StoreDAO dao = new StoreDAO();
        int result = dao.updateSubPwd(storeno, pwd);
        if (result == 1) {
            response.setStatus(200);
            return null;
        }
        else {
            try {
                response.sendError(500);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
