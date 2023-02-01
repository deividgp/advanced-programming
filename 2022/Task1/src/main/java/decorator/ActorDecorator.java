package decorator;

import actor.Actor;
import actor.ActorImpl;
import message.Message;

import java.util.Queue;

public abstract class ActorDecorator extends ActorImpl {
    private Actor actor;

    public ActorDecorator(Actor actor){
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }
}
