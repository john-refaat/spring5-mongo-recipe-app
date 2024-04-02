package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author john
 * @since 02/04/2024
 */
public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {
}
