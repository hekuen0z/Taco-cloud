package edu.taco_cloud.messaging;

import edu.taco_cloud.models.TacoOrder;

public interface OrderMessagingService {

    void sendOrder(TacoOrder order);

    void convertAndSendOrder(TacoOrder order);
}
