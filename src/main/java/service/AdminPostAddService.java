package service;

import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class AdminPostAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		 /*getParameter : storeno, title, content */
			
		 int storeno = 1; // 작성 가맹점 -- session	 		
		 String title = request.getParameter("title"); // 제목 
		 String	content = request.getParameter("content"); // 작성 내용 을 받아와야함  
		 
		 PostVO vo = new PostVO();
		 
		 //사용자가 입력한 값을 vo에 다시 저장 
		 vo.setStoreno(storeno);
		 vo.setTitle(title);
		 vo.setContent(content);
		 
		 try { 
				PostDAO dao = new PostDAO();	
				int row = dao.insertAdminPost(vo); /*게시글 작성*/
				System.out.println(row);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/admin/postpage.do");  /*session에 저장된 id가 admin 계정이면 page에 보여주기*/
				
									
		 } catch (Exception e) {
			e.printStackTrace();
		}
		 /*서비스 실행된후 page.do로 넘어가야 하니까 ! */
		 return forward;
	}

}
