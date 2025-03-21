package tacos.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)

public class Ingredient implements Persistable<String> {
    @Id
    private final String id;
    private final String name;
    private final Type type;

    @Override
    public boolean isNew() {
        return false;
    }

    public enum Type{
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    }




}
