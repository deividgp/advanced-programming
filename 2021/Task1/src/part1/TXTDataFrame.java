package part1;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * DataFrameFile implementation (DataFrame file).
 */
public class TXTDataFrame extends DataFrameFile {

    /**
     * TXTDataFrame constructor.
     * @param dataFrame dataframe data structure.
     */
    public TXTDataFrame(LinkedList<HashMap<String, Object>> dataFrame){
        super(dataFrame);
    }

}