package decorator;

import actor.Actor;
import message.Message;

import java.util.function.Predicate;

public class LambdaFirewallDecorator extends ActorDecorator {
    private Predicate<String> filter;

    public LambdaFirewallDecorator(Actor actor){
        super(actor);
    }

    public void addClosureMessage(Predicate<String> filter) {
        this.filter = filter;
    }

    public void add(Message message) {
        super.getActor().add(message);
    }

    @Override
    public void process(Message message) {

    }
}
