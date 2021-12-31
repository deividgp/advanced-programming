package part1;

import part5.DataFrameVisitor;
import part5.DataFrameVisitorInterface;

import java.util.*;
import java.util.function.Predicate;

public abstract class DataFrameAbstract<T> implements DataFrame<T> {
    HashMap<String, ArrayList<T>> dataFrame;

    public DataFrameAbstract(HashMap<String, ArrayList<T>> dataFrame){
        this.dataFrame = dataFrame;
    }

    public String at(int row, String column){
        return "";
    }

    public String iat(int row, int column){
        return null;
    }

    public int columns(){
        return 0;
    }

    public int size(){
        return 0;
    }

    public List<T> sort(String column, Comparator<T> comparator){
        List<T> values = dataFrame.get(column);
        values.sort(comparator);
        return values;
    }

    public List<T> query(Predicate<T> condition){
        return null;
    }

    @Override
    public Iterator<HashMap<String, ArrayList<T>>> iterator() {
        return null;
    }

    @Override
    public double maximum (DataFrameVisitor<T> visitor){
        return 0;
    }

    @Override
    public double minimum (DataFrameVisitor<T> visitor){
        return 0;
    }

    @Override
    public double average (DataFrameVisitor<T> visitor){
        return 0;
    }

    @Override
    public double sum (DataFrameVisitor<T> visitor){
        return 0;
    }
}
