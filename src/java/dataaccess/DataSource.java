/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Shariar
 */
public class DataSource {
    
    private final static DataSource DATA_SOURCE = new DataSource();
    
    private javax.sql.DataSource ds;

    private DataSource() {
    }

    private void init() throws NamingException{
        if (ds!=null) {
            return;
        }
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        ds = (javax.sql.DataSource) envContext.lookup("jdbc/Registrar");
    }
    
    private Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
    
    /**
     * Only use one connection for this application, prevent memory leaks.
     */
    public static Connection createConnection() throws SQLException, NamingException {
        DATA_SOURCE.init();
        return DATA_SOURCE.getConnection();
    }
}
