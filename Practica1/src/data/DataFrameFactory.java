package data;

import javax.xml.crypto.Data;

public abstract class DataFrameFactory {
    String fileName;

    public DataFrameFactory(String fileName){
        this.fileName = fileName;
    }

    public abstract DataFrame createDataFrame();
}
