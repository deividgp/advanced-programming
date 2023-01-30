package decorator;

import actor.Actor;
import actor.ActorImpl;
import message.Message;

public abstract class ActorDecorator extends ActorImpl {
    private Actor actor;

    public ActorDecorator(Actor actor){
        super();
        this.actor = actor;
    }
}
