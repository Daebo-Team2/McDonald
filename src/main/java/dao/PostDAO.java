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
			//게시물 정렬 하기 위에서 쿼리문에 orderby절 추가 하면 된다고 함 최근 게시물 상단에 출력 되도록 
			// 일단 where 절에 storename 으로 하는게 맞을지 ..
			
			pstmt = conn.prepareStatement(sql); // 쿼리문 생성 
			pstmt.setString(1, storename); 
			
			rs = pstmt.executeQuery(); // 쿼리문 실행 
			list = new ArrayList<PostVO>();
			
			
			while(rs.next()) {	//결과를 순회하며 한행(게시물 하나)의 내용을 vo에 저장 ( list 에 담는 것임 ) 
				
				PostVO vo = new PostVO();
				
				vo.setNo(rs.getInt("no"));
				vo.setStorename(rs.getString("storename"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setTime(rs.getDate("time"));
				vo.setStatus(rs.getInt("status"));
				vo.setReno(rs.getInt("reno"));
				
				list.add(vo); // 결과 목록에 ㅈ장 
			}
			
		} catch (Exception e) {
			System.out.println("목록 보여주기 실패");
		} finally {	
			try { // 반환 부분 
				pstmt.close();
				rs.close();
				conn.close(); 
			} catch (Exception e2) {

			}	
		}
		return list;
		
	}
	
	// 게시글 총 게시물 몇개인지 구하기 ..? 할지 말지 
	public int selectCountPost() { 	
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int selectCount = 0;
		
		try {
			conn = ds.getConnection();
			String sql ="SELECT COUNT(*) CNT FROM POST"; // 총 갯수 세는 쿼리문 
			pstmt = conn.prepareStatement(sql); //쿼리문 생성
			rs = pstmt.executeQuery();//쿼리문 실행 
			
			if(rs.next()) {	
				selectCount = rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			
		}finally {	
			try {
				pstmt.close();
				rs.close();
				conn.close(); // connection pool 에 반환하기 
				
			} catch (Exception e2) {
			}

		}		
		return selectCount;
	}
	
	
	//게시글 작성하기 ( 1개의 행이 업데이트 되었습니다 . . 이런식으로 결과가 뜨니까 성공한 행의 개수를 정수(int로 반환 )
	
	public int insertPost(PostVO vo) {	
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			StringBuffer sb = new StringBuffer(); // 쿼리문이 넘 길어서 sb 에 담음 
			
			sb.append("INSERT INTO POST (NO, STORENAME, TITLE, CONTENT, TIME, ");
			sb.append("STATUS, RENO) VALUES(post_seq.nextval, ?, ?, ?, SYSDATE, 0, 0) " ); 
			/*여기서 storename 은 가맹점 테이블에 값이 있어야 들어 갈 수 있음!! 주의 !!  */			
			/*postno가  sequence! */
			
			pstmt = conn.prepareStatement(sb.toString()); // 쿼리문 생성  
			
			/* System.out.println(sb); 쿼리문이 정상적으로 들어갔는지 확인 하는 코드 */
			
			//storename 도 같이 작성하도록 하긴 했는데 로그인 하면 어차피 가맹점이 정해져있는데 ..음 ..
			pstmt.setString(1, vo.getStorename()); // 사용자가 입력한 값을 대입 하는 코드 
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			
			row = pstmt.executeUpdate(); // 쿼리문 실행 
			
						
		} catch (Exception e) {
			
		} finally {	
			try {
				pstmt.close();
				conn.close(); 
			} catch (Exception e2) {

			}
		}
		
		return row; // 성공적으로 추가한 행의 개수를 돌려줌 
	}
	
	
	//글상세보기 --> 전체 보기에서 list 볼때 storename 으로 전달했었음 이번엔 no으로 해봄 
	public PostVO selectDetailPost(String title) {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		PostVO vo = new PostVO();
		
		String sql = "SELECT * FROM POST WHERE TITLE=?";
		
		
		try {
			
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql); // 쿼리문 생성 
			System.out.println(pstmt);
			pstmt.setString(1, title); // 인파라미터?로 title 설정 
			rs = pstmt.executeQuery(); //쿼리 실행 
			
			//결과 처리 
			
			if(rs.next()) {	 // /rs에서 반환된 행을 next() 메서드로 확인한후 vo 에 저장 
				
				  vo.setNo(rs.getInt(1)); 
				  vo.setStorename(rs.getString(2));
				  vo.setTitle(rs.getString(3)); 
				  vo.setContent(rs.getString(4));
				  vo.setTime(rs.getDate(5)); 
				  vo.setStatus(rs.getInt(6));
				  vo.setReno(rs.getInt(7));

			}
					
		} catch (Exception e) {
			
		} finally {	
			try {
				pstmt.close();
				rs.close();
				conn.close(); 
			} catch (Exception e2) {

			}
		}
		
		return vo; 
	}
}

