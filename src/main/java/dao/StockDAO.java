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


	public List<StockVO> selectAllStoreStock(int storeno) { //가맹점 : 재고 조회
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StockVO> list = new ArrayList<>();
		
		try {
			conn = ConnectionPool.getConnection();

			String sql = "SELECT * FROM STOCK WHERE STORENO = ? ORDER BY FOODNO ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storeno);
			rs = pstmt.executeQuery();

			while( rs.next() ) {
				StockVO vo = new StockVO();
					vo.setFoodno(rs.getInt("foodno"));
					vo.setQuantity(rs.getInt("quantity"));
					vo.setStoreno(rs.getInt("storeno"));
					
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
	} //end selectAllStoreStock

	public int updateStock(int foodno, int quantity, int storeno) { //가맹점재고변경, 본사발주확인->가맹점재고추가
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			
			conn = ConnectionPool.getConnection();

			String sql = "UPDATE STOCK SET QUANTITY = ? WHERE FOODNO = ?  AND STORENO = ?  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, foodno);
			pstmt.setInt(3, storeno);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return result;
	} //end updateStock
	
	public int selectstockquantity(int foodno, int storeno){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int quantity = 0;
		
		try {
			conn = ConnectionPool.getConnection();
			String sql = "SELECT QUANTITY FROM STOCK WHERE STORENO = ? AND FOODNO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, storeno);
			pstmt.setInt(2, foodno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				quantity = rs.getInt("quantity");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return quantity;
	}

	public int setStock(int foodno, int storeno) { //가맹점생성시 재고 기본 세팅
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = ConnectionPool.getConnection();
			String sql = "insert into stock (foodno, quantity, storeno) values ( ?, 0, ? ) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, foodno);
			pstmt.setInt(2, storeno);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return result;
	}
	
}
