package dataFrameRow.part1;

import dataFrameRow.part4.MapReduce;

import dataFrameRow.part5.AverageVisitor;
import dataFrameRow.part5.MaximumVisitor;
import dataFrameRow.part5.MinimumVisitor;
import dataFrameRow.part5.Visitor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
//        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
//        DataFrame data = jsonFactory.createDataFrame("cities.json", "");
//        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
//        DataFrame data = txtFactory.createDataFrame("cities.txt", "quote");
        List<DataFrame> aux = new LinkedList<>();
        aux.add(data);
        MapReduce map = new MapReduce(aux);
        map.ReduceMaxMinSum(map.MapColumn("LatD"), Double::max);
        map.ReduceAvg(map.MapColumn("LatD"));
        Iterator<LinkedList<HashMap<String, Object>>> ite = data.iterator();
        while (ite.hasNext()){
            LinkedList<HashMap<String, Object>> o = ite.next();
            System.out.println(o);
        }
        data.query(e -> (Double)e.get("LatD") > 39);
        System.out.println("Column values: "+data.getColumnValues("LatD"));
        Visitor visit = new MinimumVisitor("LatD");
        System.out.println("Visitor: " + data.accept(visit));
    }
}
