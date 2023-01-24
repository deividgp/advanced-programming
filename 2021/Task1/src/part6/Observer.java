package part6;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer abstract class to track operations.
 */
public abstract class Observer {
    List<String> logger;

    /**
     * Observer constructor.
     */
    public Observer(){
        logger = new ArrayList<>();
    }

    /**
     * Adds a new log.
     * @param value Log register.
     */
    public void update(String value){
        logger.add(value);
    }

    /**
     * Logger getter.
     * @return List of String.
     */
    public List<String> getLogger() {
        return logger;
    }
}
