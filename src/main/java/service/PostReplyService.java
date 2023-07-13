package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class PostReplyService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		/*본사가-  status가 2 인 상태로 답글 상태가 Update 되는 service */
		 int no =  Integer.parseInt(request.getParameter("no")); //글번호  
		 
		 int row= 0;
		 
		 PostVO vo = new PostVO();
		
		 //사용자가 입력한 값을 vo에 저장 
		 vo.setNo(no);
	 
		 try {
			 //DAO를 통해 db에 status 값 변경함 
			 PostDAO dao = new PostDAO();		 
			 row = dao.updateStatus2(vo);
			 
			 System.out.println(row);/*행이 업데이트 되었는지 확인 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		 	 
		 ActionForward forward = new ActionForward();
		 forward.setRedirect(false);
		 forward.setPath("/WEB-INF/views/super/postreply.jsp");

		 return forward;
	}

}
