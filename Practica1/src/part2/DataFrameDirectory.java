package part2;

import part1.DataFrame;
import part5.DataFrameVisitor;
import part5.DataFrameVisitorInterface;

import java.util.*;
import java.util.function.Predicate;

public class DataFrameDirectory<T> implements DataFrame<T>, DataFrameVisitorInterface<T> {

    private String name;
    private List<DataFrame<T>> dataFrameList;

    public DataFrameDirectory(String name) {
        super();
        this.name = name;
        dataFrameList = new LinkedList<>();
    }

    public void addChild(DataFrame<T> child){
        dataFrameList.add(child);
    }

    public void removeChild(DataFrame<T> child){
        dataFrameList.remove(child);
    }

    public String at(int row, String column) {
        for (DataFrame<T> dataFrame : dataFrameList){
            dataFrame.at(row, column);
        }
        return null;
    }

    public String iat(int row, int column) {
        for (DataFrame<T> dataFrame : dataFrameList){
            dataFrame.iat(row, column);
        }
        return null;
    }

    public int columns() {
        for (DataFrame<T> dataFrame : dataFrameList){
            dataFrame.columns();
        }
        return 0;
    }

    public int size() {
        for (DataFrame<T> dataFrame : dataFrameList){
            dataFrame.size();
        }
        return 0;
    }

    public List<T> sort(String column, Comparator<T> comparator) {
        for (DataFrame<T> dataFrame : dataFrameList){
            dataFrame.sort(column, comparator);
        }
        return null;
    }

    public List<T> query(Predicate<T> condition) {
        for (DataFrame<T> dataFrame : dataFrameList){
            dataFrame.query(condition);
        }
        return null;
    }

    @Override
    public Iterator<HashMap<String, ArrayList<T>>> iterator() {
        return null;
    }

    @Override
    public double maximum(DataFrameVisitor<T> visitor) {
        return 0;
    }

    @Override
    public double minimum(DataFrameVisitor<T> visitor) {
        return 0;
    }

    @Override
    public double average(DataFrameVisitor<T> visitor) {
        return 0;
    }

    @Override
    public double sum(DataFrameVisitor<T> visitor) {
        return 0;
    }
}
