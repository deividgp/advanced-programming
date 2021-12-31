package part5;

import part1.DataFrame;
import part2.DataFrameDirectory;

public interface DataFrameVisitor<T> {
    double maximum (String label, DataFrameDirectory<T> dataFrameDirectory);
    double maximum (String label, DataFrame<T> dataFrame);
    double minimum (String label, DataFrameDirectory<T> dataFrameDirectory);
    double minimum (String label, DataFrame<T> dataFrame);
    double average (String label, DataFrameDirectory<T> dataFrameDirectory);
    double average (String label, DataFrame<T> dataFrame);
    double sum (String label, DataFrameDirectory<T> dataFrameDirectory);
    double sum (String label, DataFrame<T> dataFrame);
}
