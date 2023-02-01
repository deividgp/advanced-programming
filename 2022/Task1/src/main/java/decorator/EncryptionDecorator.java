package decorator;

import actor.Actor;
import actor.ActorImpl;
import message.Message;

public class EncryptionDecorator extends ActorDecorator {

    public EncryptionDecorator(Actor actor){
        super(actor);
    }

    @Override
    public void process(Message message) {
        super.getActor().process(message);
    }
}
