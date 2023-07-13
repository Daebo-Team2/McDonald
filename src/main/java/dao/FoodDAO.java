package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.FoodVO;
import vo.StockVO;

public class FoodDAO {

	public List<FoodVO> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FoodVO> list = null;

		try {
			conn = ConnectionPool.getConnection();
			String sql = "SELECT * FROM FOOD ORDER BY NO ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();


			list = new ArrayList<>();
			while( rs.next() ) {
				FoodVO vo = new FoodVO();
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));

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
	
	public String selectFoodName( int foodno ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String foodname = null;
		
		try {
			conn = ConnectionPool.getConnection();
			String sql = "SELECT * FROM FOOD WHERE NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, foodno);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
				foodname = rs.getString("name");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(rs);
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		
		return foodname;
	}
	
}
