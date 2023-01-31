package message;

import actor.ActorProxy;

public class GetInsultMessage extends Message {
    public GetInsultMessage(ActorProxy from) {
        super(from, null);
    }
}
