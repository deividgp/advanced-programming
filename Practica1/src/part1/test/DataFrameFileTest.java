package part1.test;

import org.junit.jupiter.api.Test;
import part1.CSVDataFrameFactory;
import part1.DataFrame;
import part1.JSONDataFrameFactory;
import part1.TXTDataFrameFactory;
import part2.DataFrameDirectory;
import part5.AverageVisitor;

import static org.junit.jupiter.api.Assertions.*;
import static part1.DataFrameFile.compare;

class DataFrameFileTest {

    @Test
    void at() {
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        data.at(0, "LatD");
    }

    @Test
    void iat() {
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        data.iat(0, 0);
    }

    @Test
    void columns() {
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        data.columns();
    }

    @Test
    void size() {
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        data.size();
    }

    @Test
    void sort() {
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        data.sort("LatD", (o1, o2) -> compare(o1, o2, 1));
    }

    @Test
    void query() {
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        data.query(e -> (Double)e.get("LatD") > 39);
    }

    @Test
    void getColumnValues() {
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        data.getColumnValues("LatD");
    }

    @Test
    void iterator() {
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        data.iterator();
    }

    @Test
    void accept() {
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        data.accept(new AverageVisitor("LatD"));
    }
}