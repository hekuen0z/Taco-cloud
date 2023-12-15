package edu.taco_cloud.utilities;

import edu.taco_cloud.models.Ingredient;
import edu.taco_cloud.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Класс-конвертер преобразует id ингредиентов, получаемых из формы,
 * и возвращает объект класса Ingredient.
 * <p>
 * Spring автоматически зарегистрирует этот класс как конвертер и
 * будет использовать, когда потребуется преобразовать параметры запроса в свойства.
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientByIdConverter(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientService.findById(id);
    }

}
