import actor.ActorContext;
import actor.ActorProxy;
import actor.HelloWorldActor;
import actor.InsultActor;
import decorator.FirewallDecorator;
import decorator.LambdaFirewallDecorator;
import dynamic.DynamicProxy;
import dynamic.InsultService;
import dynamic.InsultServiceImpl;
import message.AddInsultMessage;
import message.GetInsultMessage;
import message.Message;
import message.QuitMessage;

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

        /*LambdaFirewallDecorator lambdaFirewallDecorator = new LambdaFirewallDecorator(new FirewallDecorator(new HelloWorldActor()));
        Predicate<String> containsA = x -> x.startsWith("A");
        lambdaFirewallDecorator.addClosureMessage(containsA);
        ActorProxy firewall = ActorContext.getInstance().spawnActor("Test 3", new EncryptionDecorator(lambdaFirewallDecorator));
        firewall.send(new Message(hello, "Hola"));
        firewall.send(new Message(hello, "AAHola"));
        firewall.send(new QuitMessage());*/

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
    }

    public void Ring(){

    }

    public void PingPong(){

    }
}