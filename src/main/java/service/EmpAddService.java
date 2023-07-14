package service;

import dao.EmpDAO;
import vo.EmpVO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmpAddService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;
        HttpSession session = request.getSession();
        UserVO user = (UserVO)session.getAttribute("login");
        int storeNo = user.getNo();

        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        int pay = Integer.parseInt(request.getParameter("pay"));
        try {
            EmpDAO dao = new EmpDAO();
            request.setAttribute("result", dao.empInsert(new EmpVO(name, storeNo, tel, pay)));
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/admin/empContent.do");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
