package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.StockVO;

public class StockDAO {


	public List selectAllStoreStock(int storeno) { //가맹점 : 재고 조회
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StockVO> list = new ArrayList<>();
		
		try {
			int no = storeno;
			conn = ConnectionPool.getConnection();
			System.out.println("StockDAO selectAllStoreStock !!");
			
			String sql = "SELECT * FROM STOCK WHERE STORENO = ? ORDER BY FOODNO ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while( rs.next() ) {
				StockVO vo = new StockVO();
					vo.setFoodno(rs.getInt("foodno"));
					vo.setQuantity(rs.getInt("quantity"));
					vo.setStoreno(rs.getInt("storeno"));
					
					list.add(vo);
				}
			//System.out.println("selectAllStoreStock : " + list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(rs);
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return list;
	} //end selectAllStoreStock



	public int updateStock(int foodno, int quantity, int storeno) { //가맹점재고변경, 본사발주확인->가맹점재고추가
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			
			conn = ConnectionPool.getConnection();
			System.out.println("StockDAO updateStock !!");
			System.out.println(foodno + ", " + quantity + ", " + storeno);
			
			String sql = "UPDATE STOCK SET QUANTITY = ? WHERE FOODNO = ?  AND STORENO = ?  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, foodno);
			pstmt.setInt(3, storeno);
			
			result = pstmt.executeUpdate();
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return result;
	} //end updateStock

}
