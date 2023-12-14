package edu.taco_cloud.controllers;

import edu.taco_cloud.models.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

    /**
     * Метод для обработки заказа пользователя
     * @return - возвращает представление для завершения заказа
     */
    @GetMapping("/current")
    public String orderForm() {
        return "orderPage";
    }

    /**
     * Обрабатывает информацию, полученную от пользователя по завершению заказа
     * @param order - текущий заказ
     * @param sessionStatus
     * @return - возвращает представление главной страницы
     */
    @PostMapping
    public String processOrder(TacoOrder order,
                               SessionStatus sessionStatus) {
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
