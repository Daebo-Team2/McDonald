package service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.loginDAO;
import vo.StoreVO;
import vo.UserVO;

public class LoginCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("LoginCheckService !!");
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);

		try {
			request.setCharacterEncoding("UTF-8");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			System.out.println("id : " + id + ", pwd : " + password);
			loginDAO dao = new loginDAO();
			StoreVO vo = dao.checkId(id);
			System.out.println(vo);


			if ( vo == null || vo.getStatus() == 0) {
				forward.setPath("login.jsp");
				//System.out.println("ID X");
			} else {       
				if (vo.getPwd().equals(password)) {
					UserVO uservo = new UserVO();

					if ( vo.getNo() == 0 ) { //본사
						uservo.setName(vo.getName());
						uservo.setNo(vo.getNo());
						request.getSession().setAttribute("login", uservo); //("key", value)
						forward.setPath("/page/super.jsp");
						//System.out.println("super!!");
					} else { //가맹점
						uservo.setName(vo.getName());
						uservo.setNo(vo.getNo());
						request.getSession().setAttribute("login", uservo); //("key", value)
						forward.setPath("/stocktest.jsp");
						//forward.setPath("page/admin.jsp");
						System.out.println("store!!");
					}
				} else {
					forward.setPath("login.jsp");
					//System.out.println("pwd X");
				}
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return forward;
	}
}

