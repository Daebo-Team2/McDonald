package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.StockVO;
import vo.StoreName;
import vo.StoreNo;
import vo.StoreVO;

public class StoreDAO { //가맹점

	public List<StoreVO> selectAllStore() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoreVO> list = null;

		try {
			conn = ConnectionPool.getConnection();
			String sql ="SELECT * FROM STORE WHERE STATUS = 1 ORDER BY NAME";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			list = new ArrayList<>();
			while (rs.next() ) {
				StoreVO vo = new StoreVO();
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setTel(rs.getString("tel"));
				vo.setOwner(rs.getString("owner"));
				vo.setAddress(rs.getString("address"));
				vo.setOpeningday(rs.getTimestamp("openingday"));
				vo.setStatus(rs.getInt("status"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(rs);
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return list;
	}


	public int storeAdd( String name, String id, String pwd, String tel, String owner, String address ) { //가맹점추가
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int storeno = 0;

		try {
			conn = ConnectionPool.getConnection();

			String sql = "INSERT INTO STORE (NO, NAME, ID, PWD, TEL, OWNER, ADDRESS, OPENINGDAY ) VALUES (STORE_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP) ";
			String[] cols = new String[] {"no"};
			pstmt = conn.prepareStatement(sql, cols);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pwd);
			pstmt.setString(4, tel);
			pstmt.setString(5, owner);
			pstmt.setString(6, address);

			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();

			if( rs.next() ) {
				storeno = rs.getInt(1);
			}
			StoreName.isUpdate = true;
			StoreNo.isUpdate = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(rs);
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return storeno;
	}


	public int storeUpdate(String id, String tel, String owner, String address) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = ConnectionPool.getConnection();

			String sql = "UPDATE STORE SET TEL = ?, OWNER = ?, ADDRESSS = ? WHERE ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, owner);
			pstmt.setString(3, address);
			pstmt.setString(4, id);

			result = pstmt.executeUpdate();
			StoreName.isUpdate  = true;
			StoreNo.isUpdate = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return result;
	}

	public int storeDelete(int no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = ConnectionPool.getConnection();

			String sql = "UPDATE STORE SET STATUS = 0 WHERE NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			StoreName.isUpdate  = true;
			StoreNo.isUpdate = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return result;
	}
}
