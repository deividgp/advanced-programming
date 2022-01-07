package dataFrameColumn.part4;

import dataFrameColumn.part1.DataFrame;

import java.util.*;
import java.util.stream.Collectors;

public class MapReduce {
    List<DataFrame> dataFrameList;

    public MapReduce(List<DataFrame> dataFrameList){
        this.dataFrameList = dataFrameList;
    }

    public void MapMax(String label){
        List<Integer> maxList = new LinkedList<>();

        for (DataFrame dataframe : dataFrameList){
            var wrapper = new Object(){ int max = 0; };
            Iterator<Set<Map.Entry<String, ArrayList<Object>>>> ite = dataframe.iterator();
            while (ite.hasNext()){
                Set<Map.Entry<String, ArrayList<Object>>> setDataframe = ite.next();
                List<Object> list = setDataframe.stream()
                        .filter(e -> e.getKey().equalsIgnoreCase(label))
                        .map(e -> e.getValue()).collect(Collectors.toList()).get(0);
                wrapper.max = (int) list.get(0);
                list.forEach(e -> {
                            if(wrapper.max < (int) e){
                                wrapper.max = (int)e;
                            }
                        });
            }
            maxList.add(wrapper.max);
        }

        maxList.stream().reduce(Integer::max).ifPresent(System.out::println);
        for (double e : maxList)
            System.out.println(e);
    }
}
