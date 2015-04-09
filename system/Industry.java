/**
 * Created by Andreas on 09.04.2015.
 */
package system;
public class Industry {
    private String trade;
    private String center_name;

    public Industry(String trade, String center_name){
        this.trade = trade;
        this.center_name = center_name;
    }

    String getTrade(){
        return trade;
    }
    String getCenter(){
        return center_name;
    }
    void setTrade(String nyTrade){
        trade = nyTrade;
    }
    void setCenter(String nyCenter){
        center_name = nyCenter;
    }
}
