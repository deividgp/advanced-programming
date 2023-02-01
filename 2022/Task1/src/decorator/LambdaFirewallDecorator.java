package decorator;

import actor.Actor;
import message.Message;
import message.QuitMessage;

import java.util.function.Predicate;

public class LambdaFirewallDecorator extends ActorDecorator {
    private Predicate<String> filter;

    public LambdaFirewallDecorator(Actor actor){
        super(actor);
    }

    public void addClosureMessage(Predicate<String> filter) {
        this.filter = filter;
    }

    @Override
    public void process(Message message) {
        if (message instanceof QuitMessage || filter.test(message.getText()))
            super.getActor().process(message);
    }
}
