package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MenuVO;
import vo.RecipeVO;

public class MenuDAO {
	
	
	public List<MenuVO> selectAllMenu() { //판매 중인 메뉴전체조회 (valid = 1)
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MenuVO> list = new ArrayList<>();
		
		try {
			conn = ConnectionPool.getConnection();
			String sql = "SELECT * FROM MENU WHERE VALID = 1 ORDER BY NO ";
			//String sql = "SELECT * FROM MENU WHERE ORDER BY NO ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while ( rs.next() ) {
				MenuVO vo = new MenuVO();
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));
				vo.setCategory(rs.getString("category"));
				vo.setImage(rs.getString("image"));
				vo.setPrice(rs.getInt("price"));
				vo.setValid(rs.getInt("valid"));
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return list;
	}
	
	public MenuVO detailMenu(int no) { //메뉴 상세조회 (메뉴 조회)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MenuVO vo = new MenuVO();
		
		try {
			conn = ConnectionPool.getConnection();
			String sql = "SELECT * FROM MENU WHERE NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
				vo.setNo(rs.getInt("no"));
				vo.setCategory(rs.getString("category"));
				vo.setName(rs.getString("name"));
				vo.setImage(rs.getString("image"));
				vo.setPrice(rs.getInt("price"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return vo;
	}
	
	public List<RecipeVO> detailRecipe(int no) { //메뉴 상세조회 (레시피 조회)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RecipeVO> list = new ArrayList<>();
		
		try {
			conn = ConnectionPool.getConnection();
			String sql = "SELECT * FROM RECIPE WHERE MENUNO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				RecipeVO vo = new RecipeVO();
				vo.setFoodno(rs.getInt("foodno"));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(rs);
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return list;
	}
	
	
	public int deleteMenu(int no) { //메뉴 삭제
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = ConnectionPool.getConnection();
			String sql = "UPDATE MENU SET VALID = 0 WHERE NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
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
