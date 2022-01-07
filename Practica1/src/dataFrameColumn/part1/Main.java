package dataFrameColumn.part1;

import dataFrameColumn.part4.MapReduce;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        List<DataFrame> aux = new LinkedList<>();
        aux.add(data);
        MapReduce map = new MapReduce(aux);
        map.MapMax("LatD");
//        Iterator<Set<java.util.Map.Entry<String, ArrayList<Object>>>> ite = data.iterator();
//        while (ite.hasNext()){
//            Set<Map.Entry<String, ArrayList<Object>>> o = ite.next();
//            System.out.println(o);
//        }
//        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
//        DataFrame dataJSON = jsonFactory.createDataFrame("cities.json", "");
    }
}
