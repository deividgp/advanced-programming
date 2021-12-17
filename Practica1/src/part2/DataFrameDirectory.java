package part2;

import part1.DataFrame;

import javax.xml.crypto.Data;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DataFrameDirectory extends DataFrame {

    private List<DataFrame> dataFrameList;
    private String category;

    public DataFrameDirectory(String category) {
        this.category = category;
        dataFrameList = new LinkedList<>();
    }

    public String at(int row, String column){
        for(DataFrame dataFrame : dataFrameList){
            dataFrame.at(row, column);
        }
        return null;
    }

    public String iat(int row, String column){
        for(DataFrame dataFrame : dataFrameList){
            dataFrame.iat(row, column);
        }
        return null;
    }

    public int columns(){
        for(DataFrame dataFrame : dataFrameList){
            dataFrame.columns();
        }
        return 0;
    }

    public int size(){
        for(DataFrame dataFrame : dataFrameList){
            dataFrame.size();
        }
        return 0;
    }

    public String sort(String column, Comparator<DataFrame> comparator){
        for(DataFrame dataFrame : dataFrameList){
            dataFrame.sort(column, comparator);
        }
        return null;
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }
}
