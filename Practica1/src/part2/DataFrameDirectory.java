package part2;

import part1.DataFrame;
import part5.Visitor;

import java.util.*;
import java.util.function.Predicate;

/**
 * DataFrame implementation.
 */
public class DataFrameDirectory implements DataFrame {

    private String name;
    private final List<DataFrame> dataFrameList;

    /**
     * DataFrameDirectory constructor.
     * @param name Directory name.
     */
    public DataFrameDirectory(String name) {
        super();
        this.name = name;
        dataFrameList = new LinkedList<>();
    }

    /**
     * DataFrameDirectory constructor.
     * @param name Directory name.
     * @param dataFrameList DataFrame list.
     */
    public DataFrameDirectory(String name, LinkedList<DataFrame> dataFrameList) {
        super();
        this.name = name;
        this.dataFrameList = dataFrameList;
    }

    /**
     * Name getter.
     * @return DataFrameDirectory name.
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter.
     * @param name New DataFrameDirectory name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * DataFrame list getter.
     * @return DataFrame list.
     */
    public List<DataFrame> getDataFrameList() {
        return dataFrameList;
    }

    /**
     * Add a dataframe to the list.
     * @param dataframe DataFrame (file or directory).
     */
    public void addDataFrame(DataFrame dataframe){
        dataFrameList.add(dataframe);
    }

    /**
     * Remove a dataframe from the list.
     * @param dataframe DataFrame (file or directory).
     */
    public void removeDataFrame(DataFrame dataframe){
        dataFrameList.remove(dataframe);
    }

    /**
     * Remove a dataframe from the list.
     * @param index List index.
     */
    public void removeDataFrame(int index){
        dataFrameList.remove(index);
    }

    /**
     * Access a single value for a row and column.
     * @param row Row index.
     * @param column Column label/name.
     * @return List of dataframe cell values (one per dataframe).
     */
    @Override
    public List<Object> at(int row, String column) {
        List<Object> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.at(row, column));
        }
        return list;
    }

    /**
     * Access a single value for a row and column by integer position.
     * @param row Row index.
     * @param column Column index.
     * @return List of dataframe cell values (one per dataframe).
     */
    @Override
    public List<Object> iat(int row, int column) {
        List<Object> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.iat(row, column));
        }
        return list;
    }

    /**
     * @return List of number of labels (one per dataframe).
     */
    @Override
    public List<Integer> columns() {
        List<Integer> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.columns());
        }
        return list;
    }

    /**
     * @return List of number of rows (one per dataframe).
     */
    @Override
    public List<Integer> size() {
        List<Integer> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.size());
        }
        return list;
    }

    /**
     * @param column Column label/name.
     * @param comparator Comparator.
     * @return List of values of a column in the dataframe following a certain order (one per dataframe).
     */
    @Override
    public List<List<Object>> sort(String column, Comparator<Object> comparator) {
        List<List<Object>> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.sort(column, comparator));
        }
        return list;
    }

    /**
     * @param condition Predicate.
     * @return List of elements where a label value fulfills a certain condition (one per dataframe).
     */
    public List<List<HashMap<String, Object>>> query(Predicate<HashMap<String, Object>> condition) {
        List<List<HashMap<String, Object>>> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.query(condition));
        }
        return list;
    }

    /**
     * @param column Column label.
     * @return List of elements of a certain column in a dataframe.
     */
    @Override
    public List<Object> getColumnValues(String column) {
        List<Object> list = new LinkedList<>();

        for (DataFrame dataFrame : dataFrameList){
            list.addAll(dataFrame.getColumnValues(column));
        }
        return list;
    }

    /**
     * @param v Visitor.
     * @return List of elements returned by the visitor (one per dataframe).
     */
    @Override
    public List<Double> accept(Visitor v){
        return v.visit(this);
    }

    /**
     * @return DataFrame Iterator.
     */
    @Override
    public Iterator<LinkedList<HashMap<String, Object>>> iterator() {
        List<LinkedList<HashMap<String, Object>>> list = new LinkedList<>();
        for(DataFrame dataframe : dataFrameList){
            Iterator<LinkedList<HashMap<String, Object>>> ite = dataframe.iterator();
            while (ite.hasNext()){
                LinkedList<HashMap<String, Object>> o = ite.next();
                list.add(o);
            }
        }
        return list.iterator();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public LinkedList<HashMap<String, Object>> next() {
        return null;
    }
}
