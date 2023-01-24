package part5;

import part1.DataFrameFile;

/**
 * Average Visitor implementation.
 */
public class AverageVisitor extends Visitor {

    /**
     * AverageVisitor constructor.
     * @param label Column label/name.
     */
    public AverageVisitor(String label) {
        super(label);
    }

    /**
     * Visit method (DataFrame file only).
     * @param dataframe Dataframe file.
     * @return Visit result.
     */
    @Override
    public double visit(DataFrameFile dataframe) {
        return dataframe.getColumnValues(super.label).stream().mapToDouble(c -> (double) c).average().orElse(0);
    }
}
