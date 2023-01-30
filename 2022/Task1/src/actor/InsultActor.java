package actor;

import message.Message;

import java.util.LinkedList;
import java.util.List;

public class InsultActor extends ActorImpl {
    List<String> insults = new LinkedList<>();


    @Override
    public void process(Message message) {

    }
}
