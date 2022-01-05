package part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TXTDataFrameFactory extends DataFrameFactory {

    public TXTDataFrameFactory() {
        super();
    }

    @Override
    public DataFrameAbstract createDataFrame(String filename, String format) {
        String split = "";
        switch (format){
            case "space":
                split = " ";
                break;
            case "semicolon":
                split = ";";
                break;
            case "comma":
                split = ",";
                break;
        }
        HashMap<String, ArrayList<Object>> dataFrame = new HashMap<>();
        String[] headers = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int index = 0;
            String line;

            if (format.equalsIgnoreCase("quote")) {
                while ((line = br.readLine()) != null) {

                    Pattern pattern = Pattern.compile("\"(.*?)\"");
                    Matcher matcher = pattern.matcher(line);
                    List<String> splitLine = new ArrayList<>();
                    while (matcher.find()) {
                        String match = matcher.group(1);
                        if (!match.equals(" ") && !match.contains("  ")) {
                            splitLine.add(match);
                        }
                    }

                    if (index == 0) {
                        headers = removeChars((String[]) splitLine.toArray());
                        for (String column : headers) {
                            dataFrame.put(column, new ArrayList<>());
                        }
                    } else {
                        for (int i = 0; i < splitLine.size(); i++) {

                            ArrayList<Object> columnList = dataFrame.get(headers[i]);
                            columnList.add(removeChars(splitLine.get(i)));
                            dataFrame.put(headers[i], columnList);
                        }
                    }
                    index++;
                }
            } else {
                while ((line = br.readLine()) != null) {
                    String[] splitLine = line.split(split);
                    if (index == 0) {
                        headers = removeChars(splitLine);
                        for (String column : headers) {
                            dataFrame.put(column, new ArrayList<>());
                        }
                    } else {
                        for (int i = 0; i < splitLine.length; i++) {

                            ArrayList<Object> columnList = dataFrame.get(headers[i]);
                            columnList.add(removeChars(splitLine[i]));
                            columnList.add(75.55);
                            dataFrame.put(headers[i], columnList);
                        }
                    }
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TXTDataFrame(dataFrame);
    }
}
