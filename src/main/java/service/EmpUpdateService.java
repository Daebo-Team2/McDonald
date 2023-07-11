package service;

import dao.EmpDAO;
import vo.EmpVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpUpdateService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("EmpUpdateService");
        ActionForward forward = null;
        try {
            request.setCharacterEncoding("UTF-8");
            int empNo = Integer.parseInt(request.getParameter("no"));
            EmpDAO dao = new EmpDAO();
            EmpVO emp = dao.empSelect(empNo);

            emp.setTel(request.getParameter("tel"));
            emp.setPay(Integer.parseInt(request.getParameter("pay")));
            System.out.println(emp);

            request.setAttribute("result", dao.empUpdate(emp));

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/EmpResult.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
