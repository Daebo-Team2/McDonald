package service;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class PostListService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			PostDAO dao = new PostDAO();		
			/*
			 * //게시물 총 갯수 
			 * int countpost = dao.selectCountPost();
			 */
			
			String storename = request.getParameter("storename");
			List<PostVO> list = dao.selectListPost(storename);
			System.out.println(list);
			//list 저장 
			request.setAttribute("list",list);

		}
		
		catch (Exception e) {
			e.printStackTrace();
		}

		return "/WEB-INF/views/post/postList.jsp";
	}

}
