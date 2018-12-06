package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Singleton DataSource SQL connection class
 * 
 * @author Harry Hum
 */
public class DataSource {
    
    /**
     * Static constant for DataSource class object
     */
    private final static DataSource DATA_SOURCE = new DataSource();
    
    /**
     * SQL DataSource object
     */
    private javax.sql.DataSource ds;

    /**
     * Default constructor
     */
    private DataSource() {}

    /**
     * Initialize SQL DataSource
     * @throws NamingException 
     */
    private void init() throws NamingException{
        if (ds != null) {
            return;
        }
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        ds = (javax.sql.DataSource) envContext.lookup("jdbc/Registrar");
    }
    
    /**
     * Get connection from SQL DataSource
     * @return Connection from SQL DataSource object
     * @throws SQLException 
     */
    private Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
    
    /**
     * Initialize DataSource class
     * Only use one connection for this application, prevent memory leaks
     * @return the connection from SQL DataSource object
     */
    public static Connection createConnection() throws SQLException, NamingException {
        DATA_SOURCE.init();
        return DATA_SOURCE.getConnection();
    }
}
