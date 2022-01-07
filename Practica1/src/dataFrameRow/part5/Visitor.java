package dataFrameRow.part5;

import dataFrameRow.part1.DataFrame;
import dataFrameRow.part1.DataFrameAbstract;
import dataFrameRow.part2.DataFrameDirectory;

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
            results.addAll(elem.accept(this));

        return results;
    }
}
