package part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DataFrameFactory implementation.
 */
public class TXTDataFrameFactory extends DataFrameFactory {

    /**
     * TXTDataFrameFactory constructor.
     */
    public TXTDataFrameFactory() {
        super();
    }

    /**
     * @param filename File.
     * @param format Character used to separate fields.
     * @return TXTDataFrame.
     */
    @Override
    public DataFrameFile createDataFrame(String filename, String format) {
        String split = switch (format) {
            case "space" -> " ";
            case "semicolon" -> ";";
            case "comma" -> ",";
            case "quote" -> "\"";
            case "simplequote" -> "\'";
            default -> "";
        };
        if(split.equals("")) return null;

        LinkedList<HashMap<String, Object>> dataFrame = new LinkedList<>();
        String[] headers = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int index = 0;
            String line;

            if (split.equals("\"") || split.equals("\'")) {
                while ((line = br.readLine()) != null) {

                    Pattern pattern = Pattern.compile(split + "(.*?)" + split);
                    Matcher matcher = pattern.matcher(line);
                    List<String> splitLine = new ArrayList<>();
                    while (matcher.find()) {
                        String match = matcher.group(1);
                        if (!match.equals(" ") && !match.contains("  ")) {
                            splitLine.add(match);
                        }
                    }

                    if (index == 0) {
                        headers = removeChars(Arrays.copyOf(splitLine.toArray(),splitLine.toArray().length,String[].class));
                    } else {
                        HashMap<String, Object> map = new HashMap<>();
                        for (int i = 0; i < splitLine.size(); i++) {
                            map.put(headers[i], transformString(splitLine.get(i)));
                        }
                        dataFrame.add(map);
                    }
                    index++;
                }
            } else {
                while ((line = br.readLine()) != null) {
                    String[] splitLine = line.split(split);
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TXTDataFrame(dataFrame);
    }
}
