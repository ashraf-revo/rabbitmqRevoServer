package rabbitmqRevoServer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by ashraf on 9/20/2015.
 */
@Component
public class ServerRevoListener {
    @RabbitListener(queues = "toServer")
    public void recive(String message) {
        System.out.println(message);
    }
}
