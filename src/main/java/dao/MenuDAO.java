package dao;

import vo.RecipeVO;
import vo.MenuVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    public List<MenuVO> selectAll() throws SQLException {
        String sql = "select * from menu";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        List<MenuVO> list = new ArrayList<>();
        try {
            while (rs.next()) {
                MenuVO menu = new MenuVO();
                menu.setNo(rs.getInt("no"));
                menu.setCategory(rs.getString("category"));
                menu.setName(rs.getString("name"));
                menu.setImage(rs.getString("image"));
                menu.setPrice(rs.getInt("price"));
                menu.setValid(rs.getInt("valid"));
                list.add(menu);
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
	
	public int addMenu(String category, String name, int price) { //메뉴 추가
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int menuno = 0;
		
		try {
			conn = ConnectionPool.getConnection();
			String[] cols = new String[]{"no"};
			String sql = "INSERT INTO MENU (NO, CATEGORY, NAME, PRICE, VALID) VALUES (MENU_SEQ.NEXTVAL, ?, ?, ?, 1 ) ";
			pstmt = conn.prepareStatement(sql, cols);
			pstmt.setString(1, category);
			pstmt.setString(2, name);
			pstmt.setInt(3, price);
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if ( rs.next() ) {
				menuno = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuno;
	}
	
	public int addRecipe(int menuno, int foodno) { //메뉴 추가 : 레시피 추가
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = ConnectionPool.getConnection();
			String sql = "insert into recipe (menuno, foodno) values (?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menuno);
			pstmt.setInt(2, foodno);
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return result;
	}
	
	public int updateMenu(int no, String image) { //메뉴 추가 : 이미지 경로 수정
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = ConnectionPool.getConnection();
			String sql = "UPDATE SET MENU IMAGE = ? WHERE NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, image);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(conn);
		}
		return result;
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
