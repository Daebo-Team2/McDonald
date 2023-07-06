package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.StockOrderVO;
import vo.StockVO;

public class StockOrderDAO { //발주
	
	//연결 부분
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc:projectDB");
		return ds.getConnection();
	}
	
	
	public List selectAllStockOrder( ) { //본사발주조회
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StockOrderVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			System.out.println("dao까지 옴 db 연결 완!");
			String sql = "SELECT * FROM STOCKORDER ORDER BY STORENO";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
				while ( rs.next() ) {
					StockOrderVO vo = new StockOrderVO();
					vo.setNo(rs.getInt("no"));
					vo.setStoreno(rs.getInt("storeno"));
					vo.setFoodname(rs.getString("foodname"));
					vo.setQuantity(rs.getInt("quantity"));
					vo.setTime(rs.getTimestamp("time"));
					vo.setStatus(rs.getInt("status"));
					
					list.add(vo);
				}
				
				System.out.println(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//pstmt.close();
			//conn.close();
		}
		return list;
	} //end selectAllStockOrder
	
	

	


}
