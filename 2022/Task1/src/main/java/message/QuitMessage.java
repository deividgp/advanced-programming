package message;

import actor.ActorProxy;

public class QuitMessage extends Message {
    public QuitMessage(){
        super();
    }

    public QuitMessage(ActorProxy from) {
        super(from, null);
    }
}
