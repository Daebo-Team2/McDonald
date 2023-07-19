package dao;

import vo.OrderVO;

import java.sql.*;

public class OrderDAO {
    public OrderVO insertOrder(int price, int storeno) {
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] cols = new String[]{"no"};
        int key = -1;
        OrderVO order = null;
        Date time = new Date(System.currentTimeMillis());
        try {
            pstmt = conn.prepareStatement("insert into orders values (order_seq.nextval, ?, ?, ?)", cols);
            pstmt.setInt(1, price);
            pstmt.setDate(2, time);
            pstmt.setInt(3, storeno);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
            order = new OrderVO();
            order.setNo(key);
            order.setPrice(price);
            order.setOrderTime(time);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
          ConnectionPool.close(rs);
          ConnectionPool.close(pstmt);
          ConnectionPool.close(conn);
        }
        return order;
    }

    public int insertOrderList(int key, int menuno, int quantity) {
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = -1;
        try {
            pstmt = conn.prepareStatement("insert into orderlist values (?, ?, ?)");
            pstmt.setInt(1, key);
            pstmt.setInt(2, menuno);
            pstmt.setInt(3, quantity);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(rs);
            ConnectionPool.close(pstmt);
            ConnectionPool.close(conn);
        }
        return result;
    }
}
