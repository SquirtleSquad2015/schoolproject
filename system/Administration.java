/**
 * Created by Andreas on 09.04.2015.
 */
package system;
abstract public class Administration {
   protected String username;
   protected String name;
   protected String title;
   protected String telephone;
   protected String email;
   protected int access_level;

    public Administration(String username, String name, String title, String telephone, String email,int access_level){
        this.username = username;
        this.name = name;
        this.title = title;
        this.telephone = telephone;
        this.email = email;
        this.access_level = access_level;
    }

    String getUsername(){
        return username;
    }

    String getName(){
        return name;
    }

    String getTitle(){
        return title;
    }

    String getTelephone(){
        return telephone;
    }

    String getEmail(){
        return email;
    }

    void setTitle(String newTitle){
        title = newTitle;
    }

    void setTelephone(String newTelephone){
        telephone = newTelephone;
    }

    String viewShopinfo(){
        return "shopinfo";//filler
    }

    String viewCenterInfo(){
        return "centerInfo";//filler
    }

}


