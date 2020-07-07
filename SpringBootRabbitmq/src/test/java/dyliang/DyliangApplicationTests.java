package dyliang;

import dyliang.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class DyliangApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    void contextLoads() {
    }

    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
		System.out.println("创建完成");

        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
//
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",
                Binding.DestinationType.QUEUE,
                "amqpadmin.exchange",
                "amqp.#",
                null));

    }

    // 单播
    @Test
    public void testPutMessage() {
        //Message需要自己构造一个;定义消息体内容和消息头
        //rabbitTemplate.send(exchage,routeKey,message);

        //object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq；
        //rabbitTemplate.convertAndSend(exchage,routeKey,object);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","first message");
        map.put("data", Arrays.asList("hello world",123,true));
        //对象被默认序列化以后发送出去
        rabbitTemplate.convertAndSend("amqpadmin.exchange","amqp.#",new Book(1,"西游记"));

    }

    //接受数据,如何将数据自动的转为json发送出去
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("amqpadmin.queue");
        System.out.println(o.getClass());
        System.out.println(o);
        /*
        class dyliang.domain.Book
        dyliang.domain.Book@3d7fb838
         */
    }

    // 广播
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout",
                "",
                new Book(2,"红楼梦"));
    }

}
