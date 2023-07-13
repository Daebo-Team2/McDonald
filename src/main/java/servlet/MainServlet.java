package servlet;

import dao.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "MainServlet", value = "/MainServlet")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = ConnectionPool.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from menu");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("no"));
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}