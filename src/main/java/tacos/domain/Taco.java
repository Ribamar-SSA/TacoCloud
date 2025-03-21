package tacos.domain;

import jakarta.persistence.*; // Import JPA annotations
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
//import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;
import java.util.List;

@Entity // JPA Entity annotation
@Data
//@RestResource(rel = "tacos" ,path = "tacos")
public class Taco {

    @Id // JPA Id annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use Long or Integer for auto-generated IDs

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "you must choose at least 1 ingredient")
    @ManyToMany(targetEntity = Ingredient.class) // Or @OneToMany, depending on your relationship
    private List<Ingredient> ingredients;


}