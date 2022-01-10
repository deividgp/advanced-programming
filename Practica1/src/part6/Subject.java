package part6;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Observers management class.
 */
public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    /**
     * Observers getter.
     * @return List of observers.
     */
    public List<Observer> getObservers() {
        return observers;
    }

    /**
     * Add an observer.
     * @param observer Observer to add.
     */
    public void subscribe(Observer observer){
        observers.add(observer);
    }

    /**
     * Remove an observer.
     * @param observer Observer to delete.
     */
    public void unsubscribe(Observer observer){
        observers.remove(observer);
    }

    /**
     * Updates the observers.
     * @param methodName Method that just been executed.
     */
    public void notify(String methodName){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String now = formatter.format(new Date());

        for(Observer observer : observers){
            if(observer instanceof LogObserver){
                observer.update("Method " + methodName + " has been executed. Time: " + now);
            }else if(methodName.equals("query") && observer instanceof QueryObserver){
                observer.update("Query has been executed. Time: " + now);
            }
        }
    }
}
