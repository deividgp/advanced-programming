package part4;

import part1.DataFrame;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * MapReduce class.
 */
public class MapReduce {
    List<DataFrame> dataFrameList;

    /**
     * Constructor.
     * @param dataFrameList List to work with.
     */
    public MapReduce(List<DataFrame> dataFrameList){
        this.dataFrameList = dataFrameList;
    }

    /**
     * @param label Column identifier.
     * @return Result Value.
     */
    public List<Object> MapColumn(String label){
        return dataFrameList.stream().map(e -> e.getColumnValues(label)).collect(Collectors.toList()).get(0);
    }

    /**
     * @param result List of Objects.
     * @param operator Double operator (maxi, min, sum...).
     * @return Result Value.
     */
    public Double ReduceMaxMinSum(List<Object> result, DoubleBinaryOperator operator){
        return result.stream().mapToDouble(e -> (double) e).reduce(operator).orElse(0);
    }

    /**
     * @param result List of Objects.
     * @return Result Value.
     */
    public Double ReduceAvg(List<Object> result){
        return result.stream().mapToDouble(e -> (double) e).average().orElse(0);
    }

    /**
     * Maps the rows with the maximum value.
     * @param label Column identifier.
     * @return Rows that contain the max value.
     */
    public List<HashMap<String, Object>> MapMaxRow(String label){
        List<HashMap<String, Object>> maxRows = new LinkedList<>();
        double max = ReduceMaxMinSum(MapColumn(label),Double::max);
        for(DataFrame dataframe : dataFrameList){
            Iterator<LinkedList<HashMap<String, Object>>> ite = dataframe.iterator();
            maxRows.add(ite.next().stream().filter(e -> (double)e.get(label) == max).collect(Collectors.toList()).get(0));
        }
        return maxRows;
    }
}
