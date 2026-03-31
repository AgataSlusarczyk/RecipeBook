package recipeBook;

import org.springframework.web.bind.annotation.*;
import recipeBook.model.Recipe;
import recipeBook.services.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/")
public class RecipeController {
    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }


    @GetMapping("/recipes")
    public List<Recipe> findAllRecipes() {
        return service.findAll();
    }

    @GetMapping("/recipes/{id}")
    public Recipe findRecipe(@PathVariable long id) throws Exception {
        return service.findById(id);
    }

    @PostMapping("/recipes")
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return service.save(recipe);
    }

    @PutMapping("/recipes")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @RequestParam Long id){
        return service.updateRecipe(recipe,id);
    }

    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable Long id){
        service.deleteById(id);
    }
}
