package guru.springframework.repositories;

import guru.springframework.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * @author john
 * @since 31/03/2024
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
