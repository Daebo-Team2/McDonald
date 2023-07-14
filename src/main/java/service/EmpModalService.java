package service;

import dao.EmpDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpModalService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("EmpModalService");
        ActionForward forward = null;
        try {
            request.setCharacterEncoding("UTF-8");
            int empNo = Integer.parseInt(request.getParameter("no"));
            System.out.println(empNo);

            EmpDAO dao = new EmpDAO();
            request.setAttribute("emp", dao.empSelect(empNo));

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/empSelect.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
