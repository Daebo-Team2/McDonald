package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class TestDAO {
    public Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc:projectDB");
        Connection conn = ds.getConnection();
        return conn;
    }

    public String test() {
        String msg = "connection success";
        try {
            this.getConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
            msg = "connection failed";
        }
        return msg;
    }
}
