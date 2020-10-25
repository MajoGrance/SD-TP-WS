package py.una.pol.moviles.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bd {

    
    private static final String url = "jdbc:postgresql://localhost/sd";
    private static final String user = "postgres";
    private static final String password = "postgres";
 
    /**
     * @return object Connection 
     */
    public static Connection connect() throws SQLException {
    	System.out.println(url);
        return DriverManager.getConnection(url, user, password);
    }

    

}
