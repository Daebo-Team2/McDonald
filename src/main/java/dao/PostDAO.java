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

public class PostDAO { // 쿼리만 실행하도록 하는 것이 제일 좋음 
	
	//(1) 게시글 전체 목록 보기 selectListPost(int storeno )
	
	public List<PostVO> selectListPost(int storeno){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<PostVO> adminlist = new ArrayList<>();
		
		try {
			
			conn = ConnectionPool.getConnection();
			
			String sql = "SELECT  * FROM POST WHERE STORENO=?  ";
			//게시물 정렬 하기 위에서 쿼리문에 orderby절 추가 하면 된다고 함 최근 게시물 상단에 출력 되도록 

			System.out.println();
			pstmt = conn.prepareStatement(sql); // 쿼리문 생성
			pstmt.setInt(1, storeno); 
			
			rs = pstmt.executeQuery(); // 쿼리문 실행 
			
			adminlist = new ArrayList<PostVO>();
			System.out.println(rs);
			while(rs.next()) {	//결과를 순회하며 한행(게시물 하나)의 내용을 vo에 저장 ( list 에 담는 것임 ) 
				
				PostVO vo = new PostVO();
				
				vo.setNo(rs.getInt("no"));
				vo.setStoreno(rs.getInt("storeno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setTime(rs.getDate("time"));
				vo.setStatus(rs.getInt("status"));
				vo.setReno(rs.getInt("reno"));
				
				adminlist.add(vo); // 결과 목록에 저장 

			}
			
		} catch (Exception e) {
			System.out.println("목록 보여주기 실패");
		} finally {	
			try { // 반환 부분 
				
				ConnectionPool.close(pstmt);
				ConnectionPool.close(rs);
				ConnectionPool.close(conn);
 
			} catch (Exception e2) {

			}	
		}
		
		System.out.println(adminlist);
		return adminlist;
		
	}
	
	//(2)  게시글 총 게시물 몇개인지 구하기 ..? -- 보류 
	public int selectCountPost(String storeno) { 	
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int selectCount = 0;
		
		try {
			conn = ConnectionPool.getConnection();
			String sql ="SELECT COUNT(*) CNT FROM POST WHERE STORENO=?"; // 총 갯수 세는 쿼리문 
			
			pstmt = conn.prepareStatement(sql); //쿼리문 생성
			pstmt.setString(1, storeno); 
			
			rs = pstmt.executeQuery();//쿼리문 실행 

			if(rs.next()) {	
				selectCount = rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			
		}finally {	
			try {
				ConnectionPool.close(pstmt);
				ConnectionPool.close(rs);
				ConnectionPool.close(conn);
				
			} catch (Exception e2) {
			}

		}		
		return selectCount;
	}
	
	
	//(3) 게시글 작성하기 : insertPost(PostVO vo)
	// ( 1개의 행이 업데이트 되었습니다 . . 이런식으로 결과가 뜨니까 성공한 행의 개수를 정수(int로 반환 )
	
	public int insertPost(PostVO vo) {	
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row = 0;
		
		try {
			conn = ConnectionPool.getConnection();
			
			StringBuffer sb = new StringBuffer(); 
			String[] cols = new String[]{"no"}; //쿼리문을 실행시키고 글 no을 가져 갈 수 있음 
			
			sb.append("INSERT INTO POST (NO, STORENO, TITLE, CONTENT, TIME, ");
			sb.append("STATUS, RENO) VALUES(post_seq.nextval, ?, ?, ?, SYSDATE, 0, 0) " ); 
			/*여기서 storename 은 가맹점 테이블에 값이 있어야 들어 갈 수 있음!! 주의 !!  */			
			
			pstmt = conn.prepareStatement(sb.toString(), cols); // 쿼리문 생성  
			
			/* System.out.println(sb); 쿼리문이 정상적으로 들어갔는지 확인 하는 코드 */
			
			//storename 도 같이 작성하도록 하긴 했는데 로그인 하면 어차피 가맹점이 정해져있는데 ..음 ..
		   // 사용자가 입력한 값을 대입 하는 코드 
			pstmt.setInt(1, vo.getStoreno());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {	
				vo.setNo(rs.getInt(1));
			}
			
			row = pstmt.executeUpdate(); // 쿼리문 실행 
			
						
		} catch (Exception e) {
			
		} finally {	
			try {
				ConnectionPool.close(pstmt);
				ConnectionPool.close(conn);
				
			} catch (Exception e2) {

			}
		}
		
		return row; // 성공적으로 추가한 행의 개수를 돌려줌 
	}
		
	//(4) 글 상세보기 : selectDetailPost(int no)
	
	public PostVO selectDetailPost(int no) {	/*글번호 no */
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		PostVO vo = new PostVO();
		
		String sql = "SELECT * FROM POST WHERE NO=? ";
				
		try {	
			conn = ConnectionPool.getConnection();
			
			pstmt = conn.prepareStatement(sql); // 쿼리문 생성 
			System.out.println(pstmt);
			pstmt.setInt(1, no); // 인파라미터?로  no 설정 
			rs = pstmt.executeQuery(); //쿼리 실행 
			
			//결과 처리 
			
			if(rs.next()) {	 // /rs에서 반환된 행을 next() 메서드로 확인한후 vo 에 저장 
				
				  vo.setNo(rs.getInt(1)); 
				  vo.setStoreno(rs.getInt(2));
				  vo.setTitle(rs.getString(3)); 
				  vo.setContent(rs.getString(4));
				  vo.setTime(rs.getDate(5)); 
				  vo.setStatus(rs.getInt(6));
				  vo.setReno(rs.getInt(7));

			}
					
		} catch (Exception e) {
			
		} finally {	
			try {
				ConnectionPool.close(pstmt);
				ConnectionPool.close(rs);
				ConnectionPool.close(conn);
			} catch (Exception e2) {

			}
		}		
		
		return vo; 
	}
	
	//(5) 가맹점 본사가 답글을 달았을때 status 값이 1로 변경  : updateStatus1(PostVO vo ) -- 보류 
	
	public int updateStatus1(PostVO vo ) {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int row =0;
		
		try {
			conn = ConnectionPool.getConnection();
			
			String sql = "update post set status = 1 where no =?";		
			//답글이 달린 게시글의 Status = 1 , 답글 번호 = 답글로 작성된 글 no 
				
			pstmt = conn.prepareStatement(sql); //쿼리문 생성
			pstmt.setInt(1,vo.getNo()); //  글번호 
			   
			row = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			
		}finally {	
			try {
				ConnectionPool.close(pstmt);
				ConnectionPool.close(rs);
				ConnectionPool.close(conn);
			} catch (Exception e2) {
			}
		}
		
		return row;
	}
	
	
	//(6) 가맹점 본사가 답글을 달았을때 reno값이 postno 로 변경 : updateReno(PostVO vo) -- 보류 
	public int updateReno(PostVO vo ) {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int row =0;
		
		try {
			conn = ConnectionPool.getConnection();
			
			String sql = "update post set reno = ? where no =?";		
			//답글이 달린 게시글의 Status = 2 , 답글 번호 = 답글로 작성된 글 no 이 ? 안에 들어가야함 ..
				
			pstmt = conn.prepareStatement(sql); //쿼리문 생성
			pstmt.setInt(1,vo.getReno()); //  글번호 
			pstmt.setInt(2,vo.getNo()); //  글번호 
			   
			row = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			
		}finally {	
			try {
				ConnectionPool.close(pstmt);
				ConnectionPool.close(rs);
				ConnectionPool.close(conn);
			} catch (Exception e2) {
			}
		}
		
		return row;
	}
	
	
	
	//*********본사*************************
	
	//(1)  본사가 처음에 답글 쓰는 건 위에 insertPost()메소드로 이용 
 	//(2)  답글 작성 후  status가 2 인 상태로 변경 
	
	public int updateStatus2(PostVO vo ) {	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int updaterow =0;
		
		try {
			conn = ConnectionPool.getConnection();
			
			String sql = "update post set status = 2 where no =?";		
			//답글이 달린 게시글의 Status = 2 로 변경 
			//그럴려면 no가 본사가 쓴 글인지를 확인해야 하는데 storeno가 1 인지를 알아야하는데 .
				
			pstmt = conn.prepareStatement(sql); //쿼리문 생성
			pstmt.setInt(1,vo.getNo()); //  글번호 
			   
			updaterow = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			
		}finally {	
			try {
				ConnectionPool.close(pstmt);
				ConnectionPool.close(rs);
				ConnectionPool.close(conn);
			} catch (Exception e2) {
			}
		}
		
		return updaterow;
	}
		
	
	// 전체 문의 게시글 출력 근데 storeno가 0인 게시글은 모달 안에서 보여야할텐데,,  
	public List<PostVO> selectAllPost() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<PostVO> superlist = new ArrayList<>();
		
		try {
			conn = ConnectionPool.getConnection();
			String sql = "select * from post order by no desc ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			superlist = new ArrayList<PostVO>();
			
			while(rs.next()) {
				
				PostVO vo = new PostVO();
				
				vo.setNo(rs.getInt("no"));
				vo.setStoreno(rs.getInt("storeno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setTime(rs.getDate("time"));
				vo.setStatus(rs.getInt("status"));
				vo.setReno(rs.getInt("reno"));
				
				superlist.add(vo); // 결과 목록에 저장 
					
			}
			
		} catch (Exception e) {
			
		}finally {	
			try {
				ConnectionPool.close(pstmt);
				ConnectionPool.close(rs);
				ConnectionPool.close(conn);
			} catch (Exception e2) {
				
			}
		}
		
		System.out.println(superlist);
		return superlist;
	}


	
}

