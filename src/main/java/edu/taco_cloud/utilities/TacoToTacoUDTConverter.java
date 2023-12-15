package edu.taco_cloud.utilities;

import edu.taco_cloud.models.Taco;
import edu.taco_cloud.models.UDT.TacoUDT;

public class TacoToTacoUDTConverter {

    public static TacoUDT convertTacoToTacoUDT(Taco taco) {
        return new TacoUDT(taco.getName(), taco.getIngredients());
    }
}
