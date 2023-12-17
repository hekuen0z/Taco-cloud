package edu.taco_cloud.controllers.rest;

import edu.taco_cloud.models.TacoOrder;
import edu.taco_cloud.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/orders", produces = "applications/json")
@CrossOrigin(origins = "http://localhost:8080")
public class RestOrderController {

    private final OrderService orderService;

    @Autowired
    public RestOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Метод для обновления данных о заказе
     * Требует указания каждого из полей объекта TacoOrder
     * Иначе заполнит неуказанные свойства null'ами
     * @param orderId - идентификатора того, что надо обновить
     * @param order - измененный объект
     * @return - хранимый объект TacoOrder
     */
    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder putOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody TacoOrder order) {

        order.setId(orderId);
        return orderService.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody TacoOrder order) {

        TacoOrder tempOrder = orderService.findById(orderId);
        if (order.getDeliveryName() != null) {
            tempOrder.setDeliveryName(order.getDeliveryName());
        }
        if (order.getDeliveryStreet() != null) {
            tempOrder.setDeliveryStreet(order.getDeliveryStreet());
        }
        if (order.getDeliveryCity() != null) {
            tempOrder.setDeliveryCity(order.getDeliveryCity());
        }
        if (order.getDeliveryState() != null) {
            tempOrder.setDeliveryState(order.getDeliveryState());
        }
        if (order.getDeliveryZip() != null) {
            tempOrder.setDeliveryZip(order.getDeliveryZip());
        }
        if (order.getCcNumber() != null) {
            tempOrder.setCcNumber(order.getCcNumber());
        }
        if (order.getCcExpiration() != null) {
            tempOrder.setCcExpiration(order.getCcExpiration());
        }
        if (order.getCcCVV() != null) {
            tempOrder.setCcCVV(order.getCcCVV());
        }
        return orderService.save(order);
    }


}
