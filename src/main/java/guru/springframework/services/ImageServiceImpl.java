package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by jt on 7/3/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Mono<Recipe> saveImageFile(String recipeId, MultipartFile file) {
        System.out.println("================================================");
        Mono<Recipe> recipeMono =  recipeReactiveRepository.findById(recipeId).map(recipe -> {
            System.out.println("****************************************************************");
            try {
                Byte[] byteObjects = new Byte[file.getBytes().length];
                int i = 0;

                for (byte b : file.getBytes()){
                    byteObjects[i++] = b;
                }

                recipe.setImage(byteObjects);
                return recipe;
            } catch (IOException e) {
                log.info("Error uploading file", e);
                throw new RuntimeException(e);
            }
        });


        return recipeReactiveRepository.save(Objects.requireNonNull(recipeMono.block()));
    }
}
