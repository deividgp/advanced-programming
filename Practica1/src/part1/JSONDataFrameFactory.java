package part1;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * DataFrameFactory implementation.
 */
public class JSONDataFrameFactory extends DataFrameFactory {
    /**
     * JSONDataFrameFactory constructor.
     */
    public JSONDataFrameFactory() {
        super();
    }


    /**
     * Create a new JSONDataFrame object (dataframe file).
     * @param filename File.
     * @param format Character used to separate fields.
     * @return JSONDataFrame.
     */
    public DataFrameFile createDataFrame(String filename, String format) {
        LinkedList<HashMap<String, Object>> dataFrame = new LinkedList<>();
        Gson gson = new Gson();

        try (JsonReader reader = new JsonReader(new FileReader(filename))) {
            Object o = gson.fromJson(reader, Object.class);
            ArrayList<?> data = (ArrayList<?>) o;
            for (Object datum : data) {
                LinkedTreeMap<?, ?> map = (LinkedTreeMap<?, ?>) datum;
                Set<?> keys = map.keySet();
                HashMap<String, Object> mapAux = new HashMap<>();
                for (int j = 0; j < map.size(); j++) {
                    String key = (String) keys.toArray()[j];
                    mapAux.put(key, map.get(key));
                }
                dataFrame.add(mapAux);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return new JSONDataFrame(dataFrame);
    }
}
