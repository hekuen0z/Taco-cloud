package edu.taco_cloud.controllers;

import edu.taco_cloud.models.TacoOrder;
import edu.taco_cloud.services.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Метод для обработки заказа пользователя
     *
     * @return - возвращает представление для завершения заказа
     */
    @GetMapping("/current")
    public String orderForm() {
        return "orderPage";
    }

    /**
     * Обрабатывает информацию, полученную от пользователя по завершению заказа
     *
     * @param order         - текущий заказ
     * @param sessionStatus - используется для закрытия сессии TacoOrder
     * @param errors        - содержит ошибки валидации TacoOrder
     * @return - возвращает представление главной страницы
     */
    @PostMapping
    public String processOrder(@Valid TacoOrder order,
                               Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderPage";
        }

        log.info("Order submitted: {}", order);
        orderService.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
