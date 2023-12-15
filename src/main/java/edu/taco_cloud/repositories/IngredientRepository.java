package edu.taco_cloud.repositories;

import edu.taco_cloud.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {

}
