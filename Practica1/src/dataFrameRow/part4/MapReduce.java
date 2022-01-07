package dataFrameRow.part4;

import dataFrameRow.part1.DataFrame;

import javax.lang.model.type.PrimitiveType;
import javax.xml.crypto.Data;
import java.util.*;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MapReduce {
    List<DataFrame> dataFrameList;

    public MapReduce(List<DataFrame> dataFrameList){
        this.dataFrameList = dataFrameList;
    }

    public List<Object> MapColumn(String label){
        return dataFrameList.stream().map(e -> e.getColumnValues(label)).collect(Collectors.toList()).get(0);
    }

    public Double ReduceMaxMinSum(List<Object> result, DoubleBinaryOperator operator){
        return result.stream().mapToDouble(e -> (double) e).reduce(operator).orElse(0);
    }

    public Double ReduceAvg(List<Object> result){
        return result.stream().mapToDouble(e -> (double) e).average().orElse(0);
    }

    public List<HashMap<String, Object>> MapMaxRow(String label){
        List<HashMap<String, Object>> maxRows = new LinkedList<>();
        double max = ReduceMaxMinSum(MapColumn(label),Double::max);
        for(DataFrame dataframe : dataFrameList){
            Iterator<LinkedList<HashMap<String, Object>>> ite = dataframe.iterator();
            maxRows.add(ite.next().stream().filter(e -> (double)e.get(label) == max).collect(Collectors.toList()).get(0));
        }
        return maxRows;
    }

    public void ReduceRows(List<HashMap<String, Object>> result){
        result.stream().reduce(e -> e.get())
    }

    public void Join(Predicate<HashMap<String, Object>> condition){
        for(DataFrame dataframe : dataFrameList){
            Iterator<LinkedList<HashMap<String, Object>>> ite = dataframe.iterator();
            ite.next().stream().filter(condition).collect(Collectors.toList());
        }
    }
}
