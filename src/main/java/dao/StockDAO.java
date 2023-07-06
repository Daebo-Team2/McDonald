package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.StockVO;

public class StockDAO {

	//연결 부분
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc:projectDB");
		return ds.getConnection();
	}

	public List selectAllStoreStock(int storeno) { //가맹점재고조회
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StockVO> list = new ArrayList<>();
		
		System.out.println("dao까지 왔음");

		try {
			int no = storeno;
			System.out.println("dao no: " + no);

			conn = getConnection();
			System.out.println("db connection success!");
			String sql = "SELECT * FROM STOCK WHERE STORENO = ? ORDER BY FOODNAME ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while( rs.next() ) {
				StockVO vo = new StockVO();
//					System.out.println("rs.next() 존재함");
//					System.out.println("foodname : " + rs.getString("foodname"));
//					System.out.println("quantity : " + rs.getInt("quantity"));
//					System.out.println("storeno : " + rs.getInt("storeno"));
					vo.setFoodname(rs.getString("foodname"));
					vo.setQuantity(rs.getInt("quantity"));
					vo.setStoreno(rs.getInt("storeno"));
					
					list.add(vo);
				}
				System.out.println(list);
//		else {
//				System.out.println("데이터가 없거나 오류 발생");
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//pstmt.close();
			//conn.close();
		}
		return list;
	} //end selectAllStoreStock



	public int updateStock(String foodname, int quantity, int storeno) { //가맹점재고변경, 본사발주확인->가맹점재고추가
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			System.out.println("dao까지 오고 디비 연결 성공!");
			System.out.println(foodname + ", " + quantity + ", " + storeno);
			String sql = "UPDATE STOCK SET QUANTITY = ? WHERE FOODNAME = ?  AND STORENO = ?  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, quantity);
			pstmt.setString(2, foodname);
			pstmt.setInt(3, storeno);
			
			result = pstmt.executeUpdate();
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//pstmt.close();
			//conn.close();
		}
		return result;
	} //end updateStock

}
