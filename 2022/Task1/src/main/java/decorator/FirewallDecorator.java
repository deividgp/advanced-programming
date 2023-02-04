package decorator;

import actor.Actor;
import actor.ActorContext;
import actor.ActorImpl;
import message.Message;
import message.QuitMessage;
import org.json.JSONException;

public class FirewallDecorator extends ActorDecorator {

    public FirewallDecorator(Actor actor){
        super(actor);
    }

    @Override
    public void process(Message message) throws JSONException {
        if(message instanceof QuitMessage){
            this.getActor().process(message);
            return;
        }

        ActorContext actorContext = ActorContext.getInstance();
        String actorName = null;
        try {
            actorName = message.getFrom().getActor().getName();
        }catch (NullPointerException e) {

        }
        if (actorContext.lookup(actorName) != null) {
            this.getActor().process(message);
        }
    }
}
