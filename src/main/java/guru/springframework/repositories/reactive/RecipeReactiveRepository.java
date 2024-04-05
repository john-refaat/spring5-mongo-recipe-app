package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author john
 * @since 02/04/2024
 */
public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {

    Mono<Recipe> findByDescription(String description);

}
