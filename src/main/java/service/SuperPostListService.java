package service;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PostDAO;
import vo.PostVO;
import vo.UserVO;

public class SuperPostListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		int status = 0;
		if (request.getParameter("status") != null) {
			status = Integer.parseInt(request.getParameter("status"));
		}
		ActionForward forward = null;
				
		try {	
			PostDAO dao = new PostDAO();		

			List<PostVO> superlist = dao.selectStatusPost(status); 
			
			//페이지 처리 
			//전체 페이지 수 계산 
			int pageSize = 10; // 한 페이지에 출력할 게시물 수 
			int blockPage = 5; ////한 화면에 출력할 페이지 번호의 개수 pages_per_block
			int totalCount = superlist.size(); //전체 리스트 수 
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
			
			List<PostVO> pagingPostSuperList = superlist.subList(start-1, end);
			
			request.setAttribute("list",pagingPostSuperList); /*page 안에서 list.status 값이 0일때와 1일때 나눠서 보여주기 */
			request.setAttribute("status", status);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageStart", pageStart);
			request.setAttribute("pageEnd", pageEnd);
			request.setAttribute("pageCurrent", pageCurrent);
			
			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/component/super/postContent.jsp");
										
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	
	

}
