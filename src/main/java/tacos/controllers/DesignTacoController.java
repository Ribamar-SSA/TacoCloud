package tacos.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.domain.Ingredient;
import tacos.domain.Ingredient.Type;
import tacos.domain.Taco;
import tacos.domain.TacoOrder;
import tacos.domain.Users;
import tacos.repository.IngredientsRepository;
import tacos.repository.OrderRepository;
import tacos.repository.TacoRepository;
import tacos.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientsRepository ingredientRepository;

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final TacoRepository tacoRepository;


    public DesignTacoController(IngredientsRepository ingredientRepository, OrderRepository orderRepository, UserRepository userRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> all = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredient -> {all.add(ingredient);});
        for (Type type : Ingredient.Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(all, type));
        }

    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public  String showDesignForm() {
     return "design";
    }


    @ModelAttribute(name = "user")
    public Users user(Principal principal) {
        String username = principal.getName();
        Optional<Users> byUsername = userRepository.findByUsername(username);
        return byUsername.orElseThrow();
    }


    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute("tacoOrder") TacoOrder tacoOrder ){
        if(errors.hasErrors()){
            return "design";
        }
        tacoRepository.save(taco);
        tacoOrder.addTaco(taco);//adicionando na sessão

        return "redirect:/orders/current";

    }


}