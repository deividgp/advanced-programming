package actor;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class ActorContext {
    private static ActorContext actorContext = new ActorContext();
    private HashMap<String, ActorImpl> actors = new HashMap<>();

    private ActorContext() {}

    public static ActorContext getInstance(){
        return actorContext;
    }

    public ActorProxy spawnActor(String name, ActorImpl actor){
        actors.put(name, actor);
        actor.setName(name);
        actor.start();
        return new ActorProxy(actor);
    }

    public ActorImpl lookup(String name){
        return actors.get(name);
    }

    public Set<String> getNames(){
        return actors.keySet();
    }
}
