package part5;

import part1.DataFrameAbstract;

public class MinimumVisitor extends Visitor {

    public MinimumVisitor(String label) {
        super(label);
    }

    @Override
    public double visit(DataFrameAbstract dataframe) {
        return dataframe.getColumnValues(super.label).stream().mapToDouble(c -> (double) c).min().orElse(0);
    }
}
