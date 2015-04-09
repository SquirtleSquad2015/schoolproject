
import java.sql.*;
public class Database {
    public static void closeResSet(ResultSet res) {
        try {
            if (res != null) {
                res.close();
            }
        } catch (SQLException e) {
            printMesssage(e, "closeResSet()");
        }
    }
 
    public static void closeStatement(Statement stm) {
        try {
            if (stm != null) {
                stm.close();
            }
        } catch (SQLException e) {
            printMesssage(e, "closeStatement");
        }
    }
 
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            printMesssage(e, "closeConnection()");
        }
    }
 
    public static void rollBack(Connection forbindelse) {
        try {
            if (forbindelse != null && !forbindelse.getAutoCommit()) {
                forbindelse.rollback();
            }
        } catch (SQLException e) {
            printMesssage(e, "rollback()");
        }
    }
 
    public static void settAutoCommit(Connection forbindelse) {
        try {
            if (forbindelse != null && !forbindelse.getAutoCommit()) {
                forbindelse.setAutoCommit(true);
            }
        } catch (SQLException e) {
            printMesssage(e, "setAutoCommit()");
        }
    }
 
    public static void printMesssage(Exception e, String message) {
        System.err.println("*** Error: " + message + ". ***");
        e.printStackTrace(System.err);
    }
}