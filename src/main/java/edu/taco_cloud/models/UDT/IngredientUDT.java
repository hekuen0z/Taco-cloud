package edu.taco_cloud.models.UDT;

import edu.taco_cloud.models.Ingredient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

/**
 * Класс для денормализации БД Cassandra.
 * Хранит копию данных из таблицы Ingredient в классе Taco
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@UserDefinedType("ingredient")
public class IngredientUDT {

    private final String name;

    private final Ingredient.Type type;
}
