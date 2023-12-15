package edu.taco_cloud.utilities;

import edu.taco_cloud.models.Ingredient;
import edu.taco_cloud.models.UDT.IngredientUDT;

public class TacoUDRUtils {

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }
}
