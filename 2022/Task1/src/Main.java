import actor.ActorContext;
import actor.HelloWorldActor;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ActorContext actorContext1 = ActorContext.getInstance();
        actorContext1.spawnActor("helloworld", new HelloWorldActor());
    }

    public void Ring(){

    }

    public void PingPong(){

    }
}