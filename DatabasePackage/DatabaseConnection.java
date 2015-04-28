package shoolprodject.DatabasePackage;


import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    private Connection connection;

    /**
     *
     * @throws Exception
     */
    public void openConnection() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://mysql.stud.aitel.hist.no:3306/14hing06","14hing06","Aiz3ee");
        } catch (Exception e){
            Database.printMesssage(e, "Konstruktør");
        }
    }

    /**
     *
     * @throws Exception
     */
    public void closeConnection() throws Exception{
        Database.closeConnection(connection);
    }

    /**
     * Checks if the database can be connected.
     *
     * @return              Returns true if it can connect to our database, false otherwise.
     * @throws Exception
     */
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
            if(number.equals("admin")){
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

    /**
     * Returns an ArrayList containing the names of shopping centers that matches the full or partial center
     * name that is entered as argument.
     * If the argument is empty, every center name will be returned.
     *
     * @param centername    A full or a partial name of a shopping center that the user wants to be returned.
     * @return              Returns a list of shopping centers that matches the center name inserted.
     */
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

    /**
     * Returns an ArrayList containing the existing stores inside a specified center.
     * The centerName argument must specify a complete center name.
     *
     * @param centerName    Complete name of a shopping center
     * @return              ArrayList of existing stores specified by the centerName
     */
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

    /**
     * Returns an ArrayList containing store name that are sorted out by a specific trade.
     *
     * @param centerName    Complete name of a shopping center
     * @param trade         Name on a trade
     * @return              ArrayList containing stores that are in the specified trade
     */
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
            Database.printMesssage(e, "getStoreAndTrade");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }

    /**
     * Returns an ArrayList containing center names in a specified municipality.
     * If the argument is empty, all center names will be returned
     *
     * @param kommun    Complete or partial name of a municipality.
     * @return          ArrayList containing center names in a specified municipality.
     */
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
            Database.printMesssage(e, "getMunicipality");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }

    /**
     * Return the municipality of a specified center name.
     *
     * @param centername    Complete or partial center name
     * @return              Municipality a the center name
     */
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
            Database.printMesssage(e, "getCenterMunicipality");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }

    /**
     * Returns the annual turnover to a specific store in a specified center.
     * If no annual turnover is found that matches the arguments, -1 is returned
     * @param centername    Specific center name
     * @param storename     Specific store name
     * @return              Annual turnover for the selected store
     */
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
            Database.printMesssage(e, "getTurnoverStore");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return svar;
    }

    /**
     * Returns the trade of a specific store.
     * If no store is found, "No trades found in store"
     *
     * @param Storename     Specific store name
     * @return              Returns Trade
     */
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

    /**
     * Returns a string that contains either n or y depending on the center.
     * If no center is found "No parking information found" is returned.
     *
     * @param centername    Specific center name
     * @return              y, n or "No parking information found".
     */
    public String getParking(String centername){
        Statement statement = null;
        ResultSet resultSet = null;
        String retur;
        try {
            statement = connection.createStatement();
            String sqlStatement = "SELECT car_park from center where center_name='" + centername + "'";
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

    /**
     * Returns the address of a specific center.
     *
     * @param centername    Specific center name
     * @return              Address
     */
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

    /**
     * Returns the center manager of a specific center
     *
     * @param centername    Specific center name
     * @return              The username to the center manager of the specified center
     */
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

    /**
     * Returns the name of the store manager to the specific store and center
     *
     * @param centername    Specific center name
     * @param storename     Specific store name
     * @return              The name or "No store manager found" as a String
     */
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

    /**
     * Returns a String containing the name of the store manager.
     *
     * @param username  Specific username
     * @return          Name or "No name found"
     */
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

    /**
     * Returns a String containing the location and the floor to a specific store.
     *
     * @param Storename     Specific store name
     * @return              The location or "No location found"
     */
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
            Database.printMesssage(e, "getLocation");
            retur = "No location found";
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return retur;
    }

    /**
     * Returns a String containing opening hours for a specific store.
     *
     * @param centerName    Specific store name
     * @param Storename     Specific center name
     * @return              Opening hours or "No opening hours found"
     */
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

    /**
     * Returns a boolean value of true or false depending on how the registration turns out.
     *
     * @param center        Center name
     * @param subject       Subject of the question
     * @param question      The actual question
     * @return              True or false
     */
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
            Database.printMesssage(e, "RegCustomerQuestion");
        }
        finally {
            Database.closeStatement(statement);
            Database.settAutoCommit(connection);
        }
        return ok;
    }

    /**
     * Returns the highest customer case index as a int.
     *
     * @return      highest number of the customer case id or -1
     */
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

    /**
     * Returns a boolean value depending on the outcome of the username check.
     * If the username is already registered in the database false is returned.
     *
     * @param userName      Specific username
     * @return              True or false
     * @throws Exception
     */
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

    /**
     * Returns an Integer depending on the outcome of the registration of the new user.
     *
     * @param userName      New username
     * @param telephone     new phone number
     * @param password      Password
     * @param centerName    Specific Center name
     * @param realName      Real name
     * @param mail          Email
     * @param userLevel     The user level
     * @param title         The title of the user
     * @return              Returns a number between 1 and 4
     */
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
            Database.printMesssage(e, "RegNewCenterManager");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
            Database.settAutoCommit(connection);
        }
        System.out.println(ok);
        return ok;
    }

    /**
     * Returns a int value depending on the access level of the user.
     *
     * @param username     Username
     * @param password     Password
     * @return             Access level of the user or 0.
     */
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

    /**
     * Returns an ArrayList containing subjects of the customer service questions that matches the title
     * that is given.
     * The title can be a complete word or just a partial. If it is left empty. Every subject is returned
     *
     * @param title             Complete or partial title word
     * @param center_name       Center name
     * @param solved            If the case is solved or not
     * @return                  A list containing all subject names that matches the title complete or partially
     */
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

    /**
     * Returns the answer on a solved customer service case.
     *
     * @param caseID    The case ID given when the question was submitted
     * @return          String that contains either the answer or is empty
     */
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

    /**
     * Returns a String containing the center name that the person with username is working on.
     *
     * @param username      Username
     * @return              Center name or an empty String if no center is found matching the criteria
     */
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
            Database.printMesssage(e, "getCenter");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return center;
    }

    /**
     * Returns a String containing the number of shops in a specific center
     *
     * @param centername        Center name
     * @return                  Number of shops or empty if no center is found  matching the criteria
     */
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

    /**
     * Returns a String containing the square meters of a specific center.
     *
     * @param centername        Center name
     * @return                  Number of square meters or an empty string if no center is found matching the criteria.
     */
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

    /**
     * Returns a String containing the phone number to a specific center
     *
     * @param centername        Center name
     * @return                  Phone number or an empty string, if no center is found  matching the criteria
     */
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

    /**
     * Return a String containing the email address to a specific center
     *
     * @param centername         Center name
     * @return                   Email address or an empty string if no center is found matching the criteria  
     */
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

    /**
     * Returns a String containing the center description of a specific center.
     *
     * @param centername        Center name
     * @return                  Center description or an empty string if no center is found matching the criteria
     */
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

    /**
     * Returns an ArrayList containing the customer case ID matching completely or
     * partially matching the title.
     *
     * @param title             Title
     * @param center_name       Center name
     * @param solved            If the case is solved or not
     * @return                  ArrayList containing the Customer case ID where the subject is matching the title
     */
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

    /**
     * Returns a String containing the customer service question with matching case ID.
     *
     * @param caseID    Case ID
     * @return          Question or an empty String depending on the database values
     */
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

    /**
     *
     * @param answer
     * @param caseID
     * @return
     */
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

    /**
     * Returns a int
     * @param caseID
     * @return
     */
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
            Database.printMesssage(e, "deleteCustomerCase");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param email
     * @param username
     * @return
     */
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

    /**
     *
     * @param phoneNumber
     * @param username
     * @return
     */
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
            Database.printMesssage(e, "setPhoneNumber");
        }
        finally {
            Database.settAutoCommit(connection);
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }

    /**
     *
     * @param newMail
     * @param centerName
     * @return
     */
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

    /**
     *
     * @param newPhoneNumber
     * @param centerName
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param centerName
     * @param storeName
     * @return
     */
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

    /**
     *
     * @param username
     * @param newStoreName
     * @return
     */
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

    /**
     *
     * @param username
     * @param newStoreLocation
     * @return
     */
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

    /**
     *
     * @param username
     * @param newFloor
     * @return
     */
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

    /**
     *
     * @param username
     * @param newOpeningHrs
     * @return
     */
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

    /**
     *
     * @param username
     * @param newOpeningHrsWeekends
     * @return
     */
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @param trade
     * @return
     */
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

    /**
     *
     * @param trade
     * @return
     */
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

    /**
     *
     * @param username
     * @param trade
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @param description
     * @return
     */
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

    /**
     *
     * @param na
     * @param mu
     * @param tu
     * @param sh
     * @param sq
     * @param ad
     * @param tl
     * @param ma
     * @param ca
     * @param de
     * @return
     */
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

    /**
     *
     * @param storeName
     * @param centerName
     * @param trade
     * @param location
     * @param floor
     * @param openingHrs
     * @param openingHrsWeekends
     * @param description
     * @return
     */
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
            Database.printMesssage(e, "regNewStore");
        }
        finally {
            Database.settAutoCommit(connection);
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return check;
    }

    /**
     *
     * @param newSqm
     * @param centerName
     * @return
     */
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

    /**
     *
     * @param carPark
     * @param centerName
     * @return
     */
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

    /**
     *
     * @param newDescription
     * @param centerName
     * @return
     */
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

    /**
     *
     * @return
     */
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
            Database.printMesssage(e, "getUsers");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return list;
    }

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param activ
     * @param username
     * @return
     */
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

    /**
     *
     * @param centerName
     * @param storename
     * @return
     */
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

    /**
     *
     * @param centerName
     * @return
     */
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

    /**
     *
     * @param username
     * @param newPassword
     * @return
     */
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

    /**
     *
     * @param centername
     * @return
     */
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

    /**
     *
     * @param centername
     * @return
     */
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

    /**
     *
     * @param centername
     * @return
     */
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

    /**
     *
     * @param centername
     * @return
     */
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
            Database.printMesssage(e, "getUsersNotActiv");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return compareUsers;
    }

    /**
     *
     * @param username
     * @param storename
     * @param centername
     * @return
     */
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

    /**
     *
     * @param storename
     * @param centername
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param username
     * @return
     */
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

    /**
     *
     * @param centername
     * @return
     */
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

    /**
     *
     * @param centerName
     * @return
     */
    public int deleteCenter(String centerName){
        Statement statement = null;
        ResultSet resultSet = null;
        int check=0;
        ArrayList<String> list = getStore(centerName);
        for(int i=0;i<list.size();i++){
            String test =list.get(i);
            deleteStore(test,centerName);
        }
        int ok = 0;
        try {
            String username = getCenterUsername(centerName);
            int checkPerson = deletePerson(username);
            if(checkPerson == 1){
                check = deleteUser(username);
            }
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
        return ok;
    }

    /**
     *
     * @param centername
     * @return
     */
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

    /**
     *
     * @param username
     * @param centerName
     * @return
     */
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
            Database.printMesssage(e, "setCenterManager");
        }
        finally {
            Database.closeStatement(statement);
            Database.closeResSet(resultSet);
        }
        return ok;
    }

    /**
     *
     * @param username
     * @param turnover
     * @return
     */
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