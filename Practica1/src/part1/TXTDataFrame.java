package part1;

import java.util.ArrayList;
import java.util.HashMap;

public class TXTDataFrame<T> extends DataFrameAbstract<T> {

    public TXTDataFrame(HashMap<String, ArrayList<T>> dataFrame){
        super(dataFrame);
    }


}