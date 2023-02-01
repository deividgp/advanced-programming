package message;

import actor.ActorProxy;

public class AddInsultMessage extends Message {
    public AddInsultMessage(ActorProxy from, String text) {
        super(from, text);
    }
}
