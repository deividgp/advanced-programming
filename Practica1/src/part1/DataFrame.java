package part1;

import part5.DataFrameVisitorInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public interface DataFrame<T> extends Iterable<HashMap<String, ArrayList<T>>>, DataFrameVisitorInterface<T> {
    String at(int row, String column);
    String iat(int row, int column);
    int columns();
    int size();
    List<T> sort(String column, Comparator<T> comparator);
    List<T> query(Predicate<T> condition);
}
