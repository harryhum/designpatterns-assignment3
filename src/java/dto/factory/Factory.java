package dto.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * generic interface guide to be used for all implementations of factory classes.</br>
 * when creating an instance of Factory object store it as this interface.</br>
 * add more functions if needed. helper functions inside of the implementation class does not be defined here.</br>
 * </p>
 *
 * @author Shawn
 * @param <T> - Type of DTO to be created in this factory
 */
public interface Factory< T> {

    /**
     * this method creates a DTO from the given {@link ResultSet}.
     *
     * @param rs - SQL server courser
     * @return created DTO object, null if failed
     * @throws SQLException - something gone wrong with reading the results
     */
    T createFromResultSet(ResultSet rs) throws SQLException;

    /**
     * this method creates a {@link List} of DTOs from the given {@link ResultSet}.
     * this method can simply call {@link Factory#createFromResultSet(java.sql.ResultSet)} multiple times.
     *
     * @param rs - SQL server courser
     * @return list of created DTOs, if none {@link Collections#emptyList()} is returned
     * @throws SQLException - something gone wrong with reading the results
     */
    List<T> createListFromResultSet(ResultSet rs) throws SQLException;
    
    /**
     * this method creates a DTO from the given {@link Map}.
     *
     * @param map - DTO values are stored in map values while keys represent the variable
     * @return created DTO object, null if failed
     */
    T createFromMap(Map<String, int[]> map);
}
