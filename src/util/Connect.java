package util;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class Connect {
    private static Connection conn = null;

    public static void start(){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
        	conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\LuvWi\\git\\lokka-petclinic\\db\\data.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("< - - - HELLO DB! - - - >");
    }

    public static void end(){
        try {
            if (conn!= null && !conn.isClosed())conn.close();
        } catch (Exception e){
        	e.printStackTrace();
        }
        System.out.println("< - - - FAREWELL! - - - >"+"\n");
    }

    public static ResultSet exeQuery(String queryStmt) throws SQLException{
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            start();
            System.out.println("statement: " + queryStmt);
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("got problem when operated : " + e);
            throw e;
        } finally {
            if (resultSet != null) resultSet.close();
            if (stmt != null) stmt.close();
            end();
        }
        return crs;
    }

    public static void exeUpdate(String sqlStmt) throws SQLException{
        Statement stmt = null;
        try {
            start();
            System.out.println("statement: " + sqlStmt);
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("got problem when operated : " + e);
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            end();
        }
    }
}
