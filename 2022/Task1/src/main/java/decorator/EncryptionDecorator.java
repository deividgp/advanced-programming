package decorator;

import actor.Actor;
import actor.ActorImpl;
import message.Message;
import org.json.JSONException;

public class EncryptionDecorator extends ActorDecorator {

    public EncryptionDecorator(Actor actor){
        super(actor);
    }

    @Override
    public void process(Message message) throws JSONException {
        super.getActor().process(message);
    }
}
