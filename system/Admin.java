/**
 * Created by Andreas on 09.04.2015.
 */
package system;
public class Admin extends Administration {


    public Admin(String username, String name, String title, String telephone, String email,int access_level){
        super(username,name,title,telephone,email,access_level);
    }

    void generateCenter(){

    }
    void generateStore(){

    }

    String viewAdminInfo(){
    return "adminInfo";//filler
    }

    void updateCenterInfo(){

    }

    void deleteCenterInfo(){

    }

    void generateShop(){

    }

    void deleteShop(){

    }
}
