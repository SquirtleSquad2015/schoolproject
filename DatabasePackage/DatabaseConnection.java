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
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://mysql.stud.aitel.hist.no:3306/14hing06","14hing06","Aiz3ee");
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
            String sqlStatement = "SELECT DISTINCT Username from Users where LCASE(Username) LIKE LCASE('martpe')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            String number = resultSet.getString("Username");
            if(number == "martpe"){
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
    public ArrayList<String> customerServiceGetTitle(String title, String center_name, char solved){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sqlSubject = "SELECT subject from customer_service where LCASE(subject) LIKE LCASE('" + title + "%')" +
                    "AND center_name='" + center_name +"' AND solved='" + solved +"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            while(resultSet.next()){
                System.out.println(resultSet.getString("subject"));
                list.add(resultSet.getString("subject"));
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "customerServiceGetTitle");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }
    public String getCenter(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String center = "";
        try {
            String sqlSubject = "SELECT DISTINCT center_name from administration where username='" + username + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            center = resultSet.getString("center_name");
        }
        catch (Exception e){
            Database.printMesssage(e, "customerServiceGetCenter");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return center;
    }

    public ArrayList<Integer> getCustomerCaseID(String title, String center_name, char solved){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            String sqlSubject = "SELECT DISTINCT customer_case_ID from customer_service where LCASE(subject) LIKE LCASE('" + title + "%')" +
                    "AND center_name='" + center_name +"' AND solved='" + solved + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            while(resultSet.next()){
                list.add(resultSet.getInt("customer_case_ID"));
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "customerServiceGetTitle");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }
    public String getDescription(int caseID){
        Statement statement = null;
        ResultSet resultSet = null;
        String question = "";
        try {
            String sqlSubject = "SELECT DISTINCT question from customer_service where customer_case_ID="+ caseID;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            question = resultSet.getString("question");
        }
        catch (Exception e){
            Database.printMesssage(e, "customerServiceGetCenter");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return question;
    }
    public int setAnswer(String answer, int caseID){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            String sqlSubject = "UPDATE customer_service SET answer='"+answer+"', solved='y' WHERE customer_case_ID="+caseID;
            statement = connection.createStatement();
            ok = statement.executeUpdate(sqlSubject);
        }
        catch (Exception e){
            Database.printMesssage(e, "setAnswer");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int deleteCustomerCase(int caseID){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            String sqlSubject = "DELETE FROM customer_service WHERE customer_case_ID="+caseID;
            statement = connection.createStatement();
            ok = statement.executeUpdate(sqlSubject);
        }
        catch (Exception e){
            Database.printMesssage(e, "setAnswer");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public String getEmail(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String email = "";
        try {
            String sqlSubject = "SELECT DISTINCT email from administration where username='"+ username+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            email = resultSet.getString("email");
        }
        catch (Exception e){
            Database.printMesssage(e, "getEmail");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return email;
    }

    public String getPhoneNumber(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String phoneNumber = "";
        try {
            String sqlSubject = "SELECT DISTINCT tlf from administration where username='"+ username+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            phoneNumber = resultSet.getString("tlf");
        }
        catch (Exception e){
            Database.printMesssage(e, "getTlf");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return phoneNumber;
    }
    public int setEmail(String email, String username){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            String sqlSubject = "UPDATE administration SET email='"+email+"' WHERE username='"+username +"'";
            statement = connection.createStatement();
            ok = statement.executeUpdate(sqlSubject);
        }
        catch (Exception e){
            Database.printMesssage(e, "setEmail");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int setPhoneNumber(String phoneNumber, String username){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sqlcheckPhoneNumber = "SELECT COUNT(tlf) as tlf from administration where tlf='"+phoneNumber + "'";
            String sqlUpdatePhoneNumber = "UPDATE administration SET tlf='"+phoneNumber+"' WHERE username='"+username +"'";
            resultSet = statement.executeQuery(sqlcheckPhoneNumber);
            resultSet.next();
            ok = resultSet.getInt("tlf");
            if(ok == 0){
                statement.executeUpdate(sqlUpdatePhoneNumber);
                ok = 0;
            } else {
                return ok;
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "setEmail");
        }
        finally {
            Database.settAutoCommit(connection);
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    
    public int setCenterMail(String newMail, String centerName){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdateCenterMail = "UPDATE center SET email='"+newMail+"' WHERE center_name='"+centerName +"'";
            ok = statement.executeUpdate(sqlUpdateCenterMail);
            System.out.println(ok);
            }
        catch (Exception e){
            Database.printMesssage(e, "setCenterMail");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int setCenterPhoneNumber(String newPhoneNumber, String centerName){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdateCenterMail = "UPDATE center SET tlf='"+newPhoneNumber+"' WHERE center_name='"+centerName +"'";
            ok = statement.executeUpdate(sqlUpdateCenterMail);
            System.out.println(ok);
        }
        catch (Exception e){
            Database.printMesssage(e, "setCenterPhoneNumber");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    
}
