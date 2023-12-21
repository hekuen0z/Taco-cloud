package edu.taco_cloud.messaging;

import edu.taco_cloud.models.TacoOrder;
import edu.taco_cloud.ui.KitchenUI;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(TacoOrder order){
        ui.displayOrder(order);
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(TacoOrder order, ConsumerRecord<String, TacoOrder> record) {
        log.info("Receiver from partition {} with timestamp {}",
                record.partition(), record.timestamp());
        ui.displayOrder(order);
    }
}
