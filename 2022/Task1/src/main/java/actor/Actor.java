package actor;

import message.Message;

public interface Actor {
    void add(Message message);
    void process(Message message);
}
