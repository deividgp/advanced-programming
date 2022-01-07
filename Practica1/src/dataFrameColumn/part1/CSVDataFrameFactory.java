package dataFrameColumn.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CSVDataFrameFactory extends DataFrameFactory {

    public CSVDataFrameFactory() {
        super();
    }

    @Override
    public DataFrameAbstract createDataFrame(String filename, String format) {
        HashMap<String, ArrayList<Object>> dataFrame = new HashMap<>();
        String[] headers = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int index = 0;
            String line;

            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(",");
                if(index == 0){
                    headers = removeChars(splitLine);
                    for(String column : headers){
                        dataFrame.put(column, new ArrayList<>());
                    }
                }else{
                    for (int i = 0; i< splitLine.length; i++){

                        ArrayList<Object> columnList = dataFrame.get(headers[i]);
                        columnList.add(transformString(removeChars(splitLine[i])));
                        //columnList.add(75.55);
                        dataFrame.put(headers[i], columnList);
                    }
                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        List<Object> hola = dataFrame.get("LatD");
//        for (Object hol : hola){
//            System.out.println(hol.getClass());
//        }
        return new CSVDataFrame(dataFrame);
    }
}
