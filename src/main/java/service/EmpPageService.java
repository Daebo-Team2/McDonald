package service;

import dao.EmpDAO;
import vo.EmpVO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class EmpPageService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("login");
        int storeNo = user.getNo();

        try {
            EmpDAO dao = new EmpDAO();
            List<EmpVO> list = dao.empSelectAll(storeNo);
            request.setAttribute("list", list);
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/component/admin/empContent.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return forward;
    }
}
