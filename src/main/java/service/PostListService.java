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
			// int total = dao.selectCountPost(); System.out.println(total);
			
			// session에서 얻는 storeno
			int storeno =0;
			
			if(storeno==0) {
				 List<PostVO> superlist = dao.selectAllPost(); 
				 /*status확인은 jsp 에서 처리*/
				 request.setAttribute("list", superlist);
				 
				 forward = new ActionForward();
				 forward.setRedirect(false); // forward
				 forward.setPath("/WEB-INF/views/super/postpage.jsp");
					
			}else {	
				List<PostVO> adminlist = dao.selectListPost(storeno);/*얘는 가맹점에서 게시판 보여주기 위한 메서드*/
				request.setAttribute("list",adminlist);
				
				forward = new ActionForward();
				forward.setRedirect(false); // forward
				forward.setPath("/WEB-INF/views/admin/postpage.jsp");

			}			
				
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	
	

}
