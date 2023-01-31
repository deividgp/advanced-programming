package decorator;

import actor.Actor;
import actor.ActorImpl;
import message.Message;

public class EncryptionDecorator extends ActorDecorator {

    public EncryptionDecorator(Actor actor){
        super(actor);
    }

    public void add(Message message) {
        super.getActor().add(message);
    }

    @Override
    public void process(Message message) {

    }
}
