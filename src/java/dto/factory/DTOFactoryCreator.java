package dto.factory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Shariar
 */
public final class DTOFactoryCreator {

    private static final String PACKAGE = "dto.factory.";
    private static final String FACTORY = "Factory";

    private DTOFactoryCreator() {
    }

    @SuppressWarnings("unchecked")
    public static <T> Factory<T> createFactory(String factoryName) {
        Factory<T> factory = null;
        try {
            factory = (Factory< T>) Class.forName(PACKAGE + factoryName + FACTORY).newInstance();
        } catch (Exception ex) {
            Logger.getLogger(DTOFactoryCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return factory;
    }

    public static <T> Factory<T> createFactory(Class<T> type) {
        return createFactory(type.getSimpleName());
    }
}
