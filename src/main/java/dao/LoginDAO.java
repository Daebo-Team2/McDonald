package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.StoreVO;

public class LoginDAO {


	public StoreVO checkId(String id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoreVO vo = null;
		
		try {
			conn =ConnectionPool.getConnection();
			String sql = "SELECT * FROM STORE WHERE ID =? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
				vo = new StoreVO();
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setTel(rs.getString("tel"));
				vo.setStatus(rs.getInt("status"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(rs);
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return vo;
		
	}
}
