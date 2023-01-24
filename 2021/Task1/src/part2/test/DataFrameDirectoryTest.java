package part2.test;

import org.junit.jupiter.api.Test;
import part1.DataFrame;
import part1.JSONDataFrameFactory;
import part1.TXTDataFrameFactory;
import part2.DataFrameDirectory;
import part5.AverageVisitor;

import static org.junit.jupiter.api.Assertions.*;
import static part1.DataFrameFile.compare;

class DataFrameDirectoryTest {

    @Test
    void at() {
        DataFrameDirectory directory = new DataFrameDirectory("directory");
        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame data2 = jsonFactory.createDataFrame("cities.json", "");
        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
        DataFrame data3 = txtFactory.createDataFrame("cities.txt", "quote");
        directory.addDataFrame(data2);
        directory.addDataFrame(data3);
        directory.at(0, "LatD");
    }

    @Test
    void iat() {
        DataFrameDirectory directory = new DataFrameDirectory("directory");
        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame data2 = jsonFactory.createDataFrame("cities.json", "");
        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
        DataFrame data3 = txtFactory.createDataFrame("cities.txt", "quote");
        directory.addDataFrame(data2);
        directory.addDataFrame(data3);
        directory.iat(0, 0);
    }

    @Test
    void columns() {
        DataFrameDirectory directory = new DataFrameDirectory("directory");
        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame data2 = jsonFactory.createDataFrame("cities.json", "");
        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
        DataFrame data3 = txtFactory.createDataFrame("cities.txt", "quote");
        directory.addDataFrame(data2);
        directory.addDataFrame(data3);
        directory.columns();
    }

    @Test
    void size() {
        DataFrameDirectory directory = new DataFrameDirectory("directory");
        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame data2 = jsonFactory.createDataFrame("cities.json", "");
        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
        DataFrame data3 = txtFactory.createDataFrame("cities.txt", "quote");
        directory.addDataFrame(data2);
        directory.addDataFrame(data3);
        directory.size();
    }

    @Test
    void sort() {
        DataFrameDirectory directory = new DataFrameDirectory("directory");
        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame data2 = jsonFactory.createDataFrame("cities.json", "");
        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
        DataFrame data3 = txtFactory.createDataFrame("cities.txt", "quote");
        directory.addDataFrame(data2);
        directory.addDataFrame(data3);
        directory.sort("LatD", (o1, o2) -> compare(o1, o2, 1));
    }

    @Test
    void query() {
        DataFrameDirectory directory = new DataFrameDirectory("directory");
        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame data2 = jsonFactory.createDataFrame("cities.json", "");
        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
        DataFrame data3 = txtFactory.createDataFrame("cities.txt", "quote");
        directory.addDataFrame(data2);
        directory.addDataFrame(data3);
        directory.query(e -> (Double)e.get("LatD") > 39);
    }

    @Test
    void getColumnValues() {
        DataFrameDirectory directory = new DataFrameDirectory("directory");
        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame data2 = jsonFactory.createDataFrame("cities.json", "");
        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
        DataFrame data3 = txtFactory.createDataFrame("cities.txt", "quote");
        directory.addDataFrame(data2);
        directory.addDataFrame(data3);
        directory.getColumnValues("LatD");
    }

    @Test
    void accept() {
        DataFrameDirectory directory = new DataFrameDirectory("directory");
        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame data2 = jsonFactory.createDataFrame("cities.json", "");
        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
        DataFrame data3 = txtFactory.createDataFrame("cities.txt", "quote");
        directory.addDataFrame(data2);
        directory.addDataFrame(data3);
        directory.accept(new AverageVisitor("LatD"));
    }

    @Test
    void iterator() {
        DataFrameDirectory directory = new DataFrameDirectory("directory");
        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame data2 = jsonFactory.createDataFrame("cities.json", "");
        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
        DataFrame data3 = txtFactory.createDataFrame("cities.txt", "quote");
        directory.addDataFrame(data2);
        directory.addDataFrame(data3);
    }
}