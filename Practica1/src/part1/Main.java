package part1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");

        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame dataJSON = jsonFactory.createDataFrame("cities.json", "");
    }
}
