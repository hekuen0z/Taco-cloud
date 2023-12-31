package edu.taco_cloud.controllers;

import edu.taco_cloud.models.Ingredient;
import edu.taco_cloud.models.Taco;
import edu.taco_cloud.models.TacoOrder;
import edu.taco_cloud.services.IngredientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static edu.taco_cloud.models.Ingredient.*;
import static java.util.stream.Collectors.*;
import static org.aspectj.apache.bcel.Constants.types;

@Slf4j
@Controller
@RequestMapping("/design")
//Объект TacoOrder должен поддерживаться на уровне сеанса (сессии),
//чтобы перенести этот объект на несколько запросов
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientService ingredientService;

    @Autowired
    public DesignTacoController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    /**
     * Метод фильтрует ингредиенты по типам.
     * @param model - список ингредиентов для передачи в вызов showDesignForm()
     * @see DesignTacoController#showDesignForm()
     */
    @ModelAttribute
    public void addIngredientsToModel(final Model model) {
        Iterable<Ingredient> ingredientIterable = ingredientService.findAll();
        List<Ingredient> ingredients = new ArrayList<>();
        for(Ingredient i : ingredientIterable) {
            ingredients.add(i);
        }

        System.out.println(ingredientIterable.toString());

        Type[] types = Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    /**
     * Функция разделяет общую коллекцию по типам.
     * @param ingredients - список всех ингредиентов
     * @param type - тип ингредиента
     * @return - коллекцию ингредиентов одного типа
     */
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(toList());
    }

    /**
     * Создание объекта TacoOrder
     * @return - объект класса TacoOrder
     */
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    /**
     * Создание объекта Taco
     * @return - объект класса Taco
     */
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    /**
     * @return - Возвращает представление для дизайна тако
     */
    @GetMapping
    public String showDesignForm() {
        return "designPage";
    }

    /**
     * Обрабатывает запрос на создание пользовательского тако
     * @param taco - созданное тако
     * @param tacoOrder - заказ текущего пользователя
     * @param errors - содержит ошибки валидации при заполнении форм
     * @return - возвращает представление текущих заказов пользователя
     */
    @PostMapping
    public String processTaco(@Valid Taco taco,
                              Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if(errors.hasErrors()) {
            return "designPage";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }
}
