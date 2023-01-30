package actor;

import message.Message;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class ActorProxy {
    private Actor actor;
    private Queue<Message> messages = new LinkedBlockingDeque<>();

    public ActorProxy(Actor actor){
        this.actor = actor;
    }

    public void send(Message message){
        messages.add(message);
    }

    public Message receive(){
        return messages.poll();
    }

    public Actor getActor() {
        return actor;
    }

    public Queue<Message> getMessages() {
        return messages;
    }
}
