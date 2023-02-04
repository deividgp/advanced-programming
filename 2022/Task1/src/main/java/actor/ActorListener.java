package actor;

import message.Message;
import org.json.JSONException;
import service.EventType;

public interface ActorListener {
    void update(EventType eventType, ActorImpl actor, Message message);
    void update(EventType eventType, ActorImpl actor) throws JSONException;
}
