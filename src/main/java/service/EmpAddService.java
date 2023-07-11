package service;

import dao.EmpDAO;
import vo.EmpVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpAddService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("EmpAddService");
        ActionForward forward = null;
        try {
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            int storeNo = Integer.parseInt(request.getParameter("storeNo"));
            String tel = request.getParameter("tel");
            int pay = Integer.parseInt(request.getParameter("pay"));

            System.out.println(storeNo + ", " + name + ", " + tel + ", " + pay);

            EmpDAO dao = new EmpDAO();
            request.setAttribute("result", dao.empInsert(new EmpVO(name, storeNo, tel, pay)));

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/EmpResult.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
