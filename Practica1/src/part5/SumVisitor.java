package part5;

import part1.DataFrameAbstract;

public class SumVisitor extends Visitor {

    public SumVisitor(String label) {
        super(label);
    }

    @Override
    public double visit(DataFrameAbstract dataframe) {
        return dataframe.getColumnValues(super.label).stream().mapToDouble(c -> (double) c).sum();
    }
}
