
import part1.CSVDataFrameFactory;
import part1.DataFrame;
import part1.JSONDataFrameFactory;
import part1.TXTDataFrameFactory;
import part2.DataFrameDirectory;
import part4.MapReduce;
import part5.MinimumVisitor;
import part5.Visitor;
import part6.DataFrameInterceptor;
import part6.Observer;
import part6.Subject;

import java.util.*;

import static part1.DataFrameFile.compare;

/**
 * Main class for testing purposes.
 */
public class Main {
    /**
     * Main method.
     * @param args args.
     */
    public static void main(String[] args){
        CSVDataFrameFactory csvFactory = new CSVDataFrameFactory();
        DataFrame data = csvFactory.createDataFrame("cities.csv", "");
        System.out.println("Ascending" + data.sort("LatD", (o1, o2) -> compare(o1, o2, 1)));
        System.out.println("Descending" + data.sort("LatD", (o1, o2) -> compare(o1, o2, -1)));
        JSONDataFrameFactory jsonFactory = new JSONDataFrameFactory();
        DataFrame data2 = jsonFactory.createDataFrame("cities.json", "");
        TXTDataFrameFactory txtFactory = new TXTDataFrameFactory();
        DataFrame data3 = txtFactory.createDataFrame("cities.txt", "quote");
        List<DataFrame> aux = new LinkedList<>();
        aux.add(data);
        MapReduce map = new MapReduce(aux);
        map.ReduceMaxMinSum(map.MapColumn("LatD"), Double::max);
        map.ReduceAvg(map.MapColumn("LatD"));
        Iterator<LinkedList<HashMap<String, Object>>> ite = data.iterator();
        while (ite.hasNext()){
            LinkedList<HashMap<String, Object>> o = ite.next();
            System.out.println(o);
        }
        data.query(e -> (Double)e.get("LatD") > 39);
        System.out.println("Column values: "+data.getColumnValues("LatD"));
        Visitor visit = new MinimumVisitor("LatD");
        System.out.println("Visitor: " + data.accept(visit));

        LinkedList<DataFrame> list = new LinkedList<>();
        list.add(data);

        Subject subject = new Subject();
        DataFrame dataFrameProxy = (DataFrame) DataFrameInterceptor.newInstance(new DataFrameDirectory("directori", list), subject);
        dataFrameProxy.query(e -> (Double)e.get("LatD") > 20);
        dataFrameProxy.columns();
        List<Observer> observers = subject.getObservers();
        for (Observer observer : observers){
            System.out.println(observer.getLogger());
        }
    }
}
