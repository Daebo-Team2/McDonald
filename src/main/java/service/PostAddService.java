package service;

import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class PostAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		 int storeno =  Integer.parseInt(request.getParameter("storeno")); // 작성 가맹점 
		 String title = request.getParameter("title"); // 제목 
		 String	content = request.getParameter("content"); // 작성 내용 
		 
		 int row= 0;
		 
		 PostVO vo = new PostVO();
		
		 //사용자가 입력한 값을 vo에 저장 
		 vo.setStoreno(storeno);
		 vo.setTitle(title);
		 vo.setContent(content);
		 
		 try {
			//DAO를 통해 db에 작성한 게시글 저장 
			PostDAO dao = new PostDAO();	
			row = dao.insertPost(vo);
			
			System.out.println(row);
			
		 } catch (Exception e) {
			e.printStackTrace();
		}
		 
		 ActionForward forward = new ActionForward();
		 forward.setRedirect(false);
		 forward.setPath("/WEB-INF/views/post/postadd.jsp");
		 
		 /*storename=혜화&title=6번째글&content=내용66*/
		 return forward;
	}

}
