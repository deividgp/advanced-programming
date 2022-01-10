package part5;

import part1.DataFrame;
import part1.DataFrameFile;
import part2.DataFrameDirectory;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor class.
 */
public abstract class Visitor {
    String label;

    /**
     * Visitor constructor.
     * @param label Column label/name.
     */
    public Visitor(String label){
        this.label = label;
    }

    /**
     * Visit method (DataFrame file only).
     * @param dataframe Dataframe file.
     * @return Visit result.
     */
    public abstract double visit(DataFrameFile dataframe);

    /**
     * Visit method (DataFrameDirectory only).
     * @param dataframeDir Dataframe directory.
     * @return List of visit results (one per dataframe).
     */
    public List<Double> visit(DataFrameDirectory dataframeDir){
        List<Double> results = new ArrayList<>();
        for (DataFrame elem : dataframeDir.getDataFrameList())
            results.addAll(elem.accept(this));

        return results;
    }
}
