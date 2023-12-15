package edu.taco_cloud.services;

import edu.taco_cloud.models.Ingredient;
import edu.taco_cloud.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    public Iterable<Ingredient> findAll() {
        return ingredientRepo.findAll();
    }

    public Ingredient findById(String id) {
        System.out.println("id type: " + id);
        return ingredientRepo.findById(id).orElseThrow();
    }

    public Ingredient save(Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }
}
