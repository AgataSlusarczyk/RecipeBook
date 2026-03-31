package recipeBook.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    private int servings;

    private int prepTime;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "recipe")
    private List<Step> steps;

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients;


}
