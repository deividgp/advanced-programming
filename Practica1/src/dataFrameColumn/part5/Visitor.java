package dataFrameColumn.part5;

import dataFrameColumn.part1.DataFrame;
import dataFrameColumn.part1.DataFrameAbstract;
import dataFrameColumn.part2.DataFrameDirectory;

import java.util.ArrayList;
import java.util.List;

public abstract class Visitor {
    String label;

    public Visitor(String label){
        this.label = label;
    }
    public abstract double visit(DataFrameAbstract dataframe);
    public List<Double> visit(DataFrameDirectory dataframeDir){
        List<Double> results = new ArrayList<>();
        for (DataFrame elem : dataframeDir.getChildren())
            results.add(elem.accept(this));

        return results;
    }
}
