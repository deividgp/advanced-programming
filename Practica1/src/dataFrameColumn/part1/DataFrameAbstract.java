package dataFrameColumn.part1;

import dataFrameColumn.part5.Visitor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class DataFrameAbstract implements DataFrame {
    HashMap<String, ArrayList<Object>> dataFrame;

    public DataFrameAbstract(HashMap<String, ArrayList<Object>> dataFrame){
        super();
        this.dataFrame = dataFrame;
    }

    public HashMap<String, ArrayList<Object>> getDataFrame() {
        return dataFrame;
    }

    public List<Object> at(int row, String column){
        List<Object> list = new LinkedList<>();
        list.add(dataFrame.get(column).get(row));
        return list;
    }

    public List<Object> iat(int row, int column){
        List<Object> list = new LinkedList<>();
        list.add(dataFrame.get((String)dataFrame.keySet().toArray()[column]).get(row));
        return list;
    }

    public List<Integer> columns(){
        List<Integer> list = new LinkedList<>();
        list.add(dataFrame.size());
        return list;
    }

    public List<Integer> size(){
        List<Integer> list = new LinkedList<>();
        list.add(dataFrame.get((String)dataFrame.keySet().toArray()[0]).size());
        return list;
    }

    public List<List<Object>> sort(String column, Comparator<Object> comparator){
        List<List<Object>> list = new LinkedList<>();
        list.add(dataFrame.get(column).stream()
                .sorted(comparator)
                .collect(Collectors.toList()));
        return list;
    }

    public List<DataFrame> query(String column, Predicate<Object> condition){
        List<Object> columnList = dataFrame.get(column);
        List<Object> queryList = columnList.stream()
                .filter(condition).toList();
        List<Integer> indexes = new ArrayList<>();

        for(Object o : columnList){
            if(!queryList.contains(o)){
                indexes.add(columnList.indexOf(o));
            }
        }
        HashMap<String, ArrayList<Object>> dataFrameAux = new HashMap<>(dataFrame);

        for (Map.Entry<String, ArrayList<Object>> entry : dataFrameAux.entrySet()) {
            ArrayList<Object> value = entry.getValue();

            for(Integer index : indexes){
                value.remove(index);
            }

            dataFrameAux.put(entry.getKey(), value);
        }

        DataFrame aux = switch (this.getClass().toString()) {
            case "JSONDataFrame" -> new JSONDataFrame(dataFrameAux);
            case "CSVDataFrame" -> new CSVDataFrame(dataFrameAux);
            case "TXTDataFrame" -> new TXTDataFrame(dataFrameAux);
            default -> null;
        };

        List<DataFrame> list = new LinkedList<>();
        list.add(aux);
        return list;
    }

    @Override
    public double accept(Visitor v){
        return v.visit(this);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Set<Map.Entry<String, ArrayList<Object>>> next() {
        return null;
    }

    @Override
    public Iterator<Set<Map.Entry<String, ArrayList<Object>>>> iterator() {
        List<Set<Map.Entry<String, ArrayList<Object>>>> list = new LinkedList<>();
        list.add(dataFrame.entrySet());
        return list.iterator();
    }

    @Override
    public List<DataFrame> toList(){
        List<DataFrame> result = new LinkedList<>();
        result.add(this);
        return result;
    }
}
