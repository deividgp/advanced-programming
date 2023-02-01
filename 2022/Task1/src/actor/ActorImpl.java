package actor;

import message.Message;
import service.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class ActorImpl implements Actor, Runnable {
    private Queue<Message> messages = new LinkedBlockingQueue<>();
    private Thread thread;
    private String name;
    private List<ActorListener> listeners = new ArrayList<>();

    @Override
    public void add(Message message) {
        this.messageProcessed();
        messages.add(message);
    }

    @Override
    public abstract void process(Message message);

    @Override
    public void run() {
        while(!thread.isInterrupted()){
            Message message = messages.poll();
            if (message != null) {
                process(message);
            }
        }
    }

    public void start() {
        this.notifyAllObservers(EventType.CREATED);
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        this.notifyAllObservers(EventType.FINALIZATION);
        thread.interrupt();
    }

    public void messageProcessed() {
        this.notifyAllObservers(EventType.PROCESSEDMESSAGE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Queue<Message> getMessages() {
        return messages;
    }

    public Thread getThread() {
        return thread;
    }

    public void notifyAllObservers(EventType eventType) {
        for (ActorListener observer : listeners) {
            observer.update(eventType, this);
        }
    }

    public void notifyAllObservers (EventType eventType , Message message) {
        for (ActorListener observer : listeners) {
            observer.update(eventType, this, message);
        }
    }

    public List<ActorListener> getListeners() {
        return listeners;
    }
}
