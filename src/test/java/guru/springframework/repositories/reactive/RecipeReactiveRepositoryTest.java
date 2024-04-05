package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author john
 * @since 02/04/2024
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Test
    public void testSave() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setDescription("Yummy");
        recipeReactiveRepository.save(recipe).block();
        Recipe saved = recipeReactiveRepository.findByDescription("Yummy").block();
        Assert.assertEquals("Yummy", saved.getDescription());
    }
}