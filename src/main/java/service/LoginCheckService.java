package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDAO;
import vo.StoreVO;
import vo.UserVO;

public class LoginCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		LoginDAO dao = new LoginDAO();
		StoreVO vo = dao.checkId(id);

		if ( vo == null || vo.getStatus() == 0) {
			request.setAttribute("msg", "로그인에 실패햐였습니다.");
			forward.setPath("/page/login");
		} else {
			if (vo.getPwd().equals(password)) {
				UserVO uservo = new UserVO();
				uservo.setName(vo.getName());
				uservo.setNo(vo.getNo());
				request.getSession().setAttribute("login", uservo); //("key", value)
				if ( vo.getNo() == 0 ) { //본사
					forward.setPath("/page/super");
				} else { //가맹점
					forward.setPath("/page/store");
				}
			} else {
				request.setAttribute("msg", "로그인에 실패햐였습니다.");
				forward.setPath("/page/login");
			}
		}
		return forward;
	}
}

