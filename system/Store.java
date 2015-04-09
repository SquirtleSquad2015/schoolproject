/**
 * Created by Andreas on 09.04.2015.
 */
package system;
public class Store {
    private String manager_username;
    private String center_name;
    private int turnover;
    private String location;
    private int floor;
    private String opening_hours;
    private String opening_hours_weekend;
    private String name;
    private String description;
    private String trade;

    public Store(String manager_username, String center_name, int turnover, String location,
                 int floor, String opening_hours, String opening_hours_weekend, String name,
                 String description, String trade){
        this.manager_username = manager_username;
        this.center_name = center_name;
        this.turnover = turnover;
        this.location = location;
        this.floor = floor;
        this.opening_hours = opening_hours;
        this.opening_hours_weekend = opening_hours_weekend;
        this.name = name;
        this.description = description;
        this.trade = trade;
    }

    String getStoreName(){
        return name;
    }
    String getManagerUsername(){
        return manager_username;
    }
    String getAddress(){
        return "location: " + location + ". Floor: " + floor;
        //evt center.getAdress elns?
    }
    int getTurnover(){
        return turnover;
    }
    String getTelephone(){
        return "";
        //return manager.getTelephone?
    }
    String getCenterName(){
        return center_name;
    }
    String getLocation(){
        return location;
    }
    int getFloor(){
        return floor;
    }
    String getOpeningHours(){
        return opening_hours;
    }
    String getOpeningHoursWeekend(){
        return opening_hours_weekend;
    }
    String getName(){
        return name;
    }
    String getTrade(){
        return trade;
    }
    void setManagerUsername(String newManagerUsername){
        manager_username = newManagerUsername;
    }
    void setTurnover(int value){
        turnover = value;
    }
    void setLocation(String newLocation){
        location = newLocation;
    }
    void setFloor(int value){
        floor = value;
    }
    void setOpeningHours(String newOpeningHours){
        opening_hours = newOpeningHours;
    }
    void setDescription(String newDescription){
        description = newDescription;
    }
    void setOpeningHoursWeekend(String newOpeningHoursWeekend){
        opening_hours_weekend = newOpeningHoursWeekend;
    }
    void setName(String newName){
        name = newName;
    }

}
