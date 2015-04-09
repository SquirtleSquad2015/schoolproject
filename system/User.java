/**
 * Created by Andreas on 09.04.2015.
 */
package system;
public class User {
    private int access_level;
    private String username;
    private String password;

    public User(int access_level, String username, String password){
        this.access_level = access_level;
        this.username = username;
        this.password = password;
    }

    int getAccess_level(){
        return access_level;
    }
    String getUsername(){
        return username;
    }
    String getPassword(){
        return password;
    }

    void setAccessLevel(int newAccessLevel){
        access_level = newAccessLevel;
    }
    void setUsername(String newUsername){
        username = newUsername;
    }
    void setPassword(String newPassword){
        password = newPassword;
    }
}
