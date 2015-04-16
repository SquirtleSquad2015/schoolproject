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
    public boolean checkDB() throws Exception{
        
        Statement statement = null;
        ResultSet resultSet = null;
        boolean ok = false;
        try{
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT center_name from center where LCASE(center_name) LIKE LCASE('CirCus')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            String number = resultSet.getString("center_name");
            if(number == "Circus"){
                ok = true;
            }
        }
        catch (SQLException e){
            Database.printMesssage(e, "CheckDB");
        }
        finally {
            Database.closeResSet(resultSet);
            Database.closeStatement(statement);
        }
        return ok;
        
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
    public ArrayList<String> getStore(String centerName){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sqlCenter = "SELECT DISTINCT store_name from store, center where store.CENTER_NAME=center.CENTER_NAME and center.CENTER_NAME='"+centerName+"'";
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
    public Integer getTurnoverStore(String centername, String storename){
        Statement statement = null;
        ResultSet resultSet = null;

        int svar = -1;
        try {
            String sqlCenter = "SELECT turnover from store where LCASE(store_name) LIKE LCASE('" + storename + "%') AND LCASE(center_name) LIKE LCASE('" + centername + "')";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCenter);

            while(resultSet.next()){
                svar = resultSet.getInt(1);
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "getTurnover");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return svar;
    }
    public String getTradeStore(String Storename){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT trade FROM store WHERE LCASE(store_name) LIKE LCASE('" + Storename + "')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur=resultSet.getString("trade");
        }
        catch (Exception e){
            Database.printMesssage(e, "getTradeStore");
            retur = "Feil";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public String getTradeCenter(String Centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT trade FROM trade WHERE LCASE(center_name) LIKE LCASE('" + Centername + "')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur=resultSet.getString("trade");
        }
        catch (Exception e){
            Database.printMesssage(e, "getTradeCenter");
            retur = "Feil";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public String getParking(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT car_park FROM center WHERE LCASE(center_name) LIKE LCASE('" + centername + "')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur=resultSet.getString("car_park");
        }
        catch (Exception e){
            Database.printMesssage(e, "getParking");
            retur = "Feil";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public String getAddress(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT address,MUNCIPALITY FROM center WHERE LCASE(center_name) LIKE LCASE('" + centername + "')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur=resultSet.getString("address");
            retur+=",   "+resultSet.getString("muncipality");
            
        }
        catch (Exception e){
            Database.printMesssage(e, "getAddress");
            retur = "Feil";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public String getCenterManager(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT navn FROM administration WHERE LCASE(center_name) LIKE LCASE('" + centername + "')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur=resultSet.getString("navn");
        }
        catch (Exception e){
            Database.printMesssage(e, "getCenterManager");
            retur = "Feil";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public String getStoreManager(String Storename){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT manager FROM store WHERE LCASE(store_name) LIKE LCASE('" + Storename + "')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur=resultSet.getString("manager");
        }
        catch (Exception e){
            Database.printMesssage(e, "getStoreManager");
            retur = "Feil";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public String getLocation(String Storename){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT floor,location FROM store WHERE LCASE(store_name) LIKE LCASE('" + Storename + "')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur="Floor:"+resultSet.getString("floor");
            retur+=",   Location: "+resultSet.getString("location");
        }
        catch (Exception e){
            Database.printMesssage(e, "getAddress");
            retur = "Feil";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public String getOpenings(String Storename){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT openinghrs,openinghrs_weekend FROM store WHERE LCASE(store_name) LIKE LCASE('" + Storename + "')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur="Openings:"+resultSet.getString("openinghrs");
            retur+=",   Weekend: "+resultSet.getString("openinghrs_weekend");
        }
        catch (Exception e){
            Database.printMesssage(e, "getOpenings");
            retur = "Feil";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
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