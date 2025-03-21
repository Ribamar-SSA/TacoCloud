package tacos.domain;

import jakarta.persistence.*; // Import JPA annotations ONLY
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
@Data
@Entity
@Table(name="Taco_Order")

public class TacoOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id  // Use JPA's @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Or GenerationType.SEQUENCE if needed
    private Long id; // Use Long or Integer (not long)

    private Date placedAt;

    @Column(name = "customer_name")
    @NotBlank(message = "deliveryName is required!")
    private String deliveryName;

    @NotBlank(message = "street is required")
    private String deliveryStreet;

    @NotBlank(message = "city is required!")
    private String deliveryCity;

    @NotBlank(message = "state is required!")
    private String deliveryState;

    @NotBlank(message = "zip is required!")
    private String deliveryZip;

    @CreditCardNumber(message = "not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[0-9]|1[0-2])([\\/])([2-9][0-9])$", message = "must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "invalid cvv")
    private String ccCVV;


    @ManyToMany(targetEntity=Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        log.info("teste supremo {}",taco);
        this.tacos.add(taco);
    }

    @ManyToOne
    private Users user;

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }

}