package todo.springboot2.hello.rabbitmq;

import todo.springboot2.hello.model.User;
import todo.springboot2.hello.rabbitmq.fanout.FanoutSender;
import todo.springboot2.hello.rabbitmq.hello.HelloSender;
import todo.springboot2.hello.rabbitmq.many.NeoSender1;
import todo.springboot2.hello.rabbitmq.many.NeoSender2;
import todo.springboot2.hello.rabbitmq.object.ObjectSender;
import todo.springboot2.hello.rabbitmq.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTests {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private NeoSender1 neoSender1;

    @Autowired
    private NeoSender2 neoSender2;

    @Autowired
    private ObjectSender objectSender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void hello() throws Exception {
        helloSender.send();
    }

    @Test
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender1.send(i);
        }
    }

    @Test
    public void manyToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender1.send(i);
            neoSender2.send(i);
        }
    }

    @Test
    public void sendObject() throws Exception {
        User user=new User();
        user.setName("neo");
        user.setPassword("123456");
        objectSender.send(user);
    }


    @Test
    public void topic() throws Exception {
        topicSender.send();
    }

    @Test
    public void topic1() throws Exception {
        topicSender.send1();
    }

    @Test
    public void topic2() throws Exception {
        topicSender.send2();
    }


    @Test
    public void fanoutSender() throws Exception {
        fanoutSender.send();
    }

}
