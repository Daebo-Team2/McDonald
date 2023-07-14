package service;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class AdminPostListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		// session에서 얻는 storeno
		int storeno = 1;

		try {
			PostDAO dao = new PostDAO();

			/* status확인은 jsp 에서 처리 */
			List<PostVO> adminlist = dao.selectListPost(storeno);
			request.setAttribute("list", adminlist);

			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/views/admin/postpage.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
