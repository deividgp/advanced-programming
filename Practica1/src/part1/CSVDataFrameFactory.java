package part1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVDataFrameFactory<T> implements DataFrameFactory<T> {

    public CSVDataFrameFactory() {

    }

    @Override
    public DataFrameAbstract<T> createDataFrame(String filename) {
        HashMap<String, ArrayList<T>> dataFrame = new HashMap<>();
        String[] headers = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int index = 0;
            String line;

            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");
                if(index == 0){
                    headers = removeChars(splitLine);
                    for(String column : headers){
                        dataFrame.put(column, null);
                    }
                }else{
                    for (int i = 0; i< splitLine.length; i++){

                        ArrayList<T> columnList = dataFrame.get(headers[i]);
                        columnList.add((T) removeChars(splitLine[i]));
                        dataFrame.put(headers[i], columnList);
                    }
                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        List<T> hola = dataFrame.get("LatD");
//        for (T hol : hola){
//            System.out.println(hol.getClass());
//        }
        return new CSVDataFrame<>(dataFrame);
    }

    private String removeChars(String value){
        return value.replace(" ", "").replace("\"", "");
    }

    private String[] removeChars(String[] values){
        for (int i = 0; i < values.length; i++){
            values[i] = values[i].replace(" ", "").replace("\"", "");
        }

        return values;
    }
}
