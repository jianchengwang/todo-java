package core.concurrency.other.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @author jianchengwang
 * @date 2023/5/4
 */
public class HelloActor extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        System.out.println("HelloActor onReceive: " + o);
    }

    public static void main(String[] args) {
        //创建Actor系统
        ActorSystem system = ActorSystem.create("HelloSystem");
        //创建HelloActor
        ActorRef helloActor = system.actorOf(Props.apply((HelloActor.class)));
        //发送消息给HelloActor
        helloActor.tell("Actor");
    }
}
