package part1;

import part5.Visitor;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * DataFrame implementation.
 */
public abstract class DataFrameFile implements DataFrame {
    LinkedList<HashMap<String, Object>> dataFrame;

    /**
     * DataFrameFile constructor.
     * @param dataFrame DataFrame data structure.
     */
    public DataFrameFile(LinkedList<HashMap<String, Object>> dataFrame){
        super();
        this.dataFrame = dataFrame;
    }

    /**
     * DataFrame getter.
     * @return dataframe structure.
     */
    public LinkedList<HashMap<String, Object>> getDataFrame() {
        return dataFrame;
    }

    /**
     * Access a single value for a row and column.
     * @param row Row index.
     * @param column Column label/name.
     * @return list of dataframe cell values (one per dataframe).
     */
    @Override
    public List<Object> at(int row, String column){
        List<Object> list = new LinkedList<>();
        list.add(dataFrame.get(row).get(column));
        return list;
    }

    /**
     * Access a single value for a row and column by integer position.
     * @param row Row index.
     * @param column Column index.
     * @return List of dataframe cell values (one per dataframe).
     */
    @Override
    public List<Object> iat(int row, int column){
        List<Object> list = new LinkedList<>();
        list.add(dataFrame.get(row).get((String)dataFrame.get(row).keySet().toArray()[column]));
        return list;
    }

    /**
     * @return List of number of labels (one per dataframe).
     */
    @Override
    public List<Integer> columns(){
        List<Integer> list = new LinkedList<>();
        list.add(dataFrame.get(0).size());
        return list;
    }

    /**
     * @return List of number of rows (one per dataframe).
     */
    @Override
    public List<Integer> size(){
        List<Integer> list = new LinkedList<>();
        list.add(dataFrame.size());
        return list;
    }

    /**
     * @param column Column label/name.
     * @param comparator Comparator.
     * @return List of values of a column in the dataframe following a certain order (one per dataframe).
     */
    @Override
    public List<List<Object>> sort(String column, Comparator<Object> comparator){
        List<List<Object>> list = new LinkedList<>();
        list.add(dataFrame.stream()
                        .map(e -> e.get(column))
                        .sorted(comparator)
                        .collect(Collectors.toList()));
        return list;
    }

    /**
     * @param condition Predicate.
     * @return List of elements where a label value fulfills a certain condition (one per dataframe).
     */
    @Override
    public List<List<HashMap<String, Object>>> query(Predicate<HashMap<String, Object>> condition){
        List<List<HashMap<String, Object>>> list = new LinkedList<>();
        list.add(dataFrame.stream()
                .filter(condition)
                .collect(Collectors.toList()));
        return list;
    }

    /**
     * @param column Column label.
     * @return List of elements of a certain column in a dataframe.
     */
    @Override
    public List<Object> getColumnValues(String column){
        return dataFrame.stream().map(e -> e.get(column)).collect(Collectors.toList());
    }

    /**
     * @param v Visitor.
     * @return List of elements returned by the visitor (one per dataframe).
     */
    @Override
    public List<Double> accept(Visitor v){
        List<Double> aux = new LinkedList<>();
        aux.add(v.visit(this));
        return aux;
    }

    /**
     * @return DataFrame Iterator.
     */
    @Override
    public Iterator<LinkedList<HashMap<String, Object>>> iterator() {
        List<LinkedList<HashMap<String, Object>>> list = new LinkedList<>();
        list.add(dataFrame);
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

    /**
     * Compare two objects (ascending or descending)
     * @param o1 First object
     * @param o2 Second object
     * @param order 1: ascending order | -1: descending order
     * @return Number for ordering purposes (1, -1 or 0)
     */
    public static int compare(Object o1, Object o2, int order) {
        if(o1 instanceof Double && o2 instanceof Double){
            return ((Double) o1).compareTo((Double) o2) * order;
        }else if(o1 instanceof String && o2 instanceof String){
            return ((String) o1).compareTo((String) o2) * order;
        }
        return 0;
    }
}
