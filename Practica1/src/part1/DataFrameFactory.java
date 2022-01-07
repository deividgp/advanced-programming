package part1;

import java.util.Arrays;

public abstract class DataFrameFactory {
    public DataFrameFactory(){

    }
    public abstract DataFrameAbstract createDataFrame(String filename, String format);

    public Object transformString(Object value){
        try {
            return Double.parseDouble((String)value);
        } catch (NumberFormatException ex) {
            return value;
        }
    }

    public String removeChars(String value){
        return value.replace(" ", "").replace("\"", "");
    }

    public String[] removeChars(String[] values){
        return Arrays.stream(values).map(e -> e.replace(" ", "").replace("\"", "")).toArray(String[]::new);
    }
}
