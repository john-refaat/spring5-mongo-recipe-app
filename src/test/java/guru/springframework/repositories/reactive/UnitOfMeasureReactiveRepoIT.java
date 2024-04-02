package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
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

    @Before
    public void setUp() throws Exception {
        Long count = unitOfMeasureReactiveRepository.count().block();
        if (count.equals(0L)) {
            UnitOfMeasure teaspoon = new UnitOfMeasure();
            teaspoon.setDescription("teaspoon");

            UnitOfMeasure tablespoon = new UnitOfMeasure();
            tablespoon.setDescription("tablespoon");

            UnitOfMeasure cup = new UnitOfMeasure();
            cup.setDescription("cup");

            List<UnitOfMeasure> items = new ArrayList<UnitOfMeasure>();
            items.add(teaspoon);
            items.add(tablespoon);
            items.add(cup);
            unitOfMeasureReactiveRepository.saveAll(items).blockFirst();
        }
    }

    @Test
    public void findAll() throws Exception {
        Flux<UnitOfMeasure> all = unitOfMeasureReactiveRepository.findAll();
        Long count = all.count().block();
        Assert.assertEquals(Long.valueOf(3L), count);

    }

    @Test
    public void findByDescription() throws Exception {
        Mono<UnitOfMeasure> uomOptional = unitOfMeasureReactiveRepository.findByDescription("teaspoon");

        Assert.assertEquals("teaspoon", uomOptional.block().getDescription());
    }
}
