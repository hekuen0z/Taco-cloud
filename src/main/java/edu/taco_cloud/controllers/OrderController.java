package edu.taco_cloud.controllers;

import edu.taco_cloud.models.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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
     * @param sessionStatus - используется для закрытия сессии TacoOrder
     * @param errors - содержит ошибки валидации TacoOrder
     * @return - возвращает представление главной страницы
     */
    @PostMapping
    public String processOrder(@Valid TacoOrder order,
                               Errors errors,
                               SessionStatus sessionStatus) {
        if(errors.hasErrors()) {
            return "orderPage";
        }

        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
