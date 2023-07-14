package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import vo.StockOrderListVO;
import vo.StockOrderVO;

public class StockOrderDAO {

    public List<StockOrderVO> selectAllStockOrder() { //본사 : 발주 요청 조회 1) select * from stockorderlist

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StockOrderVO> list = new ArrayList<>();

        try {
            conn = ConnectionPool.getConnection();

            String sql = "SELECT * FROM STOCKORDER WHERE STATUS = 0 ORDER BY NO DESC ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StockOrderVO vo = new StockOrderVO();
                vo.setNo(rs.getInt("no"));
                vo.setStoreno(rs.getInt("storeno"));
                vo.setTime(rs.getTimestamp("time"));
                vo.setStatus(rs.getInt("status"));

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
    } //end selectAllStockOrder


    public List selectAllStockOrderList(int stockorerno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StockOrderListVO> list = new ArrayList<>();

        try {
            conn = ConnectionPool.getConnection();

            String sql = "SELECT * FROM STOCKORDERLIST WHERE STOCKORDERNO = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stockorerno);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StockOrderListVO vo = new StockOrderListVO();
                vo.setFoodno(rs.getInt("foodno"));
                vo.setQuantity(rs.getInt("quantity"));
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
    } //end selectAllStockOrderList

    public List<StockOrderVO> StockOrderPage(Map<String, Object> map) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<StockOrderVO> list = new Vector<StockOrderVO>(); //결과(게시물 목록)을 담을 변수

        try {
            conn = ConnectionPool.getConnection();
            String sql = "select * from ( "
                    + "select Tb.*, rownum rNum from ( "
                    + "select * from stockorder "
                    + "where status = 0 "
                    + "order by no desc "
                    + " ) Tb "
                    + ") "
                    + "where rNum between ? and ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, map.get("start").toString());
            pstmt.setString(2, map.get("end").toString());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                StockOrderVO vo = new StockOrderVO();
                vo.setNo(rs.getInt("no"));
                vo.setStoreno(rs.getInt("storeno"));
                vo.setTime(rs.getTimestamp("time"));
                vo.setStatus(rs.getInt("status"));

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


    public int confrimStockOder(int stockorderno) { //본사 : 발주 요청 확인-> status 0 -> 1

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        System.out.println("confrimStockOder");

        try {
            conn = ConnectionPool.getConnection();
            String sql = "UPDATE STOCKORDER SET STATUS = 1 WHERE NO = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stockorderno);
            result = pstmt.executeUpdate();
            System.out.println("result : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(pstmt);
            ConnectionPool.close(conn);
        }

        return result;
    } //end confrimeStockOder


    public int insertStockOrder(int storeno) { //가맹점 :  발주 주문 1) insert into stockorder

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;

        try {
            conn = ConnectionPool.getConnection();
            //System.out.println("insertStockOrder !!");
            String[] cols = new String[]{"no"};
            String sql = "INSERT INTO STOCKORDER (NO, STORENO, TIME, STATUS ) VALUES (STOCKORDER_SEQ.NEXTVAL, ?, CURRENT_TIMESTAMP, 0) ";
            pstmt = conn.prepareStatement(sql, cols);
            pstmt.setInt(1, storeno);

            int row = pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys(); //no
            if (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(rs);
            ConnectionPool.close(pstmt);
            ConnectionPool.close(conn);
        }
        return result;
    } //end insertStockOrder

    public int insertStockOrderList(int stockorderno, int foodno, int quantity) { //가맹점 :  발주 주문 2) inset into stockorderlist
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;


        try {
            conn = ConnectionPool.getConnection();

            String sql = "INSERT INTO STOCKORDERLIST (STOCKORDERNO, FOODNO, QUANTITY ) VALUES (?, ?, ?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stockorderno);
            pstmt.setInt(2, foodno);
            pstmt.setInt(3, quantity);

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.close(pstmt);
            ConnectionPool.close(conn);
        }

        return result;
    }


}
