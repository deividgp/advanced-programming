package part1;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * DataFrameFile implementation (DataFrame file).
 */
public class JSONDataFrame extends DataFrameFile {

    /**
     * JSONDataFrame constructor.
     * @param dataFrame dataframe data structure.
     */
    public JSONDataFrame(LinkedList<HashMap<String, Object>> dataFrame){
        super(dataFrame);
    }

}
