package part2;

import part1.DataFrame;
import part1.DataFrameAbstract;
import part5.Visitor;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.function.Predicate;

public class DataFrameDirectory implements DataFrame {

    private String name;
    private List<DataFrame> dataFrameList;

    public DataFrameDirectory(String name) {
        super();
        this.name = name;
        dataFrameList = new LinkedList<>();
    }

    public void addChild(DataFrame child){
        dataFrameList.add(child);
    }

    public void removeChild(DataFrame child){
        dataFrameList.remove(child);
    }

    public List<DataFrame> getChildren() {
        return dataFrameList;
    }

    public List<Object> at(int row, String column) {
        List<Object> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.at(row, column));
        }
        return list;
    }

    public List<Object> iat(int row, int column) {
        List<Object> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.iat(row, column));
        }
        return list;
    }

    public List<Integer> columns() {
        List<Integer> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.columns());
        }
        return list;
    }

    public List<Integer> size() {
        List<Integer> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.size());
        }
        return list;
    }

    public List<List<Object>> sort(String column, Comparator<Object> comparator) {
        List<List<Object>> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.sort(column, comparator));
        }
        return list;
    }

    public List<DataFrame> query(String column, Predicate<Object> condition) {
        List<DataFrame> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.query(column, condition));
        }
        return list;
    }

    public double accept(Visitor v){
        v.visit(this);
        return 0;
    }

    @Override
    public Iterator<Set<Map.Entry<String, ArrayList<Object>>>> iterator() {
        List<Set<Map.Entry<String, ArrayList<Object>>>> list = new LinkedList<>();
        for(DataFrame dataframe : dataFrameList){
            Iterator<Set<Map.Entry<String, ArrayList<Object>>>> ite = dataframe.iterator();
            while (ite.hasNext()){
                Set<Map.Entry<String, ArrayList<Object>>> o = ite.next();
                list.add(o);
            }
        }
        return list.iterator();
    }

    public List<DataFrame> toList() {
        List<DataFrame> result = new LinkedList<>();
        result.add(this);
        for (DataFrame child : dataFrameList)
            result.addAll(child.toList());
        return result;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Set<Map.Entry<String, ArrayList<Object>>> next() {
        return null;
    }
}
