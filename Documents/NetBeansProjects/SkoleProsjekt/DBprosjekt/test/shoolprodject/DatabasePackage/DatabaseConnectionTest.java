/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoolprodject.DatabasePackage;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseConnectionTest extends DatabaseConnection {

    
    public DatabaseConnectionTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {


    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {

        try {
            openConnection();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
           
            closeConnection();
        } catch (Exception ex) {
            Logger.getLogger(DatabaseConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of openConnection method, of class DatabaseConnection.
     */
    @Test
    public void testOpenConnection() throws Exception {
        System.out.println("openConnection");
        DatabaseConnection instance = new DatabaseConnection();
        instance.openConnection();
       
    }

    /**
     * Test of closeConnection method, of class DatabaseConnection.
     */
    @Test
    public void testCloseConnection() throws Exception {
        System.out.println("closeConnection");
        DatabaseConnection instance = new DatabaseConnection();
        closeConnection();
        
    }

    /**
     * Test of checkDB method, of class DatabaseConnection.
     */
    @Test
    public void testCheckDB() throws Exception {
        System.out.println("checkDB");
        boolean expResult = false;
        boolean result = checkDB();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of newCenter method, of class DatabaseConnection.
     * Method is functional, but JUnittest execute order is not given by
     * order of code. DummyCenter is already in database.
     */
    /*@Test
    public void testNewCenter() {
        System.out.println("newCenter");
        String na = "DummyCenter";
        String mu = "DummyMunicipality";
        String tu = "22";
        String sh = "1";
        String sq = "1000";
        String ad = "DummyAddress";
        String tl = "48292211";
        String ma = "dumy@center.no";
        String ca = "y";
        String de = "Dumt center";
        int expResult = 1;
        int result = newCenter(na, mu, tu, sh, sq, ad, tl, ma, ca, de);
        assertEquals(expResult, result);

    }*/
    /**
     * Test of regNewStore method, of class DatabaseConnection.
     * In setup class, test will not run properly if this fails there.
     */
    /*@Test
    public void testRegNewStore() {
        System.out.println("regNewStore");
        String storeName = "DummyStore";
        String centerName = "DummyCenter";
        String trade = "Elektronikk";
        String location = "DummyLocation";
        String floor = "2";
        String openingHrs = "DumOpeninghrs";
        String openingHrsWeekends = "EvenDumberOpeninghrs";
        String description = "DummyStore";
        int expResult = 1;
        int result = regNewStore(storeName, centerName, trade, location, floor, openingHrs, openingHrsWeekends, description);
        assertEquals(expResult, result);
    }*/
    /**
     * Test of regNewStoreManager method in databaseConnection.
     * Method is functional, but commented out for test purposes,
     * as values are already existing in database, and JUnit execution
     * order is not given.
     */
    /*@Test
    public void testRegNewStoreManager() {
        System.out.println("regNewCenterUser");
        String userName = "DummyStoreManager";
        String telephone = "87654321";
        char[] password = {'D','u','m','m','y', 'S'};
        String centerName = "DummyCenter";
        String realName = "Dummy Store Manager";
        String mail = "Dummy@Store.com";
        int userLevel = 2;
        String title = "Store Manager";
        int expResult = 1;
        int result = regNewCenterUser(userName, telephone, password, centerName, realName, mail, userLevel, title);
        assertEquals(expResult, result);

    }*/

    /**
     * Test of regNewCenterManager method in databaseConnection.
     * Method is functional, but commented out for test purposes,
     * as values are already existing in database, and JUnit execution
     * order is not given.
     */
    /*@Test
    public void testRegNewCenterManager() {
        System.out.println("regNewCenterUser");
        String userName = "DummyCenterManager";
        String telephone = "12345678";
        char[] password = {'D','u','m','m','y', 'C'};
        String centerName = "DummyCenter";
        String realName = "Dummy Center Manager";
        String mail = "Dummy@Center.com";
        int userLevel = 3;
        String title = "Center Manager";
        int expResult = 1;
        int result = regNewCenterUser(userName, telephone, password, centerName, realName, mail, userLevel, title);
        assertEquals(expResult, result);

    }*/
    /**
     * Test of setStoreUser method, of class DatabaseConnection.
     */
    @Test
    public void testSetStoreUser() {
        System.out.println("setStoreUser");
        String username = "DummyStoreManager";
        String storename = "DummyStore";
        String centername = "DummyCenter";
        int expResult = 1;
        int result = setStoreUser(username, storename, centername);
        assertEquals(expResult, result);

    }


    /**
     * Test of RegisterCustomerQuestion method, of class DatabaseConnection.
     */
    @Test
    public void testRegisterCustomerQuestion() {
        System.out.println("RegisterCustomerQuestion");
        String center = "DummyCenter";
        String subject = "DummySubject";
        String question = "DummyQuestion";
        boolean expResult = true;
        boolean result = RegisterCustomerQuestion(center, subject, question);
        assertEquals(expResult, result);
    }


    /**
     * Test of getCenters method, of class DatabaseConnection.
     * Returns a list of all registered centers. Method is working,
     * but setting expected result for each test run is redundant,
     * because of the number of centers involved.
     */
    /*@Test
    public void testGetCenters() {
        System.out.println("getCenters");
        String centername = "DummyCenter";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("DummyCenter");
        ArrayList<String> result = getCenters(centername);
        assertEquals(expResult, result);
    }*/

    /**
     * Test of getStore method, of class DatabaseConnection.
     */
    @Test
    public void testGetStore() {
        System.out.println("getStore");
        String centerName = "DummyCenter";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("DummyStore");
        ArrayList<String> result = getStore(centerName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getStoreAndTrade method, of class DatabaseConnection.
     */
    @Test
    public void testGetStoreAndTrade() {
        System.out.println("getStoreAndTrade");
        String centerName = "DummyCenter";
        String trade = "Elektronikk";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("DummyStore");
        ArrayList<String> result = getStoreAndTrade(centerName, trade);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getMuncipality method, of class DatabaseConnection.
     */
    @Test
    public void testGetMuncipality() {
        System.out.println("getMuncipality");
        String kommun = "DummyMunicipality";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("DummyCenter");
        ArrayList<String> result = getMuncipality(kommun);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCenterMunicipality method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterMunicipality() {
        System.out.println("getCenterMunicipality");
        String centername = "DummyCenter";
        String expResult = "DummyMunicipality";
        String result = getCenterMunicipality(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTurnoverStore method, of class DatabaseConnection.
     */
    @Test
    public void testGetTurnoverStore() {
        System.out.println("getTurnoverStore");
        String centername = "DummyCenter";
        String storename = "DummyStore";
        Integer expResult = 3;
        Integer result = getTurnoverStore(centername, storename);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTradeStore method, of class DatabaseConnection.
     */
    @Test
    public void testGetTradeStore() {
        System.out.println("getTradeStore");
        String Storename = "DummyStore";
        String expResult = "Elektronikk";
        String result = getTradeStore(Storename);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTradeCenter method, of class DatabaseConnection.
     */
    @Test
    public void testGetTradeCenter() {
        System.out.println("getTradeCenter");
        String Centername = "DummyCenter";
        String expResult = "No trades found in center";
        String result = getTradeCenter(Centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getParking method, of class DatabaseConnection.
     */
    @Test
    public void testGetParking() {
        System.out.println("getParking");
        String centername = "DummyCenter";
        String expResult = "y";
        String result = getParking(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getAddress method, of class DatabaseConnection.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String centername = "DummyCenter";
        String expResult = "DummyAddress, DummyMunicipality";
        String result = getAddress(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCenterManager method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterManager() {
        System.out.println("getCenterManager");
        String centername = "DummyCenter";
        String expResult = "Dummy Center Manager";
        String result = getCenterManager(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getStoreManager method, of class DatabaseConnection.
     */
    @Test
    public void testGetStoreManager() {
        System.out.println("getStoreManager");
        String centername = "DummyCenter";
        String storename = "DummyStore";
        String expResult = "Dummy Store Manager";
        String result = getStoreManager(centername, storename);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPersonName method, of class DatabaseConnection.
     */
    @Test
    public void testGetPersonName() {
        System.out.println("getPersonName");
        String username = "DummyCenterManager";
        String expResult = "Dummy Center Manager";
        String result = getPersonName(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getLocation method, of class DatabaseConnection.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        String Storename = "";
        String expResult = "No location found";
        String result = getLocation(Storename);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getOpenings method, of class DatabaseConnection.
     */
    @Test
    public void testGetOpenings() {
        System.out.println("getOpenings");
        String centerName = "";
        String Storename = "";
        String expResult = "No opening hours found";
        String result = getOpenings(centerName, Storename);
        assertEquals(expResult, result);
       
    }



    public void testGetHighestCustomerCaseIndexInt() {
        System.out.println("getHighestCustomerCaseIndex");
        int highestCustomerCaseIndex = getHighestCustomerCaseIndex();
        System.out.println(highestCustomerCaseIndex);
        //return highestCustomerCaseIndex;
    }



    /**
     * Test of getHighestCustomerCaseIndex method, of class DatabaseConnection.
     * Method is operational, but customer case PK is an incrementing value,
     * and setting this for every test is redundant.
     */
    /*@Test
    public void testGetHighestCustomerCaseIndex() {
        System.out.println("getHighestCustomerCaseIndex");
        int expResult = highestCustomerCaseIndex;
        int result = getHighestCustomerCaseIndex();
        assertEquals(expResult, result);
       
    }*/

    /**
     * Test of checkUsername method, of class DatabaseConnection.
     */
    @Test
    public void testCheckUsername() throws Exception {
        System.out.println("checkUsername");
        String userName = "DummyStoreManager";
        boolean expResult = true;
        boolean result = checkUsername(userName);
        assertEquals(expResult, result);
        
    }


    /**
     * Test of checkLogIn method, of class DatabaseConnection.
     */
    @Test
    public void testCheckLogIn() {
        System.out.println("checkLogIn");
        String username = "DummyStoreManager";
        String password = "DummySS";
        int expResult = 2;
        int result = checkLogIn(username, password);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of customerServiceGetTitle method, of class DatabaseConnection.
     * Method is functional, but customer service PK is an incrementing value,
     * and setting this for every test run is redundant.
     */
   /* @Test
    public void testCustomerServiceGetTitle() {
        System.out.println("customerServiceGetTitle");
        String title = "DummySubject";
        String center_name = "DummyCenter";
        char solved = 'n';
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("DummySubject");
        ArrayList<String> result = customerServiceGetTitle(title, center_name, solved);
        assertEquals(expResult, result);
        
    }*/

    /**
     * Test of getCustomerAnswer method, of class DatabaseConnection.
     * Method is functional, but caseID is an incremental value,
     * and setting this for every test run is redundant.
     */
    /*@Test
    public void testGetCustomerAnswer() {
        System.out.println("getCustomerAnswer");
        int caseID = highestCustomerCaseIndex;
        String expResult = "";
        String result = getCustomerAnswer(caseID);
        assertEquals(expResult, result);
       
    }*/

    /**
     * Test of getCenter method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenter() {
        System.out.println("getCenter");
        String username = "DummyCenterManager";
        String expResult = "DummyCenter";
        String result = getCenter(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getNoOfShops method, of class DatabaseConnection.
     */
    @Test
    public void testGetNoOfShops() {
        System.out.println("getNoOfShops");
        String centername = "DummyCenter";
        String expResult = "3";
        String result = getNoOfShops(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getSQM method, of class DatabaseConnection.
     */
    @Test
    public void testGetSQM() {
        System.out.println("getSQM");
        String centername = "DummyCenter";
        String expResult = "450";
        String result = getSQM(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCenterTelephone method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterTelephone() {
        System.out.println("getCenterTelephone");
        String centername = "DummyCenter";
        String expResult = "48292211";
        String result = getCenterTelephone(centername);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getCenterMail method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterMail() {
        System.out.println("getCenterMail");
        String centername = "DummyCenter";
        String expResult = "dumy@center.no";
        String result = getCenterMail(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCenterParking method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterParking() {
        System.out.println("getCenterParking");
        String centername = "DummyCenter";
        String expResult = "y";
        String result = getCenterParking(centername);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getCenterDescription method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterDescription() {
        System.out.println("getCenterDescription");
        String centername = "DummyCenter";
        String expResult = "Dumt center";
        String result = getCenterDescription(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCustomerCaseID method, of class DatabaseConnection.
     * Method is functional, but caseID is an incrementing value,
     * and setting expectd result for every test run is redundant.
     */
   /* @Test
    public void testGetCustomerCaseID() {

        System.out.println("getCustomerCaseID");
        String title = "DummySubject";
        String center_name = "DummyCenter";
        char solved = 'n';
        ArrayList<Integer> expResult = new ArrayList<Integer>();
        System.out.println(highestCustomerCaseIndex);
        expResult.add(highestCustomerCaseIndex);
        ArrayList<Integer> result = getCustomerCaseID(title, center_name, solved);
        assertEquals(expResult, result);
        
    }*/

    /**
     * Test of getDescription method, of class DatabaseConnection.
     * Method is functional, but caseID is an incrementing value,
     * and setting expected result for every test run is redundant.
     */
    /*@Test
    public void testGetDescription() {
        System.out.println("getDescription");
        int caseID = 0;
        String expResult = "";
        String result = getDescription(caseID);
        assertEquals(expResult, result);
        
    }*/

    /**
     * Test of setAnswer method, of class DatabaseConnection.
     */
    @Test
    public void testSetAnswer() {
        System.out.println("setAnswer");
        String answer = "";
        int caseID = 0;
        int expResult = 0;
        int result = setAnswer(answer, caseID);
        assertEquals(expResult, result);
        
    }


    /**
     * Test of getEmail method, of class DatabaseConnection.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String username = "DummyStoreManager";
        String expResult = "Dummy@Store.com";
        String result = getEmail(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPhoneNumber method, of class DatabaseConnection.
     */
    @Test
    public void testGetPhoneNumber() {
        System.out.println("getPhoneNumber");
        String username = "";
        String expResult = "";
        String result = getPhoneNumber(username);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setEmail method, of class DatabaseConnection.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "TestMailen";
        String username = "Admin";
        int expResult = 1;
        int result = setEmail(email, username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setPhoneNumber method, of class DatabaseConnection.
     */
    @Test
    public void testSetPhoneNumber() {
        System.out.println("setPhoneNumber");
        String phoneNumber = "";
        String username = "";
        int expResult = 0;
        int result = setPhoneNumber(phoneNumber, username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCenterMail method, of class DatabaseConnection.
     */
    @Test
    public void testSetCenterMail() {
        System.out.println("setCenterMail");
        String newMail = "";
        String centerName = "";
        int expResult = 0;
        int result = setCenterMail(newMail, centerName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCenterPhoneNumber method, of class DatabaseConnection.
     */
    @Test
    public void testSetCenterPhoneNumber() {
        System.out.println("setCenterPhoneNumber");
        String newPhoneNumber = "";
        String centerName = "";
        int expResult = 0;
        int result = setCenterPhoneNumber(newPhoneNumber, centerName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopName method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopName() {
        System.out.println("getShopName");
        String username = "";
        String expResult = "";
        String result = getShopName(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopTrade method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopTrade() {
        System.out.println("getShopTrade");
        String username = "";
        String expResult = "";
        String result = getShopTrade(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopLocation method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopLocation() {
        System.out.println("getShopLocation");
        String username = "";
        String expResult = "";
        String result = getShopLocation(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopFloor method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopFloor() {
        System.out.println("getShopFloor");
        String username = "";
        String expResult = "";
        String result = getShopFloor(username);
        assertEquals(expResult, result);
        
       
    }

    /**
     * Test of getShopOpeningHrs method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopOpeningHrs() {
        System.out.println("getShopOpeningHrs");
        String username = "";
        String expResult = "";
        String result = getShopOpeningHrs(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopOpeningHrsWeekends method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopOpeningHrsWeekends() {
        System.out.println("getShopOpeningHrsWeekends");
        String username = "";
        String expResult = "";
        String result = getShopOpeningHrsWeekends(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopTurnover method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopTurnover() {
        System.out.println("getShopTurnover");
        String username = "";
        String expResult = "";
        String result = getShopTurnover(username);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getShopDescription method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopDescription_String_String() {
        System.out.println("getShopDescription");
        String centerName = "DummyCenter";
        String storeName = "DummyStore";
        String expResult = "DummyStoreDescription";
        String result = getShopDescription(centerName, storeName);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setStoreName method, of class DatabaseConnection.
     */
    @Test
    public void testSetStoreName() {
        System.out.println("setStoreName");
        String username = "";
        String newStoreName = "";
        int expResult = 0;
        int result = setStoreName(username, newStoreName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setStoreLocation method, of class DatabaseConnection.
     */
    @Test
    public void testSetStoreLocation() {
        System.out.println("setStoreLocation");
        String username = "";
        String newStoreLocation = "";

        int expResult = 0;
        int result = setStoreLocation(username, newStoreLocation);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setStoreFloor method, of class DatabaseConnection.
     */
    @Test
    public void testSetStoreFloor() {
        System.out.println("setStoreFloor");
        String username = "";
        int newFloor = 0;

        int expResult = 0;
        int result = setStoreFloor(username, newFloor);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setStoreOpeningHrs method, of class DatabaseConnection.
     */
    @Test
    public void testSetStoreOpeningHrs() {
        System.out.println("setStoreOpeningHrs");
        String username = "";
        String newOpeningHrs = "";
        int expResult = 0;
        int result = setStoreOpeningHrs(username, newOpeningHrs);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setStoreOpeningHrsWeekends method, of class DatabaseConnection.
     */
    @Test
    public void testSetStoreOpeningHrsWeekends() {
        System.out.println("setStoreOpeningHrsWeekends");
        String username = "";
        String newOpeningHrsWeekends = "";
        int expResult = 0;
        int result = setStoreOpeningHrsWeekends(username, newOpeningHrsWeekends);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTrades method, of class DatabaseConnection.
     */
    @Test
    public void testGetTrades() {
        System.out.println("getTrades");
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("Bokhandel");
        expResult.add("Dagligvare");
        expResult.add("Elektronikk");
        expResult.add("Frisør");
        expResult.add("Helse");
        expResult.add("Klær");
        expResult.add("mat & drikke");

        ArrayList<String> result = getTrades();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCenterFromTrade method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterFromTrade() {
        System.out.println("getCenterFromTrade");
        String trade = "";
        ArrayList<String> expResult = new ArrayList<String>();
        ArrayList<String> result = getCenterFromTrade(trade);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getTradeDescription method, of class DatabaseConnection.
     */
    @Test
    public void testGetTradeDescription() {
        System.out.println("getTradeDescription");
        String trade = "";
        String expResult = "";
        String result = getTradeDescription(trade);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setTrade method, of class DatabaseConnection.
     */
    @Test
    public void testSetTrade() {
        System.out.println("setTrade");
        String username = "";
        String trade = "";
        int expResult = 0;
        int result = setTrade(username, trade);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getShopDescription method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopDescription_String() {
        System.out.println("getShopDescription");
        String username = "DummyStoreManager";
        String expResult = "DummyStoreDescription";
        String result = getShopDescription(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setStoreDescription method, of class DatabaseConnection.
     */
    @Test
    public void testSetStoreDescription() {
        System.out.println("setStoreDescription");
        String username = "DummyStoreManager";
        String description = "DummyStoreDescription";
        int expResult = 1;
        int result = setStoreDescription(username, description);
        assertEquals(expResult, result);
        
    }




    /**
     * Test of setCenterSqm method, of class DatabaseConnection.
     */
    @Test
    public void testSetCenterSqm() {
        System.out.println("setCenterSqm");
        String newSqm = "450";
        String centerName = "DummyCenter";
        int expResult = 1;
        int result = setCenterSqm(newSqm, centerName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCenterCarPark method, of class DatabaseConnection.
     */
    @Test
    public void testSetCenterCarPark() {
        System.out.println("setCenterCarPark");
        char carPark = 'y';
        String centerName = "DummyCenter";
        int expResult = 1;
        int result = setCenterCarPark(carPark, centerName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCenterDescription method, of class DatabaseConnection.
     */
    @Test
    public void testSetCenterDescription() {
        System.out.println("setCenterDescription");
        String newDescription = "Dumt center";
        String centerName = "DummyCenter";
        int expResult = 1;
        int result = setCenterDescription(newDescription, centerName);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getUsers method, of class DatabaseConnection.
     * Returns all users and is working, but if not testing purely on a test DB
     * Writing expected result everytime a new user is added is not worth the effort.
     */
    /*@Test
    public void testGetUsers() {
        System.out.println("getUsers");
        ArrayList<String> expResult = null;
        ArrayList<String> result = getUsers();
        assertEquals(expResult, result);
        
    }*/

    /**
     * Test of getUserAccess method, of class DatabaseConnection.
     */
    @Test
    public void testGetUserAccess() {
        System.out.println("getUserAccess");
        String username = "DummyStoreManager";
        int expResult = 2;
        int result = getUserAccess(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getUserActiv method, of class DatabaseConnection.
     */
    @Test
    public void testGetUserActiv() {
        System.out.println("getUserActiv");
        String username = "DummyStoreManager";
        String expResult = "n";
        String result = getUserActiv(username);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getUserTitle method, of class DatabaseConnection.
     */
    @Test
    public void testGetUserTitle() {
        System.out.println("getUserTitle");
        String username = "";
        String expResult = "";
        String result = getUserTitle(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserActiv method, of class DatabaseConnection.
     */
    @Test
    public void testSetUserActiv() {
        System.out.println("setUserActiv");
        String activ = "n";
        String username = "DummyStoreManager";
        int expResult = 1;
        int result = setUserActiv(activ, username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getStoreUsername method, of class DatabaseConnection.
     */
    @Test
    public void testGetStoreUsername() {
        System.out.println("getStoreUsername");
        String centerName = "DummyCenter";
        String storename = "DummyStore";
        String expResult = "DummyStoreManager";
        String result = getStoreUsername(centerName, storename);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setNewPassword method, of class DatabaseConnection.
     */
    @Test
    public void testSetNewPassword() {
        System.out.println("setNewPassword");
        String username = "DummyStoreManager";
        String newPassword = "DummySS";
        int expResult = 1;
        int result = setNewPassword(username, newPassword);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getUsersCenterManager method, of class DatabaseConnection.
     */
    @Test
    public void testGetUsersCenterManager() {
        System.out.println("getUsersCenterManager");
        String centername = "DummyCenter";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("DummyCenterManager");
        expResult.add("DummyStoreManager");
        ArrayList<String> result = getUsersCenterManager(centername);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getStoresWithoutUser method, of class DatabaseConnection.
     */
    @Test
    public void testGetStoresWithoutUser() {
        System.out.println("getStoresWithoutUser");
        String centername = "DummyCenter";
        ArrayList<String> expResult = new ArrayList<String>();
        ArrayList<String> result = getStoresWithoutUser(centername);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of getUsersWithoutStore method, of class DatabaseConnection.
     */
    @Test
    public void testGetUsersWithoutStore() {
        System.out.println("getUsersWithoutStore");
        String centername = "DummyCenter";
        ArrayList<String> expResult = new ArrayList<String>();
        ArrayList<String> result = getUsersWithoutStore(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getUsersNotActiv method, of class DatabaseConnection.
     */
    @Test
    public void testGetUsersNotActiv() {
        System.out.println("getUsersNotActiv");
        String centername = "DummyCenter";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("DummyCenterManager");
        expResult.add("DummyStoreManager");

        ArrayList<String> result = getUsersNotActiv(centername);
        assertEquals(expResult, result);
    }


   /* *//**
     * Test of deleteCustomerCase method, of class DatabaseConnection.
     * Method works, but caseID is an incrementing value and hardcoding the
     * value every test run is redundant.
     *//*
    @Test
    public void testDeleteCustomerCase() {
        System.out.println("deleteCustomerCase");
        int caseID = highestCustomerCaseIndex;
        int expResult = 1;
        int result = deleteCustomerCase(caseID);
        assertEquals(expResult, result);

    }*/

   /* @Test
    public void testDeleteStorePerson() {
        System.out.println("deletePerson");
        String username = "DummyStoreManager";
        int expResult = 1;
        int result = deletePerson(username);
        assertEquals(expResult, result);
    }
    @Test
    public void testDeleteCenterPerson() {
        System.out.println("deletePerson");
        String username = "DummyCenterManager";
        int expResult = 1;
        int result = deletePerson(username);
        assertEquals(expResult, result);
    }*/

    /**
     * Test of deleteUser method, of class DatabaseConnection.
     * Commented out for test purposes.
     */
   /* @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String username = "DummyStoreManager";
        int expResult = 1;
        int result = deleteUser(username);
        assertEquals(expResult, result);
    }
    @Test
    public void testDeleteCenterUser() {
        System.out.println("deleteUser");
        String username = "DummyCenterManager";
        int expResult = 1;
        int result = deleteUser(username);
        assertEquals(expResult, result);
    }*/


   /* @Test
   Method works, but is commented out for test purposes.
    public void testDeleteStore() {
        System.out.println("deleteStore");
        String storeName = "DummyStore";
        String centerName = "DummyCenter";
        int expResult = 1;
        int result = deleteStore(storeName, centerName);
        assertEquals(expResult, result);
    }*/

    /**
     * Test of deleteCenter method, of class DatabaseConnection.
     * Commented for test purposes.
     */
    /*@Test
    public void testDeleteCenter() {
        System.out.println("deleteCenter");
        String centerName = "DummyCenter";
        int expResult = 1;
        int result = deleteCenter(centerName);
        assertEquals(expResult, result);
    }*/
    
}
