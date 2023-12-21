package edu.taco_cloud.controllers.rest;

import edu.taco_cloud.models.Ingredient;
import edu.taco_cloud.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/ingredients", produces="application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class RestIngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public RestIngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return ingredientService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingId) {
        ingredientService.deleteById(ingId);
    }
}
