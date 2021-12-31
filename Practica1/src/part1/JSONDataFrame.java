package part1;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONDataFrame<T> extends DataFrameAbstract<T> {

    public JSONDataFrame(HashMap<String, ArrayList<T>> dataFrame){
        super(dataFrame);
    }

}
