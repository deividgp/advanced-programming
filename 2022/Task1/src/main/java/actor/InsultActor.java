package actor;

import message.*;
import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;

public class InsultActor extends ActorImpl {
    List<String> insults = new LinkedList<>();

    @Override
    public void process(Message message) throws JSONException {
        this.messageProcessed();
        switch(message){
            case QuitMessage ignored -> super.stop();
            case GetInsultMessage ignored -> {
                ActorProxy actorProxy = message.getFrom();
                String text = insults.isEmpty() ? "Empty list" : insults.get(insults.size()-1);

                if (actorProxy.getActor() == this) {
                    actorProxy.add(new Message(actorProxy, text));
                } else {
                    actorProxy.send(new Message(actorProxy, text));
                }
            }
            case AddInsultMessage addInsultMessage -> insults.add(addInsultMessage.getText());
            case GetAlInsultsMessage ignored -> {
                ActorProxy actorProxy = message.getFrom();

                if (actorProxy.getActor() == this) {
                    actorProxy.add(new Message(actorProxy, insults.toString()));
                } else {
                    actorProxy.send(new Message(actorProxy, insults.toString()));
                }
            }
            case Message m -> System.out.println(m.getText());
        }
    }
}
