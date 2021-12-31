package part1;

import java.util.ArrayList;
import java.util.HashMap;

public class CSVDataFrame<T> extends DataFrameAbstract<T> {

    public CSVDataFrame(HashMap<String, ArrayList<T>> dataFrame){
        super(dataFrame);
    }


}
