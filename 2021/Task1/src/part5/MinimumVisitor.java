package part5;

import part1.DataFrameFile;

/**
 * Minimum Visitor implementation.
 */
public class MinimumVisitor extends Visitor {

    /**
     * MinimumVisitor constructor.
     * @param label Column label/name.
     */
    public MinimumVisitor(String label) {
        super(label);
    }

    /**
     * Visit method (DataFrame file only).
     * @param dataframe Dataframe file.
     * @return Visit result.
     */
    @Override
    public double visit(DataFrameFile dataframe) {
        return dataframe.getColumnValues(super.label).stream().mapToDouble(c -> (double) c).min().orElse(0);
    }
}
