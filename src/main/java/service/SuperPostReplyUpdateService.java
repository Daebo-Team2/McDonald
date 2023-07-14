package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class SuperPostReplyUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		 ActionForward forward = new ActionForward();
		 int no =  Integer.parseInt(request.getParameter("no")); //글번호  
		 
		 try {
			 PostDAO dao = new PostDAO();
			 PostVO vo = new PostVO();
			 
			 int updaterow = dao.updateReno(vo);		 
			 System.out.println(updaterow);/*행이 업데이트 되었는지 확인 */
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 forward.setRedirect(false);
		 forward.setPath("/WEB-INF/views/super/postpage.jsp"); /*업데이트 해주고 다시 문의 목록 페이지로 돌ㄹ아가야함 */

		 return forward;\
	}

}
