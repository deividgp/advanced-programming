package part1;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JSONDataFrameFactory<T> implements DataFrameFactory<T>{
    public JSONDataFrameFactory() {
    }


    public DataFrameAbstract<T> createDataFrame(String filename) {
        HashMap<String, ArrayList<T>> dataFrame = new HashMap<>();
        Gson gson = new Gson();

        try (JsonReader reader = new JsonReader(new FileReader(filename))) {
            Object o = gson.fromJson(reader, Object.class);
            ArrayList<?> data = (ArrayList<?>) o;
            for (int i = 0; i < data.size(); i++){
                LinkedTreeMap<?, ?> map = (LinkedTreeMap<?, ?>)data.get(i);
                Set<?> keys = map.keySet();
                if(i == 0){
                    for (Object key : keys){
                        dataFrame.put((String)key, new ArrayList<>());
                    }
                }

                for(int j = 0; j < map.size(); j++){
                    String key = (String) keys.toArray()[j];
                    ArrayList<T> columnList = dataFrame.get(key);
                    columnList.add((T) map.get(key));
                    dataFrame.put(key, columnList);
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return new JSONDataFrame<>(dataFrame);
    }
}
