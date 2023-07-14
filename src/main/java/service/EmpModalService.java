package service;

import dao.EmpDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpModalService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;
        try {
            int empNo = Integer.parseInt(request.getParameter("no"));

            EmpDAO dao = new EmpDAO();
            request.setAttribute("emp", dao.empSelect(empNo));

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/component/admin/empUpdateModal.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
