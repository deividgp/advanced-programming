package part5;

import part1.DataFrameAbstract;

import java.util.List;

public class MaximumVisitor extends Visitor {

    public MaximumVisitor(String label) {
        super(label);
    }

    @Override
    public double visit(DataFrameAbstract dataframe) {
        List<Object> list = dataframe.getDataFrame().get(super.label);
        double max = (double) list.get(0);
        list.remove(0);
        for (Object o : list){
            double objValue = (double) o;
            if(max < objValue){
                max = objValue;
            }
        }
        return max;
    }
}
