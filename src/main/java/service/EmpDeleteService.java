package service;

import dao.EmpDAO;
import vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpDeleteService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;
        int empNo = Integer.parseInt(request.getParameter("no"));

        try {
            EmpDAO dao = new EmpDAO();
            request.setAttribute("result", dao.empDelete(empNo));

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/admin/empContent.do");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
