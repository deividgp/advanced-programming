package message;

import actor.ActorProxy;

public class GetAlInsultsMessage extends Message {
    public GetAlInsultsMessage(ActorProxy from) {
        super(from, null);
    }
}
