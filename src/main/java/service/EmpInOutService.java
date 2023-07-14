package service;

import dao.EmpDAO;
import vo.EmpVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class EmpInOutService implements Action {
    final int timeUnit = 60000;
//    final int timeUnit = 3600000;
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        //직원 no. storeName, time
        ActionForward forward = null;
        try {
            int empNo = Integer.parseInt(request.getParameter("no"));

            EmpDAO dao = new EmpDAO();
            EmpVO emp = dao.empSelect(empNo);
            Date onTime = new Date(System.currentTimeMillis());
            int row = dao.empInOutInsert(emp, onTime);

            if (emp.getInTime() == null) {
                emp.setInTime(onTime);
            } else {
                long workTime = (onTime.getTime() - emp.getInTime().getTime()) / timeUnit;

                emp.setwTime(emp.getwTime() + (int) workTime);
                emp.setInTime(null);
            }
            request.setAttribute("result", dao.empUpdate(emp));

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/admin/sidebar.do");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
