package rabbitmqRevoServer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqRevoServerApplication implements CommandLineRunner {
    @Autowired
    AmqpAdmin amqpAdmin;
    @Autowired
    FanoutExchange fanoutExchange;
    @Autowired
    DirectExchange directExchange;
    @Autowired
    RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqRevoServerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        newPerson("mac");
        rabbitTemplate.convertAndSend("directExchange", "ashraf", "welcome");
        rabbitTemplate.convertAndSend("fanoutExchange","","welcome");

    }

    public void newPerson(String name) {
        Queue queue = new Queue(name);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(fanoutExchange));
        amqpAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).withQueueName());
    }
}
