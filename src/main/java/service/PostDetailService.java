package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class PostDetailService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		int storeno = 1;/* 세션에서 받아와야하는 storeno 가맹점 */
		int no = Integer.parseInt(request.getParameter("no"));
		
		try {
				PostDAO dao = new PostDAO();
				PostVO vo =dao.selectDetailPost(no);
				
				// 게시물 저장
				request.setAttribute("vo", vo);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/admin/postmodal.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
