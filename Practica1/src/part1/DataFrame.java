package part1;

import part5.Visitor;

import java.util.*;
import java.util.function.Predicate;

public interface DataFrame extends Iterator<LinkedList<HashMap<String, Object>>>{
    List<Object> at(int row, String column);
    List<Object> iat(int row, int column);
    List<Integer> columns();
    List<Integer> size();
    List<List<Object>> sort(String column, Comparator<Object> comparator);
    List<List<HashMap<String, Object>>> query(Predicate<HashMap<String, Object>> condition);
    List<DataFrame> toList();
    List<Object> getColumnValues(String column);
    List<Double> accept(Visitor v);
    Iterator<LinkedList<HashMap<String, Object>>> iterator();
}