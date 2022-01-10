package dataframe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CSVDataFrameFactory extends DataFrameFactory {

    public CSVDataFrameFactory() {
        super();
    }

    @Override
    public DataFrameFile createDataFrame(String filename, String format) {
        LinkedList<HashMap<String, Object>> dataFrame = new LinkedList<>();

        String[] headers = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int index = 0;
            String line;

            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");
                if(index == 0){
                    headers = removeChars(splitLine);
                }else{
                    HashMap<String, Object> map = new HashMap<>();
                    for (int i = 0; i< splitLine.length; i++){
                        map.put(headers[i], transformString(removeChars(splitLine[i])));
                    }
                    dataFrame.add(map);
                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CSVDataFrame(dataFrame);
    }
}