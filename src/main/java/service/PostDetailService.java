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

		try {

			int storeno = 1;/* 가맹점 */
			/* int storeno =0;슈퍼본사 */

			if (storeno == 0) {
				PostDAO dao = new PostDAO();

				int no = Integer.parseInt(request.getParameter("no"));
				/* if ( reno==0) 이면 답글 작성 else { } */

				PostVO vo = dao.selectDetailPost(no);

				request.setAttribute("vo", vo);
				System.out.println("슈퍼계정");
				System.out.println(vo);

				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/super/postmodal.jsp");

			} else {
				System.out.println("가맹점계정");
				PostDAO dao = new PostDAO();
				int no = Integer.parseInt(request.getParameter("no"));

				PostVO vo = dao.selectDetailPost(no);

				// 게시물 저장
				request.setAttribute("vo", vo);
				System.out.println(vo); /* 확인 코드 */

				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/admin/postmodal.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		/* no=2 */

		return forward;
	}

}
