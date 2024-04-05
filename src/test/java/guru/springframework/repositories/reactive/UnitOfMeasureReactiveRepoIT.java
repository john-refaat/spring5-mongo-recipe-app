package guru.springframework.repositories.reactive;

import guru.springframework.bootstrap.RecipeBootstrap;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author john
 * @since 02/04/2024
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepoIT {

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    RecipeBootstrap recipeBootstrap;

    @Before
    public void setUp() throws Exception {
        recipeBootstrap = new RecipeBootstrap(null, null, unitOfMeasureRepository, null, null, unitOfMeasureReactiveRepository);
        Long count = unitOfMeasureReactiveRepository.count().block();
        if (count == 0L) {
            recipeBootstrap.loadUom();
        }

    }

    @Test
    public void findAll() throws Exception {
        Flux<UnitOfMeasure> all = unitOfMeasureReactiveRepository.findAll();
        Long count = all.count().block();
        Assert.assertEquals(Long.valueOf(8L), count);

    }

    @Test
    public void findByDescription() throws Exception {
        UnitOfMeasure uom= unitOfMeasureReactiveRepository.findByDescription("Teaspoon").block();
        System.out.println(uom);
        Assert.assertEquals("Teaspoon", uom.getDescription());
    }
}
