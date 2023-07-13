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
		ActionForward forward = null;
		
		  //int storeno = 0; // 슈퍼 본사
		 			
		  int storeno = 1; // 작성 가맹점
			 		
		 String title = request.getParameter("title"); // 제목 
		 String	content = request.getParameter("content"); // 작성 내용 
		 
		 int row= 0; /*insert 행*/
		 int updaterow =0; /*update 행 */
		 
		 PostVO vo = new PostVO();
		 
		 //사용자가 입력한 값을 vo에 저장 
		 vo.setStoreno(storeno);
		 vo.setTitle(title);
		 vo.setContent(content);
		 
		 try { /*본사*/
			//DAO를 통해 db에 작성한 게시글 저장 
			if(storeno ==0) {	
				PostDAO dao = new PostDAO();
				row = dao.insertPost(vo);/*답변 작성*/
				updaterow = dao.updateStatus2(vo); /*답변 상태2로 업데이트*/
				
				System.out.println(row);
				System.out.println(updaterow);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/super/postpage.do");
				
			}else {	/*가맹점*/
				
				PostDAO dao = new PostDAO();	
				row = dao.insertPost(vo); /*게시글 작성*/
				System.out.println(row);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/admin/postpage.do");  /*session에 저장된 id가 admin 계정이면 */
				
			}
						
		 } catch (Exception e) {
			e.printStackTrace();
		}
		 /*서비스 실행된후 page.do로 넘어가야 하니까 ! */
		 return forward;
	}

}
