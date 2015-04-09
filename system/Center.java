/**
 * Created by Andreas on 09.04.2015.
 */
package system;
public class Center {
    private String centre_name;
    private String muncipal;
    private String address;
    private int turnover;
    private int no_of_shops;
    private int sqm;
    private String mail_address;
    private String telephone; //evt int?
    private boolean parking_space;
    private String manager_username;

    public Center(String centre_name, String muncipal, String address, int turnover, int no_of_shops,
                  int sqm, String mail_address,String telephone, boolean parking_space,String manager_username){
        this.centre_name = centre_name;
        this.muncipal = muncipal;
        this.address = address;
        this.turnover = turnover;
        this.no_of_shops = no_of_shops;
        this.sqm = sqm;
        this.mail_address = mail_address;
        this.telephone = telephone;
        this.parking_space = parking_space;
        this.manager_username = manager_username;
    }

    public Store createStore(String manager_username,int turnover, String location,
                   int floor,String opening_hours ,String opening_hours_weekend
                   ,String name,String description,String trade){

        Store newStore = new Store(manager_username,getCentreName(), turnover,location,floor,
                opening_hours,opening_hours_weekend,name,description,trade);
        return newStore;
    }

    public Administration registerAdministration(String username, String name,
                            String title,String telephone,String email, int access_level){

        if(access_level == 0){
            Admin newAdmin = new Admin(username,name,title,telephone,email,access_level);
            return newAdmin;
        }//admin
        if(access_level == 1){
            CenterManager newCenterManager = new CenterManager(username,name,title,telephone,email,access_level);
            return newCenterManager;
        }//centermanager
        if(access_level == 2){
            StoreManager newStoreManager = new StoreManager(username,name,title,telephone,email,access_level);
            return newStoreManager;
        }//storemanager
        if(access_level == 3){
            CustomerService newCustomerService = new CustomerService(username,name,title,telephone,email,access_level);
            return newCustomerService;
        }//customerService
        return null;
    }

    String getCentreName(){
        return centre_name;
    }
    String getMuncipal(){
        return muncipal;
    }
    String getAddress(){
        return address;
    }
    int getTurnover(){
        return turnover;
    }
    int getNoOfShops(){
        return no_of_shops;
    }
    int getSqm(){
        return sqm;
    }
    String getMailAddress(){
        return mail_address;
    }
    String getTelephone(){
        return telephone;
    }
    boolean getParkingSpace(){
        return parking_space;
    }
    void setNoOfShops(int value){
        no_of_shops = value;
    }
    void setSqm(int value){
        sqm = value;
    }
    void setMailAdress(String adress){
        mail_address = adress;
    }
    void SetTelephone(String value){
        telephone = value;
    }
    void setParkingSpace(boolean value){
        parking_space = value;
    }


}
