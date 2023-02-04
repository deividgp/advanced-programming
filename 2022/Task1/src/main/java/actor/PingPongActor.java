package actor;

import message.Message;
import message.QuitMessage;
import org.json.JSONException;

public class PingPongActor extends ActorImpl {
    private int numberOfCommunications = 0;
    private static final int TOTALNUMBERCOMMUNICATIONS = 50;

    @Override
    public void process(Message message) throws JSONException {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.messageProcessed();

        if(message instanceof QuitMessage){
            super.stop();
        }

        numberOfCommunications++;

        if(numberOfCommunications < TOTALNUMBERCOMMUNICATIONS){
            System.out.println("Message: " + message.getText()
                    + " From: " + message.getFrom().getActor().getName());
            message.getFrom().send(
                    new Message(ActorContext.getInstance().lookup(this.getName()), message.getText()));
            return;
        }

        message.getFrom().send(
                new QuitMessage(ActorContext.getInstance().lookup(this.getName())));
    }
}
