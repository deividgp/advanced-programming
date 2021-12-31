package part5;

import part1.DataFrame;
import part2.DataFrameDirectory;

public class DataFrameVisitorImplement<T> implements DataFrameVisitor<T> {

    @Override
    public double maximum(String label, DataFrameDirectory<T> dataFrameDirectory) {
        return 0;
    }

    @Override
    public double maximum(String label, DataFrame<T> dataFrame) {
        return 0;
    }

    @Override
    public double minimum(String label, DataFrameDirectory<T> dataFrameDirectory) {
        return 0;
    }

    @Override
    public double minimum(String label, DataFrame<T> dataFrame) {
        return 0;
    }

    @Override
    public double average(String label, DataFrameDirectory<T> dataFrameDirectory) {
        return 0;
    }

    @Override
    public double average(String label, DataFrame<T> dataFrame) {
        return 0;
    }

    @Override
    public double sum(String label, DataFrameDirectory<T> dataFrameDirectory) {
        return 0;
    }

    @Override
    public double sum(String label, DataFrame<T> dataFrame) {
        return 0;
    }
}
