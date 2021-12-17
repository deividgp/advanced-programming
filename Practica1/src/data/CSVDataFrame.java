package data;

import java.util.Comparator;
import java.util.Iterator;

public class CSVDataFrame extends DataFrame {
    String[] columnLabels;
    int[] rows;
    String[] content;

    public CSVDataFrame(String[] columnLabels, int[] rows, String[] content){
        super(columnLabels, rows, content);
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }
}
