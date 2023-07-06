package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.DirStateFactory.Result;
import javax.sql.DataSource;

import vo.PostVO;

public class PostDAO {
	DataSource ds = null;
	
	public PostDAO() throws NamingException {// 연결 부분 
		Context context = new InitialContext(); 
		// 네이밍 서비스를 위한 시작점 ( 실제 이름과 객체를 연결해주는 Context) 
		ds = (DataSource)context.lookup("java:comp/env/jdbc:projectDB");
		//lookup () 이용하여 원하는 객체를 찾아오기 
		System.out.println("연결 성공 ");
	}
	
	
	//게시글 전체 목록 보기 
	
	public List<PostVO> selectListPost(String storename){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<PostVO> list = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = "SELECT  * FROM POST WHERE STORENAME=?";
			
			pstmt = conn.prepareStatement(sql); // 쿼리문 담기 
			pstmt.setString(1, storename);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<PostVO>();
			
			
			while(rs.next()) {	
				
				PostVO vo = new PostVO();
				
				vo.setNo(rs.getInt("no"));
				vo.setStorename(rs.getString("storename"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setTime(rs.getDate("time"));
				vo.setStatus(rs.getInt("status"));
				vo.setReno(rs.getInt("reno"));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println("목록 보여주기 실패");
		} finally {	
			try {
				pstmt.close();
				rs.close();
				conn.close(); // 반환 ?
			} catch (Exception e2) {

			}	
		}
		return list;
		
	}
	
}

