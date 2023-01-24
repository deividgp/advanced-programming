package part1;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * DataFrameFile implementation (DataFrame file).
 */
public class CSVDataFrame extends DataFrameFile {

    /**
     * CSVDataFrame constructor.
     * @param dataFrame dataframe data structure.
     */
    public CSVDataFrame(LinkedList<HashMap<String, Object>> dataFrame){
        super(dataFrame);
    }
}
