package actor;

import message.Message;
import service.EventType;

public interface ActorListener {
    void update(EventType eventType, ActorImpl actor, Message message);
    void update(EventType eventType, ActorImpl actor);
}
