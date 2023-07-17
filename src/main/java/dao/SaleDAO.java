package dao;

import vo.OrderListVO;
import vo.OrderVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDAO {
    public List<OrderVO> orderSelect(String start, String end, int storeNo, int rowS, int rowE) throws SQLException {
        String sql = "select * from (select rownum rn, x.* from (select * from orders " +
                "where (ordertime >= ?) and (ordertime <= ?) and (storeno >= ?) " +
                "and (storeno <= ?) order by no desc) x) where rn between ? and ?";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, start);
        pstmt.setString(2, end);
        pstmt.setInt(3, storeNo);
        pstmt.setInt(4, storeNo == 0 ? 9999 : storeNo);
        pstmt.setInt(5, rowS);
        pstmt.setInt(6, rowE);

        ResultSet rs = pstmt.executeQuery();
        List<OrderVO> list = new ArrayList<>();
        while (rs.next()) {
            OrderVO order = new OrderVO();
            order.setNo(rs.getInt("no"));
            order.setPrice(rs.getInt("price"));
            order.setOrderTime(rs.getDate("ordertime"));
            order.setStoreNo(rs.getInt("storeno"));
            list.add(order);
        }

        ConnectionPool.close(rs);
        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return list;
    }

    public List<OrderListVO> orderListSelect(int orderNo) throws SQLException {
        String sql = "select * from orderlist where orderno = ?";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, orderNo);
        ResultSet rs = pstmt.executeQuery();
        List<OrderListVO> list = new ArrayList<>();
        while (rs.next()) {
            OrderListVO menu = new OrderListVO();
            menu.setOrderNo(rs.getInt("orderno"));
            menu.setMenuNo(rs.getInt("menuno"));
            menu.setQuantity(rs.getInt("quantity"));
            list.add(menu);
        }

        ConnectionPool.close(rs);
        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return list;
    }

    public List<OrderVO> menuSelect(String start, String end, String menuName, int rowS, int rowE) throws SQLException {
        String sql = "select * from (select rownum rn, x.* from (select ol.*, m.name as menuname, m.price " +
                "as menuprice, o.ordertime as ordertime, o.storeno as storeno from orderlist ol, orders o, store s, " +
                "menu m where (ol.orderno = o.no) and (o.storeno = s.no) and (ol.menuno = m.no) and (m.name = ?) " +
                "and (o.ordertime between ? and ?) order by ol.orderno desc) x) " +
                "where rn between ? and ?";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, menuName);
        pstmt.setString(2, start);
        pstmt.setString(3, end);
        pstmt.setInt(4, rowS);
        pstmt.setInt(5, rowE);
        ResultSet rs = pstmt.executeQuery();
        List<OrderVO> list = new ArrayList<>();
        while (rs.next()) {
            OrderVO order = new OrderVO();
            order.setNo(rs.getInt("orderno"));
            order.setPrice(rs.getInt("menuprice") * rs.getInt("quantity"));
            order.setOrderTime(rs.getDate("ordertime"));
            order.setStoreNo(rs.getInt("storeno"));
            List<OrderListVO> menuList = new ArrayList<>();
            OrderListVO menu = new OrderListVO();
            menu.setMenuNo(rs.getInt("menuno"));
            menu.setOrderNo(rs.getInt("orderno"));
            menu.setQuantity(rs.getInt("quantity"));
            menuList.add(menu);
            order.setMenuList(menuList);
            list.add(order);
        }

        ConnectionPool.close(rs);
        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return list;
    }


    public int orderCount(String start, String end, int storeNo) throws SQLException {
        String sql = "select count(*) as cnt from orders where (ordertime >= ?) " +
                    "and (ordertime <= ?) and (storeno >= ?) and (storeno <= ?)";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, start);
        pstmt.setString(2, end);
        pstmt.setInt(3, storeNo);
        pstmt.setInt(4, storeNo == 0 ? 9999 : storeNo);
        ResultSet rs = pstmt.executeQuery();
        int cnt = 0;
        if (rs.next()) {
            cnt = rs.getInt("cnt");
        }

        ConnectionPool.close(rs);
        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return cnt;
    }

    public int menuCount(String start, String end, String menuName, int storeNo) throws SQLException {
        String sql = "select count(*) as cnt from (select ol.*, m.name, o.ordertime, o.storeno, s.name " +
                "from orderlist ol, orders o, store s, menu m where (ol.orderno = o.no) and (o.storeno = s.no) " +
                "and (ol.menuno = m.no) and (o.storeno between ? and ?) and (m.name = ?) " +
                "and (o.ordertime between ? and ?))";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, storeNo);
        pstmt.setInt(2, storeNo == 0 ? 9999 : storeNo);
        pstmt.setString(3, menuName);
        pstmt.setString(4, start);
        pstmt.setString(5, end);
        ResultSet rs = pstmt.executeQuery();
        int cnt = 0;
        if (rs.next()) {
            cnt = rs.getInt("cnt");
        }

        ConnectionPool.close(rs);
        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return cnt;
    }
}
