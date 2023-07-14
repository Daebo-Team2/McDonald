package dao;

import vo.EmpVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class EmpDAO {
    public List<EmpVO> empSelectAll(int storeNo) throws SQLException {
        String sql = "SELECT * FROM EMP WHERE STORENO = ?";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, storeNo);
        ResultSet rs = pstmt.executeQuery();
        List<EmpVO> list = new ArrayList<>();
        while (rs.next()) {
            EmpVO emp = new EmpVO();
            emp.setNo(rs.getInt("no"));
            emp.setName(rs.getString("name"));
            emp.setStoreNo(rs.getInt("storeno"));
            emp.setHireDate(rs.getDate("hiredate"));
            emp.setTel(rs.getString("tel"));
            emp.setPay(rs.getInt("pay"));
            emp.setwTime(rs.getInt("wtime"));
            emp.setInTime(rs.getDate("intime"));
            list.add(emp);
        }

        ConnectionPool.close(rs);
        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return list;
    }

    public int empInsert(EmpVO emp) throws SQLException {
        int row = 0;
        String sql = "insert into emp values (emp_seq.nextval, ?, ?, ?, ?, ?, 0, null)";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, emp.getName());
        pstmt.setInt(2, emp.getStoreNo());
        pstmt.setDate(3, new Date(System.currentTimeMillis()));
        pstmt.setString(4, emp.getTel());
        pstmt.setInt(5, emp.getPay());
        row = pstmt.executeUpdate();

        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return row;
    }

    public EmpVO empSelect(int empNo) throws SQLException {
        EmpVO emp = null;
        String sql = "select * from emp where no = ?";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, empNo);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            emp = new EmpVO();
            emp.setNo(rs.getInt("no"));
            emp.setName(rs.getString("name"));
            emp.setStoreNo(rs.getInt("storeno"));
            emp.setHireDate(rs.getDate("hiredate"));
            emp.setTel(rs.getString("tel"));
            emp.setPay(rs.getInt("pay"));
            emp.setwTime(rs.getInt("wtime"));
            emp.setInTime(rs.getDate("intime"));
        }

        ConnectionPool.close(rs);
        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return emp;
    }

    public int empUpdate(EmpVO emp) throws SQLException {
        int row = 0;
        String sql = "UPDATE emp SET tel = ?, pay = ?, wtime = ?, intime = ? WHERE no = ?";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, emp.getTel());
        pstmt.setInt(2, emp.getPay());
        pstmt.setInt(3, emp.getwTime());
        pstmt.setDate(4, emp.getInTime());
        pstmt.setInt(5, emp.getNo());
        row = pstmt.executeUpdate();

        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return row;
    }

    public int empInOutInsert(EmpVO emp, Date date) throws SQLException {
        int row = 0;
        String sql = "insert into empinout values (?, ?, ?)";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, emp.getNo());
        pstmt.setDate(2, date);
        pstmt.setInt(3, emp.getInTime() == null ? 0 : 1);
        row = pstmt.executeUpdate();

        ConnectionPool.close(pstmt);
        ConnectionPool.close(conn);
        return row;
    }
}
