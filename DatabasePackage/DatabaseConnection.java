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
            String sqlStatement = "SELECT DISTINCT Username from users where LCASE(Username) LIKE LCASE('admin')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            String number = resultSet.getString("username");
            if(number == "admin"){
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
            String sqlCenter = "SELECT DISTINCT center_name from center where LCASE(center.center_name) LIKE LCASE('%" + centername + "%')";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCenter);
            while(resultSet.next()){
                list.add(resultSet.getString("center_name"));
            }
            for(int i = 0; i<list.size(); i++){
                System.out.println(list.get(i));
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

    public ArrayList<String> getStoreAndTrade(String centerName, String trade){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sqlCenter = "SELECT DISTINCT store_name from store, center where store.CENTER_NAME=center.CENTER_NAME and center.CENTER_NAME='"+centerName+"' and store.trade='"+trade+"'";
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

    public ArrayList<String> getMuncipality(String kommun){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sqlCenter = "SELECT DISTINCT center_name from center where LCASE(muncipality) LIKE LCASE('%" + kommun + "%')";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCenter);
            while(resultSet.next()){
                list.add(resultSet.getString("center_name"));
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

    public String getCenterMunicipality(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String list = "";
        try {
            String sqlCenter = "SELECT DISTINCT muncipality from center where LCASE(center_name) LIKE LCASE('%" + centername + "%')";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCenter);
            resultSet.next();
            list = resultSet.getString("muncipality");

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
            retur = "No trades found in store";
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
            retur = "No trades found in center";
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
            retur = "No parking information found";
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
            retur+=", "+resultSet.getString("muncipality");

        }
        catch (Exception e){
            Database.printMesssage(e, "getAddress");
            retur = "No address found";
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
        String retur=null;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT name FROM person, center WHERE center.username = person.username AND LCASE( center.center_name ) LIKE LCASE(  '"+centername+"' ) ";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur=resultSet.getString("name");
        }
        catch (Exception e){
            Database.printMesssage(e, "getCenterManager");
            retur=null;
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public String getStoreManager(String centername,String storename){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT name FROM person, store WHERE person.username = store.username AND LCASE( store.store_name ) LIKE LCASE('"+storename+"') AND LCASE( store.center_name ) LIKE LCASE('"+centername+"')" ;
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur=resultSet.getString("name");
        }
        catch (Exception e){
            Database.printMesssage(e, "getStoreManager");
            retur = "No store manager found";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }

    public String getPersonName(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement= "SELECT name from person where LCASE(username) LIKE LCASE ('"+ username +"%')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur=resultSet.getString("name");
        }
        catch (Exception e){
            Database.printMesssage(e, "getPersonName");
            retur = "No name found";
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
            retur="Floor "+resultSet.getString("floor");
            retur+=", "+resultSet.getString("location");
        }
        catch (Exception e){
            Database.printMesssage(e, "getAddress");
            retur = "No location found";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public String getOpenings(String centerName,String Storename){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT DISTINCT openinghrs,openinghrs_weekends FROM store WHERE LCASE(store_name) LIKE LCASE('" + Storename + "') AND LCASE(center_name) LIKE LCASE('" + centerName + "')";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            retur=resultSet.getString("openinghrs");
            retur+=",   Weekends: "+resultSet.getString("openinghrs_weekends");
        }
        catch (Exception e){
            Database.printMesssage(e, "getOpenings");
            retur = "No opening hours found";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }

    //registrerer spørsmål sendt fra customer til customer support
    public boolean RegisterCustomerQuestion(String center, String subject, String question){
        Statement statement = null;        ;
        Statement resultSet = null;
        String n = "n";
        boolean ok = true;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sqlStatement = "INSERT INTO customer_service(center_name,subject,question,answer,solved)" +
                    "VALUES('" + center + "', '" + subject + "', '" +  question + "', 'null', '" + n + "')";

            statement.executeUpdate(sqlStatement);
        }
        catch (Exception e){
            ok = false;
            Database.printMesssage(e, "CustomerQuestion");
        }
        finally {
            Database.closeStatement(statement);
            Database.settAutoCommit(connection);
        }
        return ok;
    }

    public int getHighestCustomerCaseIndex(){
        Statement statement = null;
        ResultSet resultSet = null;
        int retur = -1;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT MAX(customer_case_ID) as max FROM customer_service;";
            resultSet = statement.executeQuery(sqlStatement);
            resultSet.next();
            int id = resultSet.getInt("max");
            retur = id;

        }
        catch (Exception e){
            Database.printMesssage(e, "CustomerIndex");
        }
        finally {
            Database.closeStatement(statement);
        }
        return retur;
    }

    public boolean checkUsername(String userName) throws Exception{
        Statement statement = null;
        ResultSet resultSet = null;
        boolean ok = true;
        try{
            statement = connection.createStatement();
            String sqlStatement = "select count(username) as Number from users where username='" + userName + "'";
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
                                       String centerName, String realName, String mail, int userLevel, String title){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        String stringPassword = new String(password);
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sqlGetInfoAll = "select count(*) as count from person where username='" + userName + "' OR tlf='" + telephone
                    + "' AND center_name='" + centerName + "'";
            resultSet = statement.executeQuery(sqlGetInfoAll);
            resultSet.next();
            int count = resultSet.getInt("count");
            if(count == 0){
                String sqlUpdateUser = "INSERT INTO users(access_lv, username, password, Activ) VALUES(" + userLevel +
                        ", '" + userName + "', '" + stringPassword +  "','n')";
                String sqlUpdate = "INSERT INTO person(name, center_name, title, tlf, " +
                        "mail, username) VALUES('" + realName + "', '" + centerName + "', '" + title + "', '" +
                        telephone + "', '" + mail + "', '" + userName + "')";
                statement.executeUpdate(sqlUpdateUser);
                statement.executeUpdate(sqlUpdate);
                ok = 1;
            } else{
                String sqlGetTelephoneInfo = "select count(*) as count from person where tlf='" + telephone + "'";
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
        System.out.println(ok);
        return ok;
    }

    public int checkLogIn(String username, String password){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String checkInfo = "select DISTINCT access_lv from users where username='" + username + "'" +
                    " and password='" + password + "'";
            resultSet = statement.executeQuery(checkInfo);
            resultSet.next();
            ok = resultSet.getInt("access_lv");
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

    public String getCustomerAnswer(int caseID){
        Statement statement = null;
        ResultSet resultSet = null;
        String answer="";
        try {
            String sqlAnswer = "SELECT DISTINCT answer as test from customer_service where customer_case_ID="+ caseID;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlAnswer);
            resultSet.next();
            answer = resultSet.getString("test");
            System.out.println(answer);
        }
        catch (Exception e){
            Database.printMesssage(e, "customerServiceGetAnswer");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return answer;
    }


    public String getCenter(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String center = "";
        try {
            String sqlSubject = "SELECT DISTINCT center_name from person where username='" + username + "'";
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

    public String getNoOfShops(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String no_of_shops ="";

        try {
            String sqlSubject = "SELECT nr_shops from center where center_name='" + centername + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            no_of_shops = resultSet.getString("nr_shops");
        }
        catch (Exception e){
            Database.printMesssage(e, "getNoOfShops");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return no_of_shops;
    }

    public String getSQM(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String SQM ="";
        try {
            String sqlSubject = "SELECT sqm from center where center_name='" + centername + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            SQM = resultSet.getString("sqm");
        }
        catch (Exception e){
            Database.printMesssage(e, "getSQM");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return SQM;
    }

    public String getCenterTelephone(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String tlf ="";

        try {
            String sqlSubject = "SELECT tlf from center where center_name='" + centername + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            tlf = resultSet.getString("tlf");
        }
        catch (Exception e){
            Database.printMesssage(e, "getCenterTelephone");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return tlf;
    }

    public String getCenterMail(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String email ="";

        try {
            String sqlSubject = "SELECT mail from center where center_name='" + centername + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            email = resultSet.getString("mail");
        }
        catch (Exception e){
            Database.printMesssage(e, "getCenterMail");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return email;
    }

    public String getCenterParking(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String parking ="";

        try {
            String sqlSubject = "SELECT car_park from center where center_name='" + centername + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            parking = resultSet.getString("car_park");

        }
        catch (Exception e){
            Database.printMesssage(e, "getCenterParking");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return parking;
    }

    public String getCenterDescription(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String description ="";

        try {
            String sqlSubject = "SELECT description from center where center_name='" + centername + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            description = resultSet.getString("description");
        }
        catch (Exception e){
            Database.printMesssage(e, "getDescription");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return description;
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
        String mail = "";
        try {
            String sqlSubject = "SELECT DISTINCT mail from person where username='"+ username+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlSubject);
            resultSet.next();
            mail = resultSet.getString("mail");
        }
        catch (Exception e){
            Database.printMesssage(e, "getEmail");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return mail;
    }

    public String getPhoneNumber(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String phoneNumber = "";
        try {
            String sqlSubject = "SELECT DISTINCT tlf from person where username='"+ username+"'";
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
            String sqlSubject = "UPDATE person SET mail='"+email+"' WHERE username='"+username +"'";
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
            String sqlcheckPhoneNumber = "SELECT COUNT(tlf) as tlf from person where tlf='"+phoneNumber + "'";
            String sqlUpdatePhoneNumber = "UPDATE person SET tlf='"+phoneNumber+"' WHERE username='"+username +"'";
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
            String sqlUpdateCenterMail = "UPDATE center SET mail='"+newMail+"' WHERE center_name='"+centerName +"'";
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
    public String getShopName(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String shopName = "";
        try {
            String sqlGetShop = "SELECT DISTINCT store_name from store where username='"+ username+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            resultSet.next();
            shopName = resultSet.getString("store_name");
        }
        catch (Exception e){
            Database.printMesssage(e, "getShop");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return shopName;
    }
    public String getShopTrade(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String shopTrade = "";
        try {
            String sqlGetShop = "SELECT DISTINCT trade from store where username='"+ username+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            resultSet.next();
            shopTrade = resultSet.getString("trade");
        }
        catch (Exception e){
            Database.printMesssage(e, "getStoreTrade");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return shopTrade;
    }
    public String getShopLocation(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String shopLocation = "";
        try {
            String sqlGetShop = "SELECT DISTINCT location from store where username='"+ username+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            resultSet.next();
            shopLocation = resultSet.getString("location");
        }
        catch (Exception e){
            Database.printMesssage(e, "getShopLocation");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return shopLocation;
    }
    public String getShopFloor(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String shopFloor = "";
        try {
            String sqlGetShop = "SELECT DISTINCT floor from store where username='"+ username+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            resultSet.next();
            shopFloor = resultSet.getString("floor");
        }
        catch (Exception e){
            Database.printMesssage(e, "getShopFloor");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return shopFloor;
    }
    public String getShopOpeningHrs(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String shopOpeningHrs = "";
        try {
            String sqlGetShop = "SELECT DISTINCT openingHrs from store where username='"+ username+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            resultSet.next();
            shopOpeningHrs = resultSet.getString("openingHrs");
        }
        catch (Exception e){
            Database.printMesssage(e, "getShopOpeningHrs");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return shopOpeningHrs;
    }
    public String getShopOpeningHrsWeekends(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String shopOpeningHrsWeekends = "";
        try {
            String sqlGetShop = "SELECT DISTINCT openinghrs_weekends from store where username='"+ username+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            resultSet.next();
            shopOpeningHrsWeekends = resultSet.getString("openinghrs_weekends");
        }
        catch (Exception e){
            Database.printMesssage(e, "getShopOpeningHrs_weekends");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return shopOpeningHrsWeekends;
    }
    public String getShopTurnover(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String shopTurnover = "";
        try {
            String sqlGetShop = "SELECT DISTINCT turnover from store where username='"+ username+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            resultSet.next();
            shopTurnover = resultSet.getString("turnover");
        }
        catch (Exception e){
            Database.printMesssage(e, "getShopTurnover");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return shopTurnover;
    }
    public String getShopDescription(String centerName, String storeName){
        Statement statement = null;
        ResultSet resultSet = null;
        String shopDescription = "";
        try {
            String sqlGetShop = "SELECT DISTINCT description FROM store WHERE LCASE(store_name) LIKE LCASE('" + storeName + "') AND LCASE(center_name) LIKE LCASE('" + centerName + "')";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            resultSet.next();
            shopDescription = resultSet.getString("description");
        }
        catch (Exception e){
            Database.printMesssage(e, "getShopDescription");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return shopDescription;
    }
    public int setStoreName(String username, String newStoreName){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdateCenterMail = "UPDATE store SET store_name='"+newStoreName+"' WHERE username='"+username +"'";
            ok = statement.executeUpdate(sqlUpdateCenterMail);
            System.out.println(ok);
        }
        catch (Exception e){
            Database.printMesssage(e, "setStoreName");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int setStoreLocation(String username, String newStoreLocation){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdateCenterMail = "UPDATE store SET location='"+newStoreLocation+"' WHERE username='"+username +"'";
            ok = statement.executeUpdate(sqlUpdateCenterMail);
            System.out.println(ok);
        }
        catch (Exception e){
            Database.printMesssage(e, "setStoreLocation");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int setStoreFloor(String username, int newFloor){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdateCenterMail = "UPDATE store SET floor="+newFloor+" WHERE username='"+username +"'";
            ok = statement.executeUpdate(sqlUpdateCenterMail);
            System.out.println(ok);
        }
        catch (Exception e){
            Database.printMesssage(e, "setStoreFloor");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int setStoreOpeningHrs(String username, String newOpeningHrs){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdateCenterMail = "UPDATE store SET openinghrs='"+newOpeningHrs+"' WHERE username='"+username +"'";
            ok = statement.executeUpdate(sqlUpdateCenterMail);
            System.out.println(ok);
        }
        catch (Exception e){
            Database.printMesssage(e, "setStoreOpeningHrs");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int setStoreOpeningHrsWeekends(String username, String newOpeningHrsWeekends){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "UPDATE store SET openinghrs_weekends='"+newOpeningHrsWeekends+"' WHERE username='"+username +"'";
            ok = statement.executeUpdate(sqlUpdate);
            System.out.println(ok);
        }
        catch (Exception e){
            Database.printMesssage(e, "setStoreOpeningHrsWeekends");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public ArrayList<String> getTrades(){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sql = "SELECT DISTINCT trade from trade";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                list.add(resultSet.getString("trade"));
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "getTrade");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }

    public ArrayList<String> getCenterFromTrade(String trade){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sql = "SELECT DISTINCT center_name FROM store WHERE ( store.trade =  '"+trade+"')";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                list.add(resultSet.getString("center_name"));
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "getCenterFromTrade");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }
    public String getTradeDescription(String trade){
        Statement statement = null;
        ResultSet resultSet = null;
        String description = "";
        try {
            String sqlGetShop = "SELECT DISTINCT description from trade where trade='"+ trade+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            resultSet.next();
            description = resultSet.getString("description");
        }
        catch (Exception e){
            Database.printMesssage(e, "getTradeDescription");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return description;
    }
    public int setTrade(String username, String trade){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "UPDATE store SET trade='"+trade+"' WHERE username='"+username +"'";
            ok = statement.executeUpdate(sqlUpdate);
            System.out.println(ok);
        }
        catch (Exception e){
            Database.printMesssage(e, "setTrade");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public String getShopDescription(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String shopDescription = "";
        try {
            String sqlGet = "SELECT DISTINCT description FROM store WHERE username='" + username + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGet);
            resultSet.next();
            shopDescription = resultSet.getString("description");
        }
        catch (Exception e){
            Database.printMesssage(e, "getShopDescription");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return shopDescription;
    }
    public int setStoreDescription(String username, String description){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "UPDATE store SET description='"+description+"' WHERE username='"+username +"'";
            ok = statement.executeUpdate(sqlUpdate);
            System.out.println(ok);
        }
        catch (Exception e){
            Database.printMesssage(e, "setStoreDescription");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int newCenter(String na,String mu,String tu,String sh,String sq,String ad,String tl,String ma,String ca,String de){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "INSERT INTO  `14hing06`.`center` (`center_name` ,`username` ,`muncipality` ,`turnover` ,`nr_shops` ,`sqm` ,`address` ,`mail` ,`tlf` ,`car_park` ,`description`)"
            +" VALUES ('"+na+"', NULL ,  '"+mu+"',  "+tu+",  "+sh+",  "+sq+",  '"+ad+"',  '"+tl+"',  '"+ma+"',  '"+ca+"',  '"+de+"');";
            ok = statement.executeUpdate(sqlUpdate);
            
        }
        catch (Exception e){
            Database.printMesssage(e, "newCenter");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int regNewStore(String storeName,String centerName,String trade, String location, String floor, String openingHrs,
                           String openingHrsWeekends, String description){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        int check = 0;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sqlInsert = "INSERT INTO store(store_name, center_name, username, turnover, trade, location," +
                    "floor, openingHrs, openingHrs_weekends, description) VALUES ('"+storeName+"','"+centerName+"',"+
                    null+",0,'"+trade+"','"+location+"',"+floor+",'"+openingHrs+"','"+openingHrsWeekends+"','"+description+"')";
            ok = statement.executeUpdate(sqlInsert);
            if(ok == 1){
                String sqlGet = "SELECT DISTINCT nr_shops from center where center_name='"+centerName+"'";
                resultSet = statement.executeQuery(sqlGet);
                resultSet.next();
                int shopNr = resultSet.getInt("nr_shops") + 1;
                String sqlUpdate = "UPDATE center SET nr_shops='"+shopNr+"' WHERE center_name='"+centerName +"'";
                check = statement.executeUpdate(sqlUpdate);
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "setStoreDescription");
        }
        finally {
            Database.settAutoCommit(connection);
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return check;
    }
    public int setCenterSqm(String newSqm, String centerName){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "UPDATE center SET sqm='"+newSqm+"' WHERE center_name='"+centerName +"'";
            ok = statement.executeUpdate(sqlUpdate);
        }
        catch (Exception e){
            Database.printMesssage(e, "setCenterSqm");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int setCenterCarPark(char carPark, String centerName){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "UPDATE center SET car_park='"+carPark+"' WHERE center_name='"+centerName +"'";
            ok = statement.executeUpdate(sqlUpdate);
        }
        catch (Exception e){
            Database.printMesssage(e, "setCenterCarPark");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int setCenterDescription(String newDescription, String centerName){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "UPDATE center SET description='"+newDescription+"' WHERE center_name='"+centerName +"'";
            ok = statement.executeUpdate(sqlUpdate);
        }
        catch (Exception e){
            Database.printMesssage(e, "setCenterDescription");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public ArrayList<String> getUsers(){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sqlGetShop ="SELECT username FROM users ;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            while(resultSet.next()){
                list.add(resultSet.getString("username"));
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "getUsersNotActiv");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }
    public int getUserAccess(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        int retur=-1;
        try {
            String sqlGet = "SELECT Access_lv FROM users WHERE username ='"+username+"';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGet);
            resultSet.next();
            retur = resultSet.getInt("Access_lv");
        }
        catch (Exception e){
            Database.printMesssage(e, "getUserAccess");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public String getUserActiv(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur="";
        try {
            String sqlGet = "SELECT Activ FROM users WHERE username ='"+username+"';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGet);
            resultSet.next();
            retur = resultSet.getString("Activ");
        }
        catch (Exception e){
            Database.printMesssage(e, "getUserActiv");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    } 
    public String getUserTitle(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur="";
        try {
            String sqlGet = "SELECT Title FROM person WHERE username ='"+username+"';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGet);
            resultSet.next();
            retur = resultSet.getString("Title");
        }
        catch (Exception e){
            Database.printMesssage(e, "getUserTitle");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    } 
    public int setUserActiv(String activ,String username){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok=0;
        try {
            statement = connection.createStatement();
            String sqlUpdateCenterMail = "UPDATE users SET Activ='"+activ+"' WHERE username='"+username+"';";
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
    public String getStoreUsername(String centerName, String storename){
        Statement statement = null;
        ResultSet resultSet = null;
        String storeUserName = "";
        try {
            String sqlGet = "SELECT username FROM store WHERE store_name='" + storename + "' AND center_name='" + centerName + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGet);
            resultSet.next();
            storeUserName = resultSet.getString("username");
        }
        catch (Exception e){
            Database.printMesssage(e, "getStoreUsername");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return storeUserName;
    }
     public String getCenterUsername(String centerName){
        Statement statement = null;
        ResultSet resultSet = null;
        String centerUserName = "";
        try {
            String sqlGet = "SELECT username FROM center WHERE center_name='" + centerName + "'; ";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGet);
            resultSet.next();
            centerUserName = resultSet.getString("username");
        }
        catch (Exception e){
            Database.printMesssage(e, "getCenterUsername");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return centerUserName;
    }
     public int setNewPassword(String username, String newPassword){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "UPDATE users SET password='"+newPassword+"' WHERE username='"+username +"'";
            ok = statement.executeUpdate(sqlUpdate);
        }
        catch (Exception e){
            Database.printMesssage(e, "setCenterDescription");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
     public ArrayList<String> getUsersCenterManager(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sqlGetShop ="SELECT username FROM person where center_name='"+centername+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            while(resultSet.next()){
                list.add(resultSet.getString("username"));
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "getUsersNotActiv");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }

    public ArrayList<String> getStoresWithoutUser(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            String sqlGetShop ="SELECT store_name FROM store where center_name='"+centername+"' AND username IS NULL";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGetShop);
            while(resultSet.next()){
                list.add(resultSet.getString("store_name"));
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "getStoresWithoutUsers");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }
    public ArrayList<String> getUsersWithoutStore(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        try {
            statement = connection.createStatement();
            String sqlGet ="SELECT username FROM person where center_name='"+centername+"' AND title='Store Manager'";
            String sqlGetUsername = "SELECT username from store where center_name='"+centername+"'";
            resultSet = statement.executeQuery(sqlGet);
            while(resultSet.next()){
                list.add(resultSet.getString("username"));
            }
            resultSet = statement.executeQuery(sqlGetUsername);
            while (resultSet.next()){
                list2.add(resultSet.getString("username"));
            }
            list.removeAll(list2);
        }
        catch (Exception e){
            Database.printMesssage(e, "getStoresWithoutUsers");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }
    public ArrayList<String> getUsersNotActiv(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        ArrayList<String> compareUsers = new ArrayList<String>();
        try {
            statement = connection.createStatement();
            String sqlGet ="SELECT username FROM person where center_name='"+centername+"'";
            String sqlGetUsername = "SELECT username from users where activ='n'";
            resultSet = statement.executeQuery(sqlGet);
            while(resultSet.next()){
                list.add(resultSet.getString("username"));
            }
            resultSet = statement.executeQuery(sqlGetUsername);
            while (resultSet.next()){
                list2.add(resultSet.getString("username"));
            }
            for(int i = 0; i < list.size(); i++){
                if(list2.contains(list.get(i))){
                    compareUsers.add(list.get(i));
                    System.out.println(list.get(i));
                }
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "getStoresWithoutUsers");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return compareUsers;
    }
    public int setStoreUser(String username, String storename, String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "UPDATE store SET username='"+username+"' WHERE center_name='"+centername +"' AND store_name='"+storename
                    +"'";
            ok = statement.executeUpdate(sqlUpdate);
        }
        catch (Exception e){
            Database.printMesssage(e, "setStoreUser");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int deleteStore(String storename, String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        int check = 0;
        try {
            String username = getStoreUsername(centername, storename);
            connection.setAutoCommit(false);
            String sqlSubject = "DELETE FROM store WHERE store_name='"+storename+"' AND center_name='"+centername+"'";
            statement = connection.createStatement();
            ok = statement.executeUpdate(sqlSubject);
            if(ok == 1){
                String sqlGet = "SELECT DISTINCT nr_shops from center where center_name='"+centername+"'";
                resultSet = statement.executeQuery(sqlGet);
                resultSet.next();
                int shopNr = resultSet.getInt("nr_shops") - 1;
                String sqlUpdate = "UPDATE center SET nr_shops='"+shopNr+"' WHERE center_name='"+centername +"'";
                check = statement.executeUpdate(sqlUpdate);
                int checkPerson = deletePerson(username);
                if(checkPerson == 1){
                    check = deleteUser(username);
                }
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "deleteStore");
        }
        finally {
            Database.settAutoCommit(connection);
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return check;
    }
    public int deletePerson(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "DELETE FROM person WHERE username='"+username+"';";
            ok = statement.executeUpdate(sqlUpdate);
        }
        catch (Exception e){
            Database.printMesssage(e, "deletePerson");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public int deleteUser(String username){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdate = "DELETE FROM users WHERE username='"+username+"';";
            ok = statement.executeUpdate(sqlUpdate);
        }
        catch (Exception e){
            Database.printMesssage(e, "deleteUser");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    public String getUsernameCenter(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur="";
        try {
            String sqlGet = "SELECT DISTINCT username FROM center WHERE center_name='" + centername + "';";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlGet);
            resultSet.next();
            retur = resultSet.getString("username");
        }
        catch (Exception e){
            Database.printMesssage(e, "getUsersNotActiv");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }
    public void deleteCenter(String centerName){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = getStore(centerName);
        for(int i=0;i<list.size();i++){
            String test =list.get(i);
            System.out.println(test+" slettet");
            deleteStore(test,centerName);
        }
        int ok = 0;
        try {
            String username = getCenterUsername(centerName);
            deletePerson(username);
            deleteUser(username);
            System.out.println(username+" slettet");
            String sqlSubject = "DELETE FROM center WHERE center_name='"+centerName+"'";
            statement = connection.createStatement();
            ok = statement.executeUpdate(sqlSubject);
            
        }
        catch (Exception e){
            Database.printMesssage(e, "deleteCenter");
        }
        finally {
            Database.settAutoCommit(connection);
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        //return check;
    }

    public ArrayList<String> getCenterWithoutUser(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            statement = connection.createStatement();
            String sqlGet ="SELECT center_name FROM center WHERE username is null;";
            resultSet = statement.executeQuery(sqlGet);
            while(resultSet.next()){
                list.add(resultSet.getString("center_name"));
            }
        }
        catch (Exception e){
            Database.printMesssage(e, "getCenterWithoutUser");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }
    public int setCenterManager(String username, String centerName){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            statement = connection.createStatement();
            String sqlUpdateCenterMail = "UPDATE center SET username='"+username+"' WHERE center_name='"+centerName+"';";
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
    public int setAnnualTurnover(String username, int turnover){
        Statement statement = null;
        ResultSet resultSet = null;
        int ok = 0;
        try {
            String sqlSubject = "UPDATE store SET turnover="+turnover+" WHERE username='"+username+"'";
            statement = connection.createStatement();
            ok = statement.executeUpdate(sqlSubject);
        }
        catch (Exception e){
            Database.printMesssage(e, "setAnnualTurnover");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }
    
}
