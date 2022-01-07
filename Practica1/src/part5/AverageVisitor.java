package part5;

import part1.DataFrameAbstract;

public class AverageVisitor extends Visitor {

    public AverageVisitor(String label) {
        super(label);
    }

    @Override
    public double visit(DataFrameAbstract dataframe) {
        return dataframe.getColumnValues(super.label).stream().mapToDouble(c -> (double) c).average().orElse(0);
    }
}
