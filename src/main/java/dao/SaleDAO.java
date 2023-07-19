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
    public List<OrderVO> orderSelect(String start, String end, int storeNo, int rowS, int rowE) {
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrderVO> list = null;

        try {
            String sql = "select * from (select rownum rn, x.* from (select * from orders " +
                    "where (ordertime >= TO_DATE(?, 'yyyy-MM-dd')) and (ordertime < TO_DATE(?, 'yyyy-MM-dd') + 1) and (storeno >= ?) " +
                    "and (storeno <= ?) order by no desc) x) where rn between ? and ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, start);
            pstmt.setString(2, end);
            pstmt.setInt(3, storeNo);
            pstmt.setInt(4, storeNo == 0 ? 9999 : storeNo);
            pstmt.setInt(5, rowS);
            pstmt.setInt(6, rowE);

            rs = pstmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                OrderVO order = new OrderVO();
                order.setNo(rs.getInt("no"));
                order.setPrice(rs.getInt("price"));
                order.setOrderTime(rs.getDate("ordertime"));
                order.setStoreNo(rs.getInt("storeno"));
                list.add(order);
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

    public List<OrderListVO> orderListSelect(int orderNo) {
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrderListVO> list = null;

        try {
            String sql = "select * from orderlist where orderno = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderNo);

            rs = pstmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                OrderListVO menu = new OrderListVO();
                menu.setOrderNo(rs.getInt("orderno"));
                menu.setMenuNo(rs.getInt("menuno"));
                menu.setQuantity(rs.getInt("quantity"));
                list.add(menu);
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

    public List<OrderVO> menuSelect(String start, String end, String menuName, int storeNo, int rowS, int rowE) {
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrderVO> list = null;

        try {
            String sql = "select *\n" +
                    "from (select rownum rn, x.*\n" +
                    "      from (select ol.*, m.name as menuname, m.price as menuprice, o.ordertime as ordertime, o.storeno as storeno\n" +
                    "            from orderlist ol,\n" +
                    "                 orders o,\n" +
                    "                 store s,\n" +
                    "                 menu m\n" +
                    "            where (ol.orderno = o.no)\n" +
                    "              and (o.storeno = s.no)\n" +
                    "              and (ol.menuno = m.no)\n" +
                    "              and (m.name = ?)\n" +
                    "              and (o.ordertime >= TO_DATE(?, 'yyyy-MM-dd'))\n" +
                    "              and (o.ordertime < TO_DATE(?, 'yyyy-MM-dd') + 1)\n" +
                    "              and (o.storeno between ? and ?)\n" +
                    "            order by ol.orderno desc) x)\n" +
                    "where rn between ? and ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, menuName);
            pstmt.setString(2, start);
            pstmt.setString(3, end);
            pstmt.setInt(4, storeNo);
            pstmt.setInt(5, storeNo == 0 ? 9999 : storeNo);
            pstmt.setInt(6, rowS);
            pstmt.setInt(7, rowE);
            rs = pstmt.executeQuery();

            list = new ArrayList<>();
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(rs);
            ConnectionPool.close(pstmt);
            ConnectionPool.close(conn);
        }
        return list;
    }


    public int orderCount(String start, String end, int storeNo) {
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cnt = -1;

        try {
            String sql = "select count(*) as cnt from orders where (ordertime >= TO_DATE(?, 'yyyy-MM-dd')) and" +
                    "(ordertime < TO_DATE(?, 'yyyy-MM-dd') + 1) and (storeno between ? and ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, start);
            pstmt.setString(2, end);
            pstmt.setInt(3, storeNo);
            pstmt.setInt(4, storeNo == 0 ? 9999 : storeNo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(rs);
            ConnectionPool.close(pstmt);
            ConnectionPool.close(conn);
        }
        return cnt;
    }

    public int menuCount(String start, String end, String menuName, int storeNo) {
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cnt = -1;

        try {
            String sql = "select count(*) as cnt from (select ol.*, m.name, o.ordertime, o.storeno, s.name " +
                    "from orderlist ol, orders o, store s, menu m where (ol.orderno = o.no) and (o.storeno = s.no) " +
                    "and (ol.menuno = m.no) and (o.storeno between ? and ?) and (m.name = ?) " +
                    "and (o.ordertime >= TO_DATE(?, 'yyyy-MM-dd')) and (o.ordertime < TO_DATE(?, 'yyyy-MM-dd') + 1))";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, storeNo);
            pstmt.setInt(2, storeNo == 0 ? 9999 : storeNo);
            pstmt.setString(3, menuName);
            pstmt.setString(4, start);
            pstmt.setString(5, end);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cnt = rs.getInt("cnt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(rs);
            ConnectionPool.close(pstmt);
            ConnectionPool.close(conn);
        }
        return cnt;
    }

    public int getOrderSelectTotalPrice(String start, String end, int storeNo) {
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalPrice = 0;

        try {
            String sql = "select sum(price) as psum from orders where (ordertime >= TO_DATE(?, 'yyyy-MM-dd')) " +
                    "and (ordertime < TO_DATE(?, 'yyyy-MM-dd') + 1) and (storeno between ? and ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, start);
            pstmt.setString(2, end);
            pstmt.setInt(3, storeNo);
            pstmt.setInt(4, storeNo == 0 ? 9999 : storeNo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getInt("psum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(rs);
            ConnectionPool.close(pstmt);
            ConnectionPool.close(conn);
        }
        return totalPrice;
    }

    public int getMenuSelectTotalPrice(String start, String end, String menuName, int storeNo) {
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalPrice = 0;

        try {
            String sql = "select sum(price) as psum from (select ol.*, m.name, o.ordertime, o.storeno, " +
                    "(m.price * ol.quantity) as price from orderlist ol, orders o, store s, menu m " +
                    "where (ol.orderno = o.no) and (o.storeno = s.no) and (ol.menuno = m.no) and (m.name = ?) " +
                    "and (o.storeno between ? and ?) and (o.ordertime >= TO_DATE(?, 'yyyy-MM-dd')) and (o.ordertime < TO_DATE(?, 'yyyy-MM-dd') + 1))";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, menuName);
            pstmt.setInt(2, storeNo);
            pstmt.setInt(3, storeNo == 0 ? 9999 : storeNo);
            pstmt.setString(4, start);
            pstmt.setString(5, end);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getInt("psum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(rs);
            ConnectionPool.close(pstmt);
            ConnectionPool.close(conn);
        }
        return totalPrice;
    }
}
