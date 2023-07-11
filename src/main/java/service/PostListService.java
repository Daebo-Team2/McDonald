package service;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class PostListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		int total =0;
		
		try {
			
			PostDAO dao = new PostDAO();		
			/*게시물 총 개수 */
			
			/*
			 * int total = dao.selectCountPost(); System.out.println(total);
			 */

			int storeno = Integer.parseInt(request.getParameter("storeno")); 
			System.out.println(storeno);
			List<PostVO> list = dao.selectListPost(storeno);

			//전달할 객체를 list 에 저장 
			request.setAttribute("list",list);
			
			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/views/admin/postpage.jsp");
	
		
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//Postlist.jsp로 이동 
		//storename=혜화
		
		return forward;
	}
	
	

}
