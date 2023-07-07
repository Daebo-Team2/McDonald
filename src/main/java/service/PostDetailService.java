package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class PostDetailService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {

			PostDAO dao = new PostDAO();
			String title = request.getParameter("title"); //게시물 번호를 매개변수로 받음 
			
			PostVO vo = dao.selectDetailPost(title); //게시물 내용 가져옴

			
			//게시물 저장 
			request.setAttribute("vo", vo);
			/* System.out.println(vo);  확인 코드 */ 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/WEB-INF/views/post/postDetail.jsp";
	}

}
