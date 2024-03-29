package servlet;

import service.*;
import vo.UserVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/admin/*")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestURI.substring(contextPath.length());
        ActionForward forward = new ActionForward();
        Action action = null;

        HttpSession session = request.getSession();
        UserVO vo = (UserVO) session.getAttribute("login");
        if (vo == null || vo.getNo() == 0) {
            response.sendRedirect("/page/login");
            return;
        }

        if (url.equals("/admin/saleContent.do")) {
            action = new AdminSalePageService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/empContent.do")) {
            action = new EmpPageService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/stockContent.do")) {
            action = new AdminStockService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/postContent.do")) {
            action = new AdminPostListService(); /*문의 내역 전체 조회 서비스 */
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/stockupdate.do")) { //재고수량변경
            action = new AdminStockUpdateService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/stockorder.do")) { //발주 주문
            action = new AdminStockOderService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/empadd.do")) {
            action = new EmpAddService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/empupdatemodal.do")) {
            action = new EmpModalService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/empupdate.do")) {
            action = new EmpUpdateService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/sidebar.do")) {
            action = new EmpPageService();
            forward = action.execute(request, response);
            forward.setPath("/WEB-INF/component/admin/adminSide.jsp");
        }
        if (url.equals("/admin/empinout.do")) {
            action = new EmpInOutService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/salepage.do")) {
            action = new AdminSalePageService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/kioskorder.do")) {
            action = new KioskOrderService();
            action.execute(request, response);
            response.setStatus(200);
            return;
        }
        if (url.equals("/admin/postadd.do")) { /*문의글 작성 */
            action = new AdminPostAddService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/postmodal.do")) { // 특정 글의 상세내역을 담은 모달을 반환해야함.
            action = new PostDetailService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/empdelete.do")) {
            action = new EmpDeleteService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/stockorderlist.do")) {
            action = new AdminStockOrderListService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/stockorderdelete.do")) {
            action = new AdminStockOrderDeleteService();
            forward = action.execute(request, response);
        }
        if (url.equals("/admin/checksubpwd.do")) {
            // 2차 비밀번호 확인
            action = new AdminCheckSubpwd();
            action.execute(request, response);
            return;
        }
        if (url.equals("/admin/updatesubpwd.do")) {
            // 2차 비밀번호 수정
            action = new AdminUpdateSubpwd();
            action.execute(request, response);
            return;
        }

        if (forward.getPath() == null) {
            response.sendError(404);
            return;
        }
        if (forward.isRedirect()) {
            response.sendRedirect(forward.getPath());
        } else {
            RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
            dis.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}