package dataFrameColumn.part5;

import dataFrameColumn.part1.DataFrameAbstract;

import java.util.List;

public class MinimumVisitor extends Visitor {

    public MinimumVisitor(String label) {
        super(label);
    }

    @Override
    public double visit(DataFrameAbstract dataframe) {
        List<Object> list = dataframe.getDataFrame().get(super.label);
        double min = (double) list.get(0);
        list.remove(0);
        for (Object o : list){
            double objValue = (double) o;
            if(min > objValue){
                min = objValue;
            }
        }
        return min;
    }
}
