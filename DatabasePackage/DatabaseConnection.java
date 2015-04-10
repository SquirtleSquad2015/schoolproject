package shoolprodject.DatabasePackage;

import shoolprodject.DatabasePackage.Database;

import java.sql.*;
import java.util.ArrayList;
 
/**
 * Created by jonas on 19.03.15.
 */
public class DatabaseConnection {
 
    private Connection connection;
 
    public void openConnection() throws Exception{
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/KjøpesenterTest");
        } catch (Exception e){
            Database.printMesssage(e, "Konstruktør");
            //Test
        }
    }
 
    public void closeConnection() throws Exception{
        Database.closeConnection(connection);
    }
 
    public ArrayList<String> getCenters(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sqlCenter = "SELECT DISTINCT center_name from center where LCASE(center_name) LIKE LCASE('" + centername + "%')";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCenter);
            while(resultSet.next()){
                list.add(resultSet.getString("Center_name"));
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "getCenters");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }
    
    public ArrayList<String> getStore(String storename){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sqlCenter = "SELECT DISTINCT store_name from store where LCASE(store_name) LIKE LCASE('" + storename + "%')";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCenter);
            while(resultSet.next()){
                list.add(resultSet.getString("store_name"));
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "getStore");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }
 
 
    public boolean checkUsername(String userName) throws Exception{
        Statement statement = null;
        ResultSet resultSet = null;
        boolean ok = true;
        try{
            statement = connection.createStatement();
            String sqlStatement = "select count(username) as Number from bruker where username='" + userName + "'";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            int number = resultSet.getInt("Number");
            if(number == 0){
                ok = false;
            }
        }
        catch (SQLException e){
            Database.printMesssage(e, "CheckUserName");
        }
        finally {
            Database.closeResSet(resultSet);
            Database.closeStatement(statement);
        }
        return ok;
    }
 
    public int regNewCenterUser(String userName, String telephone, char[] password,
                                       String centerName, String realName, String email, int userLevel, String title){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        String stringPassword = new String(password);
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sqlGetInfoAll = "select count(*) as count from administration where username='" + userName + "' OR tlf='" + telephone
                    + "' AND center_name='" + centerName + "'";
            resultSet = statement.executeQuery(sqlGetInfoAll);
            resultSet.next();
            int count = resultSet.getInt("count");
            if(count == 0){
                String sqlUpdateUser = "INSERT INTO bruker(access_lvl, username, password) VALUES(" + userLevel +
                        ", '" + userName + "', '" + stringPassword +  "')";
                String sqlUpdateAdministration = "INSERT INTO administration(navn, center_name, title, tlf, " +
                        "email, username) VALUES('" + realName + "', '" + centerName + "', '" + title + "', '" +
                        telephone + "', '" + email + "', '" + userName + "')";
                statement.executeUpdate(sqlUpdateUser);
                statement.executeUpdate(sqlUpdateAdministration);
                ok = 1;
            } else{
                String sqlGetTelephoneInfo = "select count(*) as count from administration where tlf='" + telephone + "'";
                resultSet = statement.executeQuery(sqlGetTelephoneInfo);
                resultSet.next();
                int countTelephone = resultSet.getInt("count");
                if(countTelephone == 0){
                    ok = 2;
                } else {
                    ok = 3;
                }
            }
        }
        catch (SQLException e){
            Database.printMesssage(e, "Register new centerManager");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
            Database.settAutoCommit(connection);
        }
        return ok;
    }

    public int checkLogIn(String username, String password){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String checkInfo = "select DISTINCT access_lvl from bruker where username='" + username + "'" +
                    " and password='" + password + "'";
            resultSet = statement.executeQuery(checkInfo);
            resultSet.next();
            ok = resultSet.getInt("access_lvl");
            System.out.println(ok);

        }
        catch (Exception e){
            Database.printMesssage(e, "checkLogIn");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
}