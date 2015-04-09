/**
 * Created by Andreas on 09.04.2015.
 */
package system;
public class CenterManager extends Administration {

    public CenterManager(String username, String name, String title, String telephone, String email,int access_level){
        super(username,name,title,telephone,email,access_level);
    }

    String viewAdminInfo(){
        return "AdminInfo";//filler
    }
    void updateCenterInfo(){

    }
    void deleteCenterInfo(){

    }
    void generateShop(){

    }
    void deleteShop(){

    }
    void generateCenter(){

    }
}
