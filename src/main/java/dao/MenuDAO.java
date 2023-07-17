package dao;

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
}
