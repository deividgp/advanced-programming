package java;

import part5.Visitor;

import java.util.*;
import java.util.function.Predicate;

public interface DataFrame extends Iterator<Set<Map.Entry<String, ArrayList<Object>>>>{
    List<Object> at(int row, String column);
    List<Object> iat(int row, int column);
    List<Integer> columns();
    List<Integer> size();
    List<List<Object>> sort(String column, Comparator<Object> comparator);
    List<DataFrame> query(String column, Predicate<Object> condition);
    List<DataFrame> toList();
    double accept(Visitor v);
    Iterator<Set<Map.Entry<String, ArrayList<Object>>>> iterator();
}
