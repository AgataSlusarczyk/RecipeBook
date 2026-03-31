package recipeBook.services;

import org.springframework.stereotype.Service;
import recipeBook.exception.RecipeException;
import recipeBook.model.Recipe;
import recipeBook.RecipeRepository;

import java.util.List;
import java.util.Objects;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe findById(Long id) throws Exception {
        if (recipeRepository.existsById(id)) {
            return recipeRepository.findById(id).get();
        } else {
            throw new RecipeException("Recipe not found");
        }
    }


    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteById(Long id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
        }
    }

    public Recipe updateRecipe(Recipe recipe, Long recipeId) {
        Recipe recipeDB = recipeRepository.findById(recipeId).get();

        if (Objects.nonNull(recipe.getTitle())) {
            recipeDB.setTitle(recipe.getTitle());
        }
        if (Objects.nonNull(recipe.getCategories())) {
            recipeDB.setCategories(recipe.getCategories());
        }
        if (Objects.nonNull(recipe.getIngredients())) {
            recipeDB.setIngredients(recipe.getIngredients());
        }
        if (Objects.nonNull(recipe.getDescription())) {
            recipeDB.setDescription(recipe.getDescription());
        }
        recipeDB.setPrepTime(recipe.getPrepTime());
        recipeDB.setServings(recipe.getServings());

        if (Objects.nonNull(recipe.getSteps())) {
            recipeDB.setSteps(recipe.getSteps());
        }

        return recipeRepository.save(recipe);
    }

}
