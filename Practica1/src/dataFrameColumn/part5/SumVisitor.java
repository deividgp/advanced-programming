package dataFrameColumn.part5;

import dataFrameColumn.part1.DataFrameAbstract;

import java.util.List;

public class SumVisitor extends Visitor {

    public SumVisitor(String label) {
        super(label);
    }

    @Override
    public double visit(DataFrameAbstract dataframe) {
        List<Object> list = dataframe.getDataFrame().get(super.label);
        double sum = 0.0;
        for (Object o : list){
            sum = sum + (double) o;
        }
        return sum;
    }
}
