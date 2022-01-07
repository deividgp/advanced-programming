package part5;

import part1.DataFrame;
import part1.DataFrameAbstract;
import part2.DataFrameDirectory;

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
