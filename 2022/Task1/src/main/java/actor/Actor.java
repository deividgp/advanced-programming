package actor;

import message.Message;
import org.json.JSONException;

public interface Actor {
    void add(Message message);
    void process(Message message) throws JSONException;
}
