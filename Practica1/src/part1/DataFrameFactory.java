package part1;

import java.util.Arrays;

/**
 * DataFrameFactory to create dataframes.
 */
public abstract class DataFrameFactory {
    /**
     * DataFrameFactory constructor.
     */
    public DataFrameFactory(){

    }

    /**
     * Create a new DataFrameFile object (dataframe file).
     * @param filename File.
     * @param format Character used to separate fields.
     * @return DataFrameFile
     */
    public abstract DataFrameFile createDataFrame(String filename, String format);

    /**
     * Parses Object.
     * @param value Original variable.
     * @return Parsed object.
     */
    public Object transformString(Object value){
        try {
            return Double.parseDouble((String)value);
        } catch (NumberFormatException ex) {
            return value;
        }
    }

    /**
     * Removes unnecessary characters before parsing (transformString).
     * @param value String to modify.
     * @return String
     */
    public String removeChars(String value){
        return value.replace(" ", "").replace("\"", "");
    }

    /**
     * Removes unnecessary characters before parsing (transformString).
     * @param values String array containing original values.
     * @return String array containing resulting values.
     */
    public String[] removeChars(String[] values){
        return Arrays.stream(values).map(e -> e.replace(" ", "").replace("\"", "")).toArray(String[]::new);
    }
}
