package service;

import dao.EmpDAO;
import vo.EmpVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class EmpInOutService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        //직원 no. storeName, time
        System.out.println("EmpInOutService");
        ActionForward forward = null;
        try {
            request.setCharacterEncoding("UTF-8");
            int empNo = Integer.parseInt(request.getParameter("no"));
            System.out.println(empNo);

            EmpDAO dao = new EmpDAO();
            EmpVO emp = dao.empSelect(empNo);
            Date onTime = new Date(System.currentTimeMillis());
            int row = dao.empInOutInsert(emp, onTime);
            System.out.println("empInOutAdd : " + row);

            if (emp.getInTime() == null) {
                emp.setInTime(onTime);
            } else {
                long workTime = (onTime.getTime() - emp.getInTime().getTime()) / 60000;
                System.out.println("workTime : " + workTime);

                emp.setwTime(emp.getwTime() + (int) workTime);
                emp.setInTime(null);
            }
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
