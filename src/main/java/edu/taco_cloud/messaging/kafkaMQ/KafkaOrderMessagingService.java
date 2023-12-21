package edu.taco_cloud.messaging.kafkaMQ;

import edu.taco_cloud.messaging.OrderMessagingService;
import edu.taco_cloud.models.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderMessagingService
        implements OrderMessagingService {

    private KafkaTemplate<String, TacoOrder> kafkaTemplate;

    @Autowired
    public KafkaOrderMessagingService(KafkaTemplate<String, TacoOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        kafkaTemplate.sendDefault(order);
    }

    @Override
    public void convertAndSendOrder(TacoOrder order) {
        kafkaTemplate.sendDefault(order);
    }
}
