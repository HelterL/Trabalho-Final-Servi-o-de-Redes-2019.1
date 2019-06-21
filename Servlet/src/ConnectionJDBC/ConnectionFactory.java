package ConnectionJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private final String ip = "localhost"; 
	private final int port = 5432;
	private final String user = "postgres";
	private final String password = "123";
	private final String database = "CarteiraVirtual";
    
	public Connection getConnection() throws ClassNotFoundException {
        try {
        	Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://"+ip+":"+port+"/"+database, user, password); 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
    }

