package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author john
 * @since 02/04/2024
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Test
    public void testSave() throws Exception {
        Category category = new Category();
        category.setDescription("Asian");
        Category savedCat = categoryReactiveRepository.save(category).block();
        Assert.assertNotNull(savedCat.getId());
        Assert.assertEquals("Asian", savedCat.getDescription());
    }
}