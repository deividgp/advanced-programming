package dataFrameRow.part5;

import dataFrameRow.part1.DataFrameAbstract;

import java.util.List;

public class SumVisitor extends Visitor {

    public SumVisitor(String label) {
        super(label);
    }

    @Override
    public double visit(DataFrameAbstract dataframe) {
        return dataframe.getColumnValues(super.label).stream().mapToDouble(c -> (double) c).sum();
    }
}
