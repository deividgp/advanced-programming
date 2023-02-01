package message;

import actor.ActorProxy;

public class GetAllInsultsMessage extends Message {
    public GetAllInsultsMessage(ActorProxy from) {
        super(from, null);
    }
}
