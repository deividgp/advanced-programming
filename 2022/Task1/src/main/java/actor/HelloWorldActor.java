package actor;

import message.Message;
import message.QuitMessage;

public class HelloWorldActor extends ActorImpl {
    @Override
    public void process(Message message) {
        switch(message){
            case QuitMessage q -> {
                if(super.getThread() != null)
                    super.stop();
            }
            case Message m -> System.out.println(m.getText());
        }
    }
}
