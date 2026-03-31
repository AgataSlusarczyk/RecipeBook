package recipeBook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import recipeBook.model.Ingredient;
import recipeBook.model.Recipe;
import recipeBook.model.Step;

import java.time.LocalDateTime;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RecipeBookApplicationTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void shouldSaveAndFindRecipe() {
        Recipe recipe = new Recipe();
        recipe.setTitle("Spaghetti");
        recipe.setDescription("Klasyczny przepis");
        recipe.setServings(4);
        recipe.setPrepTime(20);
        recipe.setCreatedAt(LocalDateTime.now());

        recipeRepository.save(recipe);

        Optional<Recipe> found = recipeRepository.findByTitle("Spaghetti");
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Spaghetti");
        assertThat(found.get().getServings()).isEqualTo(4);
    }

    @Test
    void shouldReturnEmptyWhenRecipeNotFound() {
        Optional<Recipe> found = recipeRepository.findByTitle("Nieistniejący przepis");
        assertThat(found).isEmpty();
    }

    @Test
    void shouldSaveRecipeWithIngredients() {
        Recipe recipe = new Recipe();
        recipe.setTitle("Spaghetti");
        recipe.setCreatedAt(LocalDateTime.now());
        recipeRepository.save(recipe);

        Ingredient ingredient = Ingredient.builder()
                .name("Makaron")
                .quantity(200)
                .recipe(recipe)
                .build();

        recipe.getIngredients().add(ingredient);
        recipeRepository.save(recipe);

        Optional<Recipe> found = recipeRepository.findByTitle("Spaghetti");
        assertThat(found).isPresent();
        assertThat(found.get().getIngredients()).hasSize(1);
        assertThat(found.get().getIngredients().get(0).getName()).isEqualTo("Makaron");
    }

    @Test
    void shouldSaveRecipeWithSteps() {
        Recipe recipe = new Recipe();
        recipe.setTitle("Spaghetti");
        recipe.setCreatedAt(LocalDateTime.now());
        recipeRepository.save(recipe);

        Step step = Step.builder()
                .description("Zagotuj wodę")
                .number(1)
                .recipe(recipe)
                .build();

        recipe.getSteps().add(step);
        recipeRepository.save(recipe);

        Optional<Recipe> found = recipeRepository.findByTitle("Spaghetti");
        assertThat(found).isPresent();
        assertThat(found.get().getSteps()).hasSize(1);
        assertThat(found.get().getSteps().get(0).getDescription()).isEqualTo("Zagotuj wodę");
    }
}