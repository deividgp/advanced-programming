import actor.*;
import decorator.EncryptionDecorator;
import decorator.FirewallDecorator;
import decorator.LambdaFirewallDecorator;
import dynamic.DynamicProxy;
import dynamic.InsultService;
import dynamic.InsultServiceImpl;
import message.*;
import org.json.JSONException;
import org.json.JSONObject;
import service.MonitorService;
import service.TrafficLevel;

import java.util.Set;
import java.util.function.Predicate;

import io.socket.client.Socket;

public class Main {
    public static void main(String[] args) throws InterruptedException, JSONException {
        Socket();
        Testing();
    }

    private static void Socket() {
        Socket socket = ActorContext.getActorSocket();

        socket.on("create_actor", args1 -> {
            JSONObject json = (JSONObject)args1[0];
            String actorName;
            int actorType;

            try {
                actorName = json.getString("actorName");
                actorType = json.getInt("actorType");

                ActorImpl actor;

                switch (actorType) {
                    case 1 -> actor = new HelloWorldActor();
                    case 2 -> actor = new InsultActor();
                    case 3 -> actor = new PingPongActor();
                    case 4 -> actor = new RingActor();
                    default -> {
                        return;
                    }
                }
                ActorContext.getInstance().spawnActor(actorName, actor);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

        socket.on("send_actors", args1 -> {
            Set<String> actors = ActorContext.getInstance().getNames();
            socket.emit("get_actors", actors);
        });

        socket.on("send_message", args1 -> {
            JSONObject json = (JSONObject)args1[0];
            String text;
            String from;
            String to;
            int messageType;

            try {
                text = json.getString("message");
                from = json.getString("from");
                to = json.getString("to");
                messageType = json.getInt("messageType");

                ActorProxy proxyTo = ActorContext.getInstance().lookup(to);
                ActorProxy proxyFrom = ActorContext.getInstance().lookup(from);
                if(proxyTo == null || proxyFrom == null) return;

                Message message = switch (messageType) {
                    case 1 -> new Message(proxyFrom, text);
                    case 2 -> new AddInsultMessage(proxyFrom, text);
                    case 3 -> new GetAlInsultsMessage(proxyFrom);
                    case 4 -> new GetInsultMessage(proxyFrom);
                    case 5 -> new QuitMessage();
                    default -> null;
                };

                proxyTo.send(message);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void PingPong() throws JSONException {
        ActorProxy proxyActor1 = ActorContext.getInstance().spawnActor("pingpong1", new PingPongActor());
        ActorProxy proxyActor2 = ActorContext.getInstance().spawnActor("pingpong2", new PingPongActor());
        proxyActor1.send(new Message(proxyActor2, "Hola"));
    }

    private static void Ring() throws InterruptedException, JSONException {
        RingActor r5 = new RingActor();
        RingActor r4 = new RingActor(r5);
        RingActor r3 = new RingActor(r4);
        RingActor r2 = new RingActor(r3);
        RingActor r1 = new RingActor(r2);
        r5.setActor(r1);
        ActorContext.getInstance().spawnActor("Ring 1", r1);
        ActorContext.getInstance().spawnActor("Ring 2", r2);
        ActorContext.getInstance().spawnActor("Ring 3", r3);
        ActorContext.getInstance().spawnActor("Ring 4", r4);
        ActorProxy ring = ActorContext.getInstance().spawnActor("Ring 5", r5);
        ring.send(new Message(null, "Hola Ring"));
        Thread.sleep(2000);
        ring.send(new QuitMessage());
    }

    private static void Testing() throws InterruptedException, JSONException {
        ActorProxy hello = ActorContext.getInstance().spawnActor("Test 1 ", new HelloWorldActor());
        hello.send(new Message(null, "Hello World"));
        hello.send(new QuitMessage());

        ActorProxy insult = ActorContext.getInstance().spawnActor("Test 2", new InsultActor());
        insult.send(new AddInsultMessage(insult, "Pedro"));
        insult.send(new GetInsultMessage(insult));
        System.out.println(insult.receive().getText());
        insult.send(new QuitMessage());

        LambdaFirewallDecorator lambdaFirewallDecorator = new LambdaFirewallDecorator(new FirewallDecorator(new HelloWorldActor()));
        Predicate<String> containsA = x -> x.startsWith("A");
        lambdaFirewallDecorator.addClosureMessage(containsA);
        ActorProxy firewall = ActorContext.getInstance().spawnActor("Test 3", new EncryptionDecorator(lambdaFirewallDecorator));
        firewall.send(new Message(hello, "Hola"));
        firewall.send(new Message(hello, "AAHola"));
        firewall.send(new QuitMessage());

        try {
            ActorProxy actor = ActorContext.getInstance().spawnActor("Test 4", new InsultActor());
            InsultService insulter = (InsultService) DynamicProxy.intercept(new InsultServiceImpl(), actor);
            insulter.addInsult("hello");
            insulter.addInsult("hello");
            insulter.addInsult("hello");
            System.out.println(insulter.getAllInsults());
            actor.send(new QuitMessage());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        ActorProxy monitor1 = ActorContext.getInstance().spawnActor("Test 5", new HelloWorldActor());
        ActorProxy monitor2 = ActorContext.getInstance().spawnActor("Test 6", new HelloWorldActor());
        ActorProxy monitor3 = ActorContext.getInstance().spawnActor("Test 7", new HelloWorldActor());
        ActorProxy monitor4 = ActorContext.getInstance().spawnActor("Test 8", new HelloWorldActor());

        monitor1.send(new Message(hello, "holahola"));

        Thread.sleep(2000);

        monitor1.send(new QuitMessage());
        monitor2.send(new QuitMessage());
        monitor3.send(new QuitMessage());
        monitor4.send(new QuitMessage());

        Thread.sleep(2000);

        System.out.println(MonitorService.getInstance().getTraffic().get(TrafficLevel.LOW).toString());
        System.out.println(MonitorService.getInstance().getTraffic().get(TrafficLevel.MEDIUM).toString());
        System.out.println(MonitorService.getInstance().getTraffic().get(TrafficLevel.HIGH).toString());
        System.out.println(MonitorService.getInstance().getNumberOfMessages().get("Test 5"));
        System.out.println(MonitorService.getInstance().getReceivedMessages().get("Test 6"));
        System.out.println(MonitorService.getInstance().getNumberOfMessages().get("Test 6"));
        PingPong();
        Ring();
    }
}