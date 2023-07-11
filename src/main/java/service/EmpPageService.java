package service;

import dao.EmpDAO;
import vo.EmpVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EmpPageService implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("EmpPageService");
        ActionForward forward = null;
        try {
            request.setCharacterEncoding("UTF-8");
            int storeNo = Integer.parseInt(request.getParameter("storeName"));
            System.out.println(storeNo);

            List<EmpVO> list = null;
            EmpDAO dao = new EmpDAO();
            list = dao.empSelectAll(storeNo);
            request.setAttribute("list", list);

            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/EmpList.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forward;
    }
}
