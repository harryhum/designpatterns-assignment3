
package dto.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of Factory interface
 * 
 * @author Harry Hum
 */
public abstract class AbstractFactory<T> implements Factory<T> {
    
    /**
     * Creates a list of generic objects from a result set
     * @param rs
     * @return list of generic objects
     * @throws SQLException 
     */
    @Override
    public List<T> createListFromResultSet(ResultSet rs) throws SQLException {
        List<T> list = new ArrayList<>();
        while (rs != null && rs.next()) {
            list.add(createFromResultSet(rs));
        }
        return list;
    }
    
}
