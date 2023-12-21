package edu.taco_cloud.messaging.rabbitMQ;

import edu.taco_cloud.messaging.OrderMessagingService;
import edu.taco_cloud.models.TacoOrder;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitOrderMessagingService
        implements OrderMessagingService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        MessageConverter converter = rabbitTemplate.getMessageConverter();
        MessageProperties props = new MessageProperties();
        props.setHeader("ORDER_SOURCE", "WEB");
        Message message = converter.toMessage(order, props);
        rabbitTemplate.send("tacocloud.order", message);
    }

    @Override
    public void convertAndSendOrder(TacoOrder order) {
        rabbitTemplate.convertAndSend("tacocloud.order", order,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        MessageProperties props = message.getMessageProperties();
                        props.setHeader("ORDER_SOURCE", "WEB");
                        return message;
                    }
                }
        );
    }


}
