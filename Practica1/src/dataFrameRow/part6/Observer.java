package dataFrameRow.part6;

import java.util.ArrayList;
import java.util.List;

public abstract class Observer {
    List<String> logger;

    public Observer(){
        logger = new ArrayList<>();
    }

    public void update(String value){
        logger.add(value);
    }

    public List<String> getLogger() {
        return logger;
    }
}
