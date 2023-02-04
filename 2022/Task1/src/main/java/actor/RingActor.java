package actor;

import message.Message;
import message.QuitMessage;
import org.json.JSONException;

public class RingActor extends ActorImpl {
    ActorImpl actor;

    public RingActor(ActorImpl actor)
    {
        super();
        this.actor = actor;
    }

    public RingActor(){
        super();
    }

    public ActorImpl getActor() {
        return actor;
    }

    public void setActor(ActorImpl actor) {
        this.actor = actor;
    }

    @Override
    public void process(Message message) throws JSONException {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.messageProcessed();
        actor.add(message);

        if(message instanceof QuitMessage){
            super.stop();
            return;
        }

        System.out.println("Ring actor: " + message.getText());
    }
}
