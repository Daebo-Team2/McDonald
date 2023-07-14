package service;

import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class SuperPostAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		  int storeno = 0; // 슈퍼 본사
		 /*insert 했을때 받아야 하는 값들 */		
		 String title = request.getParameter("title"); // 제목 
		 String	content = request.getParameter("content"); // 작성 내용 을 받아와야함  
		 
			/*
			 * int no = Integer.parseInt(request.getParameter("no")); int reno =
			 * Integer.parseInt(request.getParameter("reno"));
			 */
		 
		 int row= 0; /*insert 행*/
		 
		 PostVO vo = new PostVO();
		 
		 //사용자가 입력한 값을 vo에 저장 
		 vo.setStoreno(storeno);
		 vo.setTitle(title);
		 vo.setContent(content);
		 
		 try { 
			//DAO를 통해 db에 작성한 게시글 저장 				
				PostDAO dao = new PostDAO();
				row = dao.insertReplyPost(vo);/*답변 작성*/
				
				System.out.println(row);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/super/postpage.do");
								
		 } catch (Exception e) {
			e.printStackTrace();
		}
		 /*서비스 실행된후 page.do로 넘어가야 하니까 ! */
		 return forward;
	}

}
