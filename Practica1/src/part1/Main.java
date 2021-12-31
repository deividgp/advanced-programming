package part1;

public class Main {
    public static <T> void main(String[] args){
        CSVDataFrameFactory<T> csvFactory = new CSVDataFrameFactory<T>();
        csvFactory.createDataFrame("cities.csv");
//        JSONDataFrameFactory<T> jsonFactory = new JSONDataFrameFactory<T>();
//        jsonFactory.createDataFrame("cities.json");
    }
}
