package dao;

import vo.MenuVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    public List<MenuVO> selectAll() {
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MenuVO> list = null;
        try {
            pstmt = conn.prepareStatement("select * from menu where valid=1");
            rs = pstmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                MenuVO menu = new MenuVO();
                menu.setNo(rs.getInt("no"));
                menu.setCategory(rs.getString("category"));
                menu.setName(rs.getString("name"));
                menu.setImage(rs.getString("image"));
                menu.setPrice(rs.getInt("price"));
                list.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(conn);
            ConnectionPool.close(pstmt);
            ConnectionPool.close(rs);
        }
        return list;
    }
}
