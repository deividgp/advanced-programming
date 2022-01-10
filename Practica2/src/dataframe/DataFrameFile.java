package dataframe;

import part2.Visitor;

import java.util.*;
import java.util.stream.Collectors;

public abstract class DataFrameFile implements DataFrame {
    LinkedList<HashMap<String, Object>> dataFrame;

    public DataFrameFile(LinkedList<HashMap<String, Object>> dataFrame){
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

    public List<Object> getColumnValues(String column){
        return dataFrame.stream().map(e -> e.get(column)).collect(Collectors.toList());
    }

    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
}
