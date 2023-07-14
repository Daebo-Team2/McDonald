package service;

import dao.EmpDAO;
import vo.EmpVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpUpdateService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        ActionForward forward = null;
        try {
            int empNo = Integer.parseInt(request.getParameter("no"));
            EmpDAO dao = new EmpDAO();
            EmpVO emp = dao.empSelect(empNo);

            emp.setTel(request.getParameter("tel"));
            emp.setPay(Integer.parseInt(request.getParameter("pay")));

            request.setAttribute("result", dao.empUpdate(emp));

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/admin/empContent.do");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
