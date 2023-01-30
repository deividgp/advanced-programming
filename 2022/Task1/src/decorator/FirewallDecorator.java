package decorator;

import actor.Actor;
import actor.ActorContext;
import actor.ActorImpl;
import message.Message;

public class FirewallDecorator extends ActorDecorator {

    public FirewallDecorator(Actor actor){
        super(actor);
    }

    @Override
    public void process(Message message) {
        ActorContext actorContext = ActorContext.getInstance();
        //if(actorContext.lookup(message.getFrom().getActor().)
    }
}
