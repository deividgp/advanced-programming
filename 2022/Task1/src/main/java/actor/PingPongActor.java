package actor;

import message.Message;
import message.QuitMessage;

public class PingPongActor extends ActorImpl {
    private int numberOfCommunications = 0;
    private static final int TOTALNUMBERCOMMUNICATIONS = 0;
    private static final int MILISECONDSTOSLEEP = 100;

    @Override
    public void process(Message message) {
        try {
            Thread.sleep(MILISECONDSTOSLEEP);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.messageProcessed();
        numberOfCommunications++;
        System.out.println("Message: " + message.getText()
                + " From: " + message.getFrom().getActor().getName());
        message.getFrom().send(
                new Message(ActorContext.getInstance().lookup(this.getName()), message.getText()));
        if (numberOfCommunications > TOTALNUMBERCOMMUNICATIONS) {
            this.stop();
            message.getFrom().send(
                    new Message(ActorContext.getInstance().lookup(this.getName()), message.getText()));
        }
    }
}
