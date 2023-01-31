package actor;

import message.Message;
import message.QuitMessage;

public class PingPongActor extends ActorImpl {
    @Override
    public void process(Message message) {
        switch(message){
            case QuitMessage q -> super.stop();
            case Message m -> System.out.println(m.getText());
        }
    }
}
