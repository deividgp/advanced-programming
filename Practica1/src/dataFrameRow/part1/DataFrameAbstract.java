package dataFrameRow.part1;

import dataFrameRow.part5.Visitor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class DataFrameAbstract implements DataFrame {
    LinkedList<HashMap<String, Object>> dataFrame;

    public DataFrameAbstract(LinkedList<HashMap<String, Object>> dataFrame){
        super();
        this.dataFrame = dataFrame;
    }

    public LinkedList<HashMap<String, Object>> getDataFrame() {
        return dataFrame;
    }

    @Override
    public List<Object> at(int row, String column){
        List<Object> list = new LinkedList<>();
        list.add(dataFrame.get(row).get(column));
        return list;
    }

    @Override
    public List<Object> iat(int row, int column){
        List<Object> list = new LinkedList<>();
        list.add(dataFrame.get(row).get((String)dataFrame.get(row).keySet().toArray()[column]));
        return list;
    }

    @Override
    public List<Integer> columns(){
        List<Integer> list = new LinkedList<>();
        list.add(dataFrame.get(0).size());
        return list;
    }

    @Override
    public List<Integer> size(){
        List<Integer> list = new LinkedList<>();
        list.add(dataFrame.size());
        return list;
    }

    @Override
    public List<List<Object>> sort(String column, Comparator<Object> comparator){
        List<List<Object>> list = new LinkedList<>();
        list.add(dataFrame.stream()
                        .map(e -> e.get(column))
                        .sorted(comparator)
                        .collect(Collectors.toList()));
        return list;
    }

    @Override
    public List<Object> getColumnValues(String column){
        return dataFrame.stream().map(e -> e.get(column)).collect(Collectors.toList());
    }

    @Override
    public List<List<HashMap<String, Object>>> query(Predicate<HashMap<String, Object>> condition){
        List<List<HashMap<String, Object>>> list = new LinkedList<>();
        list.add(dataFrame.stream()
                .filter(condition)
                .collect(Collectors.toList()));
        return list;
    }

    @Override
    public List<Double> accept(Visitor v){
        List<Double> aux = new LinkedList<>();
        aux.add(v.visit(this));
        return aux;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public LinkedList<HashMap<String, Object>> next() {
        return null;
    }

    @Override
    public Iterator<LinkedList<HashMap<String, Object>>> iterator() {
        List<LinkedList<HashMap<String, Object>>> list = new LinkedList<>();
        list.add(dataFrame);
        return list.iterator();
    }

    @Override
    public List<DataFrame> toList(){
        List<DataFrame> result = new LinkedList<>();
        result.add(this);
        return result;
    }
}
