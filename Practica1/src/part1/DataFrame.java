package part1;

import part5.Visitor;

import java.util.*;
import java.util.function.Predicate;

/**
 * DataFrame interface (extends iterator).
 */
public interface DataFrame extends Iterator<LinkedList<HashMap<String, Object>>>{
    /**
     * Access a single value for a row and column.
     * @param row Row index.
     * @param column Column label/name.
     * @return List of dataframe cell values (one per dataframe).
     */
    List<Object> at(int row, String column);

    /**
     * Access a single value for a row and column by integer position.
     * @param row Row index.
     * @param column Column index.
     * @return List of dataframe cell values (one per dataframe).
     */
    List<Object> iat(int row, int column);

    /**
     * @return List of number of labels (one per dataframe).
     */
    List<Integer> columns();

    /**
     * @return List of number of rows (one per dataframe).
     */
    List<Integer> size();

    /**
     * @param column Column label/name.
     * @param comparator Comparator.
     * @return List of values of a column in the dataframe following a certain order (one per dataframe).
     */
    List<List<Object>> sort(String column, Comparator<Object> comparator);

    /**
     * @param condition Predicate.
     * @return List of elements where a label value fulfills a certain condition (one per dataframe).
     */
    List<List<HashMap<String, Object>>> query(Predicate<HashMap<String, Object>> condition);

    /**
     * @param column Column label.
     * @return List of elements of a certain column in a dataframe.
     */
    List<Object> getColumnValues(String column);

    /**
     * @param v Visitor.
     * @return List of elements returned by the visitor (one per dataframe).
     */
    List<Double> accept(Visitor v);

    /**
     * @return DataFrame Iterator.
     */
    Iterator<LinkedList<HashMap<String, Object>>> iterator();
}