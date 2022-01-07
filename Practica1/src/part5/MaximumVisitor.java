package part5;

import part1.DataFrameAbstract;

public class MaximumVisitor extends Visitor {

    public MaximumVisitor(String label) {
        super(label);
    }

    @Override
    public double visit(DataFrameAbstract dataframe) {
        return dataframe.getColumnValues(super.label).stream().mapToDouble(c -> (double) c).max().orElse(0);
    }
}
