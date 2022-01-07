package dataFrameColumn.part1;

public abstract class DataFrameFactory {
    public DataFrameFactory(){

    }
    public abstract DataFrameAbstract createDataFrame(String filename, String format);

    public Object transformString(Object value){
        value = value.toString();
        try {
            return Integer.parseInt((String)value);
        } catch (NumberFormatException nfe) {
            try {
                return Double.parseDouble((String)value);
            } catch (NumberFormatException ex) {
                return value;
            }
        }
    }

    public String removeChars(String value){
        return value.replace(" ", "").replace("\"", "");
    }

    public String[] removeChars(String[] values){
        for (int i = 0; i < values.length; i++){
            values[i] = values[i].replace(" ", "").replace("\"", "");
        }

        return values;
    }
}
