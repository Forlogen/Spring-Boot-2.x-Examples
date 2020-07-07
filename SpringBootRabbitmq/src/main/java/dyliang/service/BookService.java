package dyliang.service;

import dyliang.domain.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "Forlogen.book")
    public void receive(Book book){
        System.out.println("receive message: " + book);
    }

    @RabbitListener(queues = "Forlogen")
    public void receive1(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
