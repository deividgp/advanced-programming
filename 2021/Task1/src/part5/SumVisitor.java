package part5;

import part1.DataFrameFile;

/**
 * Sum visitor implementation.
 */
public class SumVisitor extends Visitor {

    /**
     * SumVisitor constructor.
     * @param label Column label/name.
     */
    public SumVisitor(String label) {
        super(label);
    }

    /**
     * Visit method (DataFrame file only).
     * @param dataframe Dataframe file.
     * @return Visit result.
     */
    @Override
    public double visit(DataFrameFile dataframe) {
        return dataframe.getColumnValues(super.label).stream().mapToDouble(c -> (double) c).sum();
    }
}
