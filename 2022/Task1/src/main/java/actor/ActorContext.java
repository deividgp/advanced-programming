package actor;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONException;
import service.MonitorService;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

public class ActorContext {
    private static ActorContext actorContext = new ActorContext();
    private HashMap<String, ActorProxy> actors = new HashMap<>();
    private static Socket actorSocket;

    static {
        try {
            actorSocket = IO.socket("http://localhost:3000").connect();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private ActorContext() {}

    public static ActorContext getInstance(){
        return actorContext;
    }

    public static Socket getActorSocket(){ return actorSocket; }

    public ActorProxy spawnActor(String name, ActorImpl actor) throws JSONException {
        actor.setName(name);
        actor.getListeners().add(MonitorService.getInstance());
        ActorProxy proxy = new ActorProxy(actor);
        actors.put(name, proxy);
        actorSocket.emit("new_actor", name);
        actor.start();
        return proxy;
    }

    public ActorProxy lookup(String name){
        return actors.get(name);
    }

    public Set<String> getNames(){
        return actors.keySet();
    }
}
