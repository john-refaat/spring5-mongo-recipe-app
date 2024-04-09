package guru.springframework.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class Notes {

    public Notes(String id, String recipeNotes) {
        this.id = id;
        this.recipeNotes = recipeNotes;
    }

    private String id;
    private String recipeNotes;

}
