import java.sql.*;
import javax.swing.*;

class Register{
    
    public void update(String oppdattering) throws Exception{
        String databaseDriver = "org.apache.derby.jdbc.ClientDriver";
        Class.forName(databaseDriver);
        String databaseName = "jdbc:derby://localhost:1527/main;user=main";
        Connection connection = DriverManager.getConnection(databaseName);
        
        Statement statement = connection.createStatement();
        ResultSet forbindelse = statement.executeQuery("SELECT * FROM manager");
        Statement setning = connection.createStatement();
        
        setning.executeUpdate(oppdattering);
        
        System.out.println("Oppdattering:   " + oppdattering);
        setning.close();
        forbindelse.close();
        
    }
    
    
}