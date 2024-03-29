package service;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import vo.PostVO;
import vo.UserVO;

public class AdminPostListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		// session에서 얻는 storeno
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("login");
		int storeno = user.getNo();

		try {
			PostDAO dao = new PostDAO();

			/* status확인은 jsp 에서 처리 */
			List<PostVO> adminlist = dao.selectListPost(storeno);
			
			//페이지 처리 
			//전체 페이지 수 계산 
			int pageSize = 10; // 한 페이지에 출력할 게시물 수 
			int blockPage = 5; ////한 화면에 출력할 페이지 번호의 개수 pages_per_block
			int totalCount = adminlist.size(); //전체 리스트 수 
			int totalPage = (int)Math.ceil((double)totalCount/pageSize); // 전체 페이지 수 
			
			//현재 페이지 확인 
			int pageCurrent = 1; // 기본값 
			String pageno = request.getParameter("pageNo");//jsp에서 받아오기
			
			if(pageno != null && !pageno.equals("")) {	
				pageCurrent = Integer.parseInt(pageno); //요청받은 페이지로 수정
			}
			
			//pageNum 현재 페이지 번호 
			// totalPage 게시물 총 갯수를 구해서 전체 페이지 
			// 화면에 5개씩 보여주고 싶다 
			// pageNum 7 ->6 , 17->16
			
			int pageStart = ((pageCurrent-1)/blockPage) * blockPage + 1;
			int pageEnd = Math.min(pageStart + blockPage -1, totalPage);
			// 출력 하고자 하는 마지막 페이지 수와 전체 페이지 수 중 작은 수를 마지막 페이지로 출력 하겠다.
			
			// 목록에 출력 해야할 게시물 범위 계산 
			int start = (pageCurrent -1)*pageSize +1;////첫 게시물 번호
			int end = Math.min((pageCurrent * pageSize), totalCount); //마지막 게시물 번호
			
			List<PostVO> pagingPostAdminList = adminlist.subList(start-1, end); // 페이징 된 adminlist
			
			request.setAttribute("list", pagingPostAdminList);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageStart", pageStart);
			request.setAttribute("pageEnd", pageEnd);
			request.setAttribute("pageCurrent", pageCurrent);

			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/component/admin/postContent.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
