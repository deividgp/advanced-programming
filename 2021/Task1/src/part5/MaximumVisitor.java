package part5;

import part1.DataFrameFile;

/**
 * Maximum Visitor implementation.
 */
public class MaximumVisitor extends Visitor {

    /**
     * MaximumVisitor constructor.
     * @param label Column label/name.
     */
    public MaximumVisitor(String label) {
        super(label);
    }

    /**
     * Visit method (DataFrame file only).
     * @param dataframe Dataframe file.
     * @return Visit result.
     */
    @Override
    public double visit(DataFrameFile dataframe) {
        return dataframe.getColumnValues(super.label).stream().mapToDouble(c -> (double) c).max().orElse(0);
    }
}
