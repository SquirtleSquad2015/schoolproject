/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoolprodject.DatabasePackage;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Haldor
 */
public class DatabaseConnectionTest extends DatabaseConnection{
    
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
        instance.closeConnection();
        
    }

    /**
     * Test of checkDB method, of class DatabaseConnection.
     */
    @Test
    public void testCheckDB() throws Exception {
        System.out.println("checkDB");
        DatabaseConnection instance = new DatabaseConnection();
        boolean expResult = false;
        boolean result = instance.checkDB();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getCenters method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenters() {
        System.out.println("getCenters");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getCenters(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getStore method, of class DatabaseConnection.
     */
    @Test
    public void testGetStore() {
        System.out.println("getStore");
        String centerName = "";
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getStore(centerName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getStoreAndTrade method, of class DatabaseConnection.
     */
    @Test
    public void testGetStoreAndTrade() {
        System.out.println("getStoreAndTrade");
        String centerName = "";
        String trade = "";
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getStoreAndTrade(centerName, trade);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getMuncipality method, of class DatabaseConnection.
     */
    @Test
    public void testGetMuncipality() {
        System.out.println("getMuncipality");
        String kommun = "";
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getMuncipality(kommun);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCenterMunicipality method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterMunicipality() {
        System.out.println("getCenterMunicipality");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getCenterMunicipality(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTurnoverStore method, of class DatabaseConnection.
     */
    @Test
    public void testGetTurnoverStore() {
        System.out.println("getTurnoverStore");
        String centername = "";
        String storename = "";
        DatabaseConnection instance = new DatabaseConnection();
        Integer expResult = null;
        Integer result = instance.getTurnoverStore(centername, storename);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTradeStore method, of class DatabaseConnection.
     */
    @Test
    public void testGetTradeStore() {
        System.out.println("getTradeStore");
        String Storename = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getTradeStore(Storename);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTradeCenter method, of class DatabaseConnection.
     */
    @Test
    public void testGetTradeCenter() {
        System.out.println("getTradeCenter");
        String Centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getTradeCenter(Centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getParking method, of class DatabaseConnection.
     */
    @Test
    public void testGetParking() {
        System.out.println("getParking");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getParking(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getAddress method, of class DatabaseConnection.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getAddress(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCenterManager method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterManager() {
        System.out.println("getCenterManager");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getCenterManager(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getStoreManager method, of class DatabaseConnection.
     */
    @Test
    public void testGetStoreManager() {
        System.out.println("getStoreManager");
        String centername = "";
        String storename = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getStoreManager(centername, storename);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPersonName method, of class DatabaseConnection.
     */
    @Test
    public void testGetPersonName() {
        System.out.println("getPersonName");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getPersonName(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getLocation method, of class DatabaseConnection.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        String Storename = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getLocation(Storename);
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
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getOpenings(centerName, Storename);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of RegisterCustomerQuestion method, of class DatabaseConnection.
     */
    @Test
    public void testRegisterCustomerQuestion() {
        System.out.println("RegisterCustomerQuestion");
        String center = "";
        String subject = "";
        String question = "";
        DatabaseConnection instance = new DatabaseConnection();
        boolean expResult = false;
        boolean result = instance.RegisterCustomerQuestion(center, subject, question);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getHighestCustomerCaseIndex method, of class DatabaseConnection.
     */
    @Test
    public void testGetHighestCustomerCaseIndex() {
        System.out.println("getHighestCustomerCaseIndex");
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.getHighestCustomerCaseIndex();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of checkUsername method, of class DatabaseConnection.
     */
    @Test
    public void testCheckUsername() throws Exception {
        System.out.println("checkUsername");
        String userName = "";
        DatabaseConnection instance = new DatabaseConnection();
        boolean expResult = false;
        boolean result = instance.checkUsername(userName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of regNewCenterUser method, of class DatabaseConnection.
     */
    @Test
    public void testRegNewCenterUser() {
        System.out.println("regNewCenterUser");
        String userName = "";
        String telephone = "";
        char[] password = null;
        String centerName = "";
        String realName = "";
        String mail = "";
        int userLevel = 0;
        String title = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.regNewCenterUser(userName, telephone, password, centerName, realName, mail, userLevel, title);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkLogIn method, of class DatabaseConnection.
     */
    @Test
    public void testCheckLogIn() {
        System.out.println("checkLogIn");
        String username = "";
        String password = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.checkLogIn(username, password);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of customerServiceGetTitle method, of class DatabaseConnection.
     */
    @Test
    public void testCustomerServiceGetTitle() {
        System.out.println("customerServiceGetTitle");
        String title = "";
        String center_name = "";
        char solved = ' ';
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.customerServiceGetTitle(title, center_name, solved);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCustomerAnswer method, of class DatabaseConnection.
     */
    @Test
    public void testGetCustomerAnswer() {
        System.out.println("getCustomerAnswer");
        int caseID = 0;
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getCustomerAnswer(caseID);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getCenter method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenter() {
        System.out.println("getCenter");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getCenter(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getNoOfShops method, of class DatabaseConnection.
     */
    @Test
    public void testGetNoOfShops() {
        System.out.println("getNoOfShops");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getNoOfShops(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getSQM method, of class DatabaseConnection.
     */
    @Test
    public void testGetSQM() {
        System.out.println("getSQM");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getSQM(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCenterTelephone method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterTelephone() {
        System.out.println("getCenterTelephone");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getCenterTelephone(centername);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getCenterMail method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterMail() {
        System.out.println("getCenterMail");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getCenterMail(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCenterParking method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterParking() {
        System.out.println("getCenterParking");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getCenterParking(centername);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getCenterDescription method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterDescription() {
        System.out.println("getCenterDescription");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getCenterDescription(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCustomerCaseID method, of class DatabaseConnection.
     */
    @Test
    public void testGetCustomerCaseID() {
        System.out.println("getCustomerCaseID");
        String title = "";
        String center_name = "";
        char solved = ' ';
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = instance.getCustomerCaseID(title, center_name, solved);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getDescription method, of class DatabaseConnection.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        int caseID = 0;
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getDescription(caseID);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setAnswer method, of class DatabaseConnection.
     */
    @Test
    public void testSetAnswer() {
        System.out.println("setAnswer");
        String answer = "";
        int caseID = 0;
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setAnswer(answer, caseID);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of deleteCustomerCase method, of class DatabaseConnection.
     */
    @Test
    public void testDeleteCustomerCase() {
        System.out.println("deleteCustomerCase");
        int caseID = 0;
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.deleteCustomerCase(caseID);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getEmail method, of class DatabaseConnection.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getEmail(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getPhoneNumber method, of class DatabaseConnection.
     */
    @Test
    public void testGetPhoneNumber() {
        System.out.println("getPhoneNumber");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getPhoneNumber(username);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setEmail method, of class DatabaseConnection.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setEmail(email, username);
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
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setPhoneNumber(phoneNumber, username);
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
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setCenterMail(newMail, centerName);
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
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setCenterPhoneNumber(newPhoneNumber, centerName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopName method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopName() {
        System.out.println("getShopName");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getShopName(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopTrade method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopTrade() {
        System.out.println("getShopTrade");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getShopTrade(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopLocation method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopLocation() {
        System.out.println("getShopLocation");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getShopLocation(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopFloor method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopFloor() {
        System.out.println("getShopFloor");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getShopFloor(username);
        assertEquals(expResult, result);
        
       
    }

    /**
     * Test of getShopOpeningHrs method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopOpeningHrs() {
        System.out.println("getShopOpeningHrs");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getShopOpeningHrs(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopOpeningHrsWeekends method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopOpeningHrsWeekends() {
        System.out.println("getShopOpeningHrsWeekends");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getShopOpeningHrsWeekends(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getShopTurnover method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopTurnover() {
        System.out.println("getShopTurnover");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getShopTurnover(username);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getShopDescription method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopDescription_String_String() {
        System.out.println("getShopDescription");
        String centerName = "";
        String storeName = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getShopDescription(centerName, storeName);
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
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setStoreName(username, newStoreName);
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
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setStoreLocation(username, newStoreLocation);
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
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setStoreFloor(username, newFloor);
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
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setStoreOpeningHrs(username, newOpeningHrs);
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
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setStoreOpeningHrsWeekends(username, newOpeningHrsWeekends);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTrades method, of class DatabaseConnection.
     */
    @Test
    public void testGetTrades() {
        System.out.println("getTrades");
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getTrades();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getCenterFromTrade method, of class DatabaseConnection.
     */
    @Test
    public void testGetCenterFromTrade() {
        System.out.println("getCenterFromTrade");
        String trade = "";
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getCenterFromTrade(trade);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getTradeDescription method, of class DatabaseConnection.
     */
    @Test
    public void testGetTradeDescription() {
        System.out.println("getTradeDescription");
        String trade = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getTradeDescription(trade);
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
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setTrade(username, trade);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getShopDescription method, of class DatabaseConnection.
     */
    @Test
    public void testGetShopDescription_String() {
        System.out.println("getShopDescription");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getShopDescription(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setStoreDescription method, of class DatabaseConnection.
     */
    @Test
    public void testSetStoreDescription() {
        System.out.println("setStoreDescription");
        String username = "";
        String description = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setStoreDescription(username, description);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of newCenter method, of class DatabaseConnection.
     */
    @Test
    public void testNewCenter() {
        System.out.println("newCenter");
        String na = "";
        String mu = "";
        String tu = "";
        String sh = "";
        String sq = "";
        String ad = "";
        String tl = "";
        String ma = "";
        String ca = "";
        String de = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.newCenter(na, mu, tu, sh, sq, ad, tl, ma, ca, de);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of regNewStore method, of class DatabaseConnection.
     */
    @Test
    public void testRegNewStore() {
        System.out.println("regNewStore");
        String storeName = "";
        String centerName = "";
        String trade = "";
        String location = "";
        String floor = "";
        String openingHrs = "";
        String openingHrsWeekends = "";
        String description = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.regNewStore(storeName, centerName, trade, location, floor, openingHrs, openingHrsWeekends, description);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setCenterSqm method, of class DatabaseConnection.
     */
    @Test
    public void testSetCenterSqm() {
        System.out.println("setCenterSqm");
        String newSqm = "";
        String centerName = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setCenterSqm(newSqm, centerName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCenterCarPark method, of class DatabaseConnection.
     */
    @Test
    public void testSetCenterCarPark() {
        System.out.println("setCenterCarPark");
        char carPark = ' ';
        String centerName = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setCenterCarPark(carPark, centerName);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCenterDescription method, of class DatabaseConnection.
     */
    @Test
    public void testSetCenterDescription() {
        System.out.println("setCenterDescription");
        String newDescription = "";
        String centerName = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setCenterDescription(newDescription, centerName);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getUsers method, of class DatabaseConnection.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getUsers();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getUserAccess method, of class DatabaseConnection.
     */
    @Test
    public void testGetUserAccess() {
        System.out.println("getUserAccess");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.getUserAccess(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getUserActiv method, of class DatabaseConnection.
     */
    @Test
    public void testGetUserActiv() {
        System.out.println("getUserActiv");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getUserActiv(username);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getUserTitle method, of class DatabaseConnection.
     */
    @Test
    public void testGetUserTitle() {
        System.out.println("getUserTitle");
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getUserTitle(username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setUserActiv method, of class DatabaseConnection.
     */
    @Test
    public void testSetUserActiv() {
        System.out.println("setUserActiv");
        String activ = "";
        String username = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setUserActiv(activ, username);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getStoreUsername method, of class DatabaseConnection.
     */
    @Test
    public void testGetStoreUsername() {
        System.out.println("getStoreUsername");
        String centerName = "";
        String storename = "";
        DatabaseConnection instance = new DatabaseConnection();
        String expResult = "";
        String result = instance.getStoreUsername(centerName, storename);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setNewPassword method, of class DatabaseConnection.
     */
    @Test
    public void testSetNewPassword() {
        System.out.println("setNewPassword");
        String username = "";
        String newPassword = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setNewPassword(username, newPassword);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getUsersCenterManager method, of class DatabaseConnection.
     */
    @Test
    public void testGetUsersCenterManager() {
        System.out.println("getUsersCenterManager");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getUsersCenterManager(centername);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getStoresWithoutUser method, of class DatabaseConnection.
     */
    @Test
    public void testGetStoresWithoutUser() {
        System.out.println("getStoresWithoutUser");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getStoresWithoutUser(centername);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of getUsersWithoutStore method, of class DatabaseConnection.
     */
    @Test
    public void testGetUsersWithoutStore() {
        System.out.println("getUsersWithoutStore");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getUsersWithoutStore(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getUsersNotActiv method, of class DatabaseConnection.
     */
    @Test
    public void testGetUsersNotActiv() {
        System.out.println("getUsersNotActiv");
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getUsersNotActiv(centername);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setStoreUser method, of class DatabaseConnection.
     */
    @Test
    public void testSetStoreUser() {
        System.out.println("setStoreUser");
        String username = "";
        String storename = "";
        String centername = "";
        DatabaseConnection instance = new DatabaseConnection();
        int expResult = 0;
        int result = instance.setStoreUser(username, storename, centername);
        assertEquals(expResult, result);
        
    }
    
}
