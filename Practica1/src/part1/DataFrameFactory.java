package part1;

public interface DataFrameFactory<T> {
    DataFrameAbstract<T> createDataFrame(String filename);
}
