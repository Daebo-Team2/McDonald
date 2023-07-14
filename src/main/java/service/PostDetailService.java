package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import vo.PostVO;
import vo.UserVO;

public class PostDetailService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		try {
				PostDAO dao = new PostDAO();
				PostVO post =dao.selectDetailPost(no);
				
				// 게시물 저장
				request.setAttribute("post", post);
				if (post.getReno() != 0) {
					PostVO reply = dao.selectDetailPost(post.getReno());
					request.setAttribute("reply", reply);
				}
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/component/admin/postViewModal.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
