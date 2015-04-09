/**
 * Created by Andreas on 09.04.2015.
 */
package system;
public class CustomerService extends Administration{

    public CustomerService(String username, String name, String title, String telephone, String email,int access_level){
        super(username,name,title,telephone,email,access_level);
    }



    void updateCustomerServiceCase(){

    }
    int generateCustomerServiceCase(){
        return -1; //filler
    }

    void deleteCustomerServiceCase(){

    }
}
