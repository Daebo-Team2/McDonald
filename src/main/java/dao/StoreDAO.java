package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StoreDAO { //가맹점


	//연결 부분
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc:projectDB");
		return ds.getConnection();
	}


	public int storeAdd( String name, String id, String pwd, String tel ) { //가맹점추가
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = getConnection();

			System.out.println("여기는 storeAdd !!");
			String sql = "INSERT INTO STORE (NO, NAME, ID, PWD, TEL, STATUS ) VALUES (STORE_SEQ.NEXTVAL, ?, ?, ?, ?, 0) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pwd);
			pstmt.setString(4, tel);

			result = pstmt.executeUpdate();
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public int storeUpdate(int no, String name, String id, String pwd, String tel) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = getConnection();
			System.out.println("여기는 storeUpdate !!");
			String sql = "UPDATE STORE SET NAME = ?, ID = ?, PWD = ?, TEL = ? WHERE NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pwd);
			pstmt.setString(4, tel);
			pstmt.setInt(5, no);

			result = pstmt.executeUpdate();
			System.out.println(result);



		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int storeDelete(int no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = getConnection();
			System.out.println("여기는 storeDelete !!");
			String sql = "UPDATE STORE SET STATUS = 1 WHERE NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			result = pstmt.executeUpdate();
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


}
