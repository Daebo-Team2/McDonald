package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDAO;
import vo.StoreVO;
import vo.UserVO;

import java.io.IOException;

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
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/script/loginFail.html");
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
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/script/loginFail.html");
			}
		}
		return forward;
	}
}

