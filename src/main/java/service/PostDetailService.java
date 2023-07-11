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

			PostDAO dao = new PostDAO();
			int no = Integer.parseInt(request.getParameter("no"));
	
			PostVO vo = dao.selectDetailPost(no); //게시물 내용 가져옴
	
			//게시물 저장 
			request.setAttribute("vo", vo);
			System.out.println(vo); /* 확인 코드 */
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/admin/postmodal.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*no=2*/
		
		return forward;
	}

}
