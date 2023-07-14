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
    public List<OrderVO> orderSelect(String start, String end, int storeNo) throws SQLException {
        String sql = "select o.no, o.price, o.ordertime, o.storeno, " +
                "(select s.name from store s where s.no = o.storeno) as storename from orders o where " +
                "(o.ordertime >= ?) and (o.ordertime <= ?) and " +
                "(o.storeno >= ?) and (o.storeno <= ?) order by o.no desc";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, start);
        pstmt.setString(2, end);
        pstmt.setInt(3, storeNo);
        pstmt.setInt(4, storeNo == 0 ? 9999 : storeNo);
        ResultSet rs = pstmt.executeQuery();
        List<OrderVO> list = new ArrayList<>();
        while (rs.next()) {
            OrderVO order = new OrderVO();
            order.setNo(rs.getInt("no"));
            order.setPrice(rs.getInt("price"));
            order.setOrderTime(rs.getDate("ordertime"));
            order.setStoreNo(rs.getInt("storeno"));
            order.setStoreName(rs.getString("storename"));
            list.add(order);
        }

        ConnectionPool.close(rs);
        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return list;
    }

    public List<OrderListVO> orderListSelect(int orderNo) throws SQLException {
        String sql = "select o.orderno, o.menuno, o.quantity, (select m.name " +
                "from menu m where m.no = o.menuno) as menuname, " +
                "o.quantity * (select m2.price from menu m2 where m2.no = o.menuno) " +
                "as price from orderlist o where o.orderno = ?";
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
            menu.setMenuName(rs.getString("menuname"));
            menu.setPrice(rs.getInt("price"));
            list.add(menu);
        }

        ConnectionPool.close(rs);
        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return list;
    }
}
