package service;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class SuperPostListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		int storeno =0;
		int status = Integer.parseInt(request.getParameter("status"));
		ActionForward forward = null;
				
		try {	
			PostDAO dao = new PostDAO();		

			List<PostVO> superlist = dao.selectStatusPost(status); 
			request.setAttribute("list",superlist); /*page 안에서 list.status 값이 0일때와 1일때 나눠서 보여주기 */
					
			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/views/admin/postpage.jsp");
										
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	
	

}
