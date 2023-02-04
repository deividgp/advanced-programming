package service;

import actor.*;
import io.socket.client.Socket;
import message.Message;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class MonitorService implements ActorListener {
    private final ActorContext actorContext = ActorContext.getInstance();
    private final Socket actorSocket = ActorContext.getActorSocket();
    private static final MonitorService monitorService = new MonitorService();
    private final Map<TrafficLevel, Set<String>> traffic = new HashMap<>();
    private final Map<String, List<Message>> sentMessages = new HashMap<>();
    private final Map<String, List<Message>> receivedMessages = new HashMap<>();
    private final Map<String, List<EventType>> events = new HashMap<>();
    private final Map<String, Integer> numberOfMessages = new HashMap<>();
    private final Map<String, Integer> numberProcessedMessages = new HashMap<>();

    public MonitorService() {
        this.traffic.put(TrafficLevel.LOW, new HashSet<>());
        this.traffic.put(TrafficLevel.MEDIUM, new HashSet<>());
        this.traffic.put(TrafficLevel.HIGH, new HashSet<>());
    }

    public static MonitorService getInstance() {
        return monitorService;
    }

    public void monitorActor(String actorName) {
        ActorProxy users = actorContext.lookup(actorName);
        users.getActor().getListeners().add(this);
    }

    public void unmonitorActor(String actorName) {
        ActorProxy users = actorContext.lookup(actorName);
        users.getActor().getListeners().add(this);
    }

    public void monitorAllActors() {
        Set<String> actorsNames = actorContext.getNames();
        for (String name : actorsNames) {
            ActorProxy users = actorContext.lookup(name);
            users.getActor().getListeners().add(this);
            if (numberOfMessages.get(name) == null) initializeListActor(name);
        }
    }

    @Override
    public void update(EventType eventType, ActorImpl actor, Message message) {
        events.get(actor.getName()).add(eventType);
        this.updateMessagesReceived(actor.getName(), message);
        this.updateNumberMessages(actor.getName());
    }

    public void update(EventType eventType, ActorImpl actor) throws JSONException {
        JSONObject status = new JSONObject();
        status.put("name", actor.getName());
        switch (eventType){
            case PROCESSEDMESSAGE -> {
                System.out.println("Processed");
                status.put("status", "Processed");
                updateProcessedMessages(actor.getName());
            }
            case FINALIZED -> {
                System.out.println("Finalized");
                status.put("status", "Finalized");
                emptyActorTraffic(EventType.FINALIZED, actor.getName());
            }
            case ERROR -> {
                System.out.println("Error");
                status.put("status", "Error");
                emptyActorTraffic(EventType.ERROR, actor.getName());
            }
            case CREATED -> {
                System.out.println("Created");
                status.put("status", "Created");
                initializeListActor(actor.getName());
            }
        }
        events.get(actor.getName()).add(eventType);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        actorSocket.emit("update_status", status);
    }

    private void updateNumberMessages(String actorName) {
        int numberOfMessages = actorContext.lookup(actorName).getMessages().size();
        this.numberOfMessages.put(actorName, numberOfMessages);

        if (numberOfMessages <= 5) {
            this.traffic.get(TrafficLevel.LOW).add(actorName);
            this.traffic.get(TrafficLevel.MEDIUM).remove(actorName);
            return;
        }

        if (numberOfMessages <= 10) {
            this.traffic.get(TrafficLevel.MEDIUM).add(actorName);
            this.traffic.get(TrafficLevel.LOW).remove(actorName);
            this.traffic.get(TrafficLevel.HIGH).remove(actorName);
            return;
        }

        this.traffic.get(TrafficLevel.HIGH).add(actorName);
        this.traffic.get(TrafficLevel.MEDIUM).remove(actorName);
    }

    public void updateProcessedMessages(String actorName) {
        this.numberProcessedMessages.put(actorName,this.numberProcessedMessages.get(actorName) + 1);
    }

    public void updateMessagesReceived(String actorName, Message message) {
        this.receivedMessages.get(actorName).add(message);
        if(message.getFrom() != null)
            this.sentMessages.get(message.getFrom().getActor().getName()).add(message);
    }

    public void initializeListActor(String actorName) {
        this.events.put(actorName, new ArrayList<>());
        this.sentMessages.put(actorName, new ArrayList<>());
        this.receivedMessages.put(actorName, new ArrayList<>());
        this.numberOfMessages.put(actorName, 0);
        this.numberProcessedMessages.put(actorName, 0);
    }

    public void emptyActorTraffic(EventType eventType, String actorName) {
        events.get(actorName).add(eventType);
        this.numberOfMessages.put(actorName, 0);
        this.updateNumberMessages(actorName);
    }

    public Map<TrafficLevel, Set<String>> getTraffic() {
        return traffic;
    }

    public Map<String, List<Message>> getReceivedMessages() {
        return receivedMessages;
    }

    public Map<String, Integer> getNumberOfMessages() {
        return numberOfMessages;
    }
}
