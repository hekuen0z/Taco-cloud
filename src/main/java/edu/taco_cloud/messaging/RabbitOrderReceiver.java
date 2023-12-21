package edu.taco_cloud.messaging;

import edu.taco_cloud.models.TacoOrder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderReceiver {

    private RabbitTemplate rabbitTemplate;
    private MessageConverter converter;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.converter = this.rabbitTemplate.getMessageConverter();
    }

    public TacoOrder receiveOrder() {
        Message message = rabbitTemplate.receive("tacocloud.order.queue");
        return message != null
                ? (TacoOrder) converter.fromMessage(message)
                : null;
    }

    public TacoOrder receiveAndConvertOrder() {
        return rabbitTemplate.receiveAndConvert("tacocloud.order.queue",
                new ParameterizedTypeReference<TacoOrder>() {});
    }
}
