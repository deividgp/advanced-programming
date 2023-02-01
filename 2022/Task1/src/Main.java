import actor.ActorContext;
import actor.ActorProxy;
import actor.HelloWorldActor;
import actor.InsultActor;
import decorator.EncryptionDecorator;
import decorator.FirewallDecorator;
import decorator.LambdaFirewallDecorator;
import dynamic.DynamicProxy;
import dynamic.InsultService;
import dynamic.InsultServiceImpl;
import message.AddInsultMessage;
import message.GetInsultMessage;
import message.Message;
import message.QuitMessage;
import service.MonitorService;
import service.TrafficLevel;

import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
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

        ActorProxy monitor1 = ActorContext.getInstance().spawnActor("Test 5 ", new HelloWorldActor());
        ActorProxy monitor2 = ActorContext.getInstance().spawnActor("Test 6 ", new HelloWorldActor());
        ActorProxy monitor3 = ActorContext.getInstance().spawnActor("Test 7 ", new HelloWorldActor());
        ActorProxy monitor4 = ActorContext.getInstance().spawnActor("Test 8 ", new HelloWorldActor());
        monitor1.stop();
        monitor2.stop();
        monitor3.stop();
        monitor4.stop();
        MonitorService.getInstance().monitorAllActors();

        monitor1.start();
        monitor2.start();
        monitor3.start();
        monitor4.start();

        monitor1.stop();
        monitor2.stop();
        monitor3.stop();
        monitor4.stop();

        monitor1.send(new Message(hello, "hola"));

        System.out.println(MonitorService.getInstance().getTraffic().get(TrafficLevel.LOW).toString());
        System.out.println(MonitorService.getInstance().getReceivedMessages().get("Test 5 "));
        System.out.println(MonitorService.getInstance().getNumberOfMessages().get("Test 5 "));
        System.out.println(MonitorService.getInstance().getReceivedMessages().get("Test 6 "));
        System.out.println(MonitorService.getInstance().getNumberOfMessages().get("Test 6 "));
    }

    public void Ring(){

    }

    public void PingPong(){

    }
}