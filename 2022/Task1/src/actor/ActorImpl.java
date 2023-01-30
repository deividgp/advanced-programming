package actor;

import message.Message;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class ActorImpl implements Actor, Runnable {
    private Queue<Message> messages = new LinkedBlockingQueue<>();
    private Thread thread;

    @Override
    public void add(Message message) {
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
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }
}
