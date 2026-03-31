package recipeBook;

import org.springframework.data.jpa.repository.JpaRepository;
import recipeBook.model.Recipe;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    Optional<Recipe> findByTitle(String title);
}
