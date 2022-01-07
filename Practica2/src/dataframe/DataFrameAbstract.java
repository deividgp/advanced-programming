package dataframe;

import part2.Visitor;

import java.util.*;

public abstract class DataFrameAbstract implements DataFrame {
    HashMap<String, ArrayList<Object>> dataFrame;

    public DataFrameAbstract(HashMap<String, ArrayList<Object>> dataFrame){
        super();
        this.dataFrame = dataFrame;
    }

    public HashMap<String, ArrayList<Object>> getDataFrame() {
        return dataFrame;
    }

    @Override
    public List<Object> at(int row, String column){
        List<Object> list = new LinkedList<>();
        list.add(dataFrame.get(column).get(row));
        return list;
    }

    @Override
    public List<Integer> columns(){
        List<Integer> list = new LinkedList<>();
        list.add(dataFrame.size());
        return list;
    }

    @Override
    public List<Integer> size(){
        List<Integer> list = new LinkedList<>();
        list.add(dataFrame.get((String)dataFrame.keySet().toArray()[0]).size());
        return list;
    }

    public List<Object> getColumnList(String column){
        return dataFrame.get(column);
    }

    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
}
