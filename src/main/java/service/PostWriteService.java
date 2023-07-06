package service;

import java.util.Date;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostDAO;
import vo.PostVO;

public class PostWriteService implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		//Action String 으로 했으니까 Path주소를 return에 바로 보내주면 됨 ! 
			
		
		 String storename = request.getParameter("storename"); // 작성 가맹점 
		 String title = request.getParameter("title"); // 제목 
		 String	content = request.getParameter("content"); // 작성 내용 
		 
		 int row= 0;
		 
		 PostVO vo = new PostVO();
		
		 vo.setStorename(storename); 
		 vo.setTitle(title);
		 vo.setContent(content);
		 
		 try {
			PostDAO dao = new PostDAO();	
			row = dao.insertPost(vo);
			
			System.out.println(row);
			
		 } catch (NamingException e) {
			e.printStackTrace();
		}
		 
		 
		return "/WEB-INF/views/post/postWrite.jsp";
	}

}
