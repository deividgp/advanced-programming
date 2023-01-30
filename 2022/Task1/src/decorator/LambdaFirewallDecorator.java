package decorator;

import actor.Actor;
import message.Message;

public class LambdaFirewallDecorator extends ActorDecorator {

    public LambdaFirewallDecorator(Actor actor){
        super(actor);
    }

    @Override
    public void process(Message message) {

    }
}
