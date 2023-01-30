package message;

import actor.Actor;
import actor.ActorProxy;

public class Message {
    private ActorProxy from;
    private String text;

    public Message(ActorProxy from, String text){
        this.from = from;
        this.text = text;
    }

    public Message(ActorProxy from){
        this.from = from;
        this.text = null;
    }

    public Message(){
        this.from = null;
        this.text = null;
    }

    public ActorProxy getFrom() {
        return from;
    }

    public void setFrom(ActorProxy from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
