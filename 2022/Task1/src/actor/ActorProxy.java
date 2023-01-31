package actor;

import message.Message;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class ActorProxy implements Actor {
    private ActorImpl actor;
    private Queue<Message> messages = new LinkedBlockingDeque<>();

    public ActorProxy(ActorImpl actor){
        this.actor = actor;
    }

    public void send(Message message){
        this.actor.add(message);
    }

    public Message receive(){
        while (messages.isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return messages.poll();
    }

    public Actor getActor() {
        return actor;
    }

    public Queue<Message> getMessages() {
        return messages;
    }

    @Override
    public void add(Message message) {
        messages.add(message);
    }

    @Override
    public void process(Message message) {

    }
}
