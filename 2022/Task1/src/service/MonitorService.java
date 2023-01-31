package service;

import actor.Actor;
import actor.ActorContext;
import actor.ActorListener;
import actor.ActorProxy;
import message.Message;

import java.sql.Array;
import java.util.*;

public class MonitorService implements ActorListener {
    private final ActorContext actorContext = ActorContext.getInstance();
    private static final MonitorService monitorService = new MonitorService();
    private Map<TrafficLevel, Set<String>> traffic = new HashMap<>();
    private Map<String, List<Message>> sentMessages = new HashMap<>();
    private Map<String, List<Message>> receivedMessages = new HashMap<>();
    private Map<String, List<EventType>> events = new HashMap<>();
    private Map<String, Integer> numberOfMessages = new HashMap<>();
    private Map<String, Integer> numberProcessedMessages = new HashMap<>();

    public MonitorService() {
        this.traffic.put(TrafficLevel.LOW, new HashSet<>());
        this.traffic.put(TrafficLevel.MEDIUM, new HashSet<>());
        this.traffic.put(TrafficLevel.HIGH, new HashSet<>());
    }

    public static MonitorService getInstance() {
        return monitorService;
    }

    public void monitorActor(String actorName) {
        Actor users = actorContext.lookup(actorName);
        users.getListeners().add(this);
    }

    public void unmonitorActor(String actorName) {
        Actor users = actorContext.lookup(actorName);
        users.getListeners().remove(this);
    }

    public void monitorAllActors() {
        Set<String> actorsNames = actorContext.getNames();
        for (String name : actorsNames) {
            Actor users = actorContext.lookup(name);
            users.getListeners().add(this);
            if (numberOfMessages.get(name) == null) initializeListActor(name);
        }
    }

    @Override
    public void update(EventType eventType, Actor actor, Message message) {
        this.updateEvent(eventType, actor.getName());
        this.updateMessagesReceived(actor.getName(), message);
        this.updateNumberMessages(actor.getName());
    }

    public void update(EventType eventType, Actor actor) {
        switch (eventType){
            case PROCESSEDMESSAGE -> updateProcessedMessages(actor.getName());
            case FINALIZATION ->emptyActorTraffic(EventType.FINALIZATION, actor.getName());
            case ERROR -> emptyActorTraffic(EventType.ERROR, actor.getName());
            case CREATED -> initializeListActor(actor.getName());
        }
        this.updateEvent(eventType, actor.getName());
    }

    public void updateEvent(EventType eventType, String actorName) {
        this.events.get(actorName).add(eventType);
    }

    private void updateNumberMessages(String actorName) {
        int numberOfMessages = actorContext.lookup(actorName).getActor().getQueue().size();
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
        updateEvent(eventType, actorName);
        this.numberOfMessages.put(actorName, 0);
        this.updateNumberMessages(actorName);
    }
}
