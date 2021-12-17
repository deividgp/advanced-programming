package part1;

public class CSVDataFrameFactory extends DataFrameFactory{

    public CSVDataFrameFactory(String fileName) {
        super(fileName);
    }

    @Override
    public DataFrame createDataFrame() {
        CSVDataFrame dataFrame = null;
        return dataFrame;
    }
}
