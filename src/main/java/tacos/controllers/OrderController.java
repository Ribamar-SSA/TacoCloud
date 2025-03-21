package tacos.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.domain.OrderProps;
import tacos.domain.TacoOrder;
import tacos.domain.Users;
import tacos.repository.OrderRepository;
import tacos.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderProps props;
    private UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, OrderProps props, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.props = props;
        this.userRepository = userRepository;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute TacoOrder order) {
        Optional<Users> byUsername = userRepository.findByUsername(user.getUsername());
        Users users = byUsername.orElseThrow();

        if (order.getDeliveryName() == null) {
            order.setDeliveryName(users.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(users.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(users.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(users.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(users.getZip());
        }

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        Optional<Users> byUsername = userRepository.findByUsername(user.getUsername());
        Users users = byUsername.orElseThrow();

        order.setUser(users);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/design";
    }
}
