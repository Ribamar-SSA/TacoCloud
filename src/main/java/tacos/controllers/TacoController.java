package tacos.controllers;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.domain.Taco;
import tacos.domain.TacoOrder;
import tacos.repository.OrderRepository;
import tacos.repository.TacoRepository;

import java.util.List;
import java.util.Optional;
@Controller
@RequestMapping(path = "/api/tacos")
@CrossOrigin(origins = "http://localhost:8080")
@Slf4j
public class TacoController {
    private TacoRepository tacoRepository;
    private OrderRepository orderRepository;

    public TacoController(TacoRepository tacoRepository, OrderRepository orderRepository) {
        this.tacoRepository = tacoRepository;
        this.orderRepository=orderRepository;
    }


    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0,12, Sort.by("CreatedAt").descending());
        log.info(tacoRepository.toString());
        return tacoRepository.findAll(page).getContent();
    }


    @PostMapping
    public String processTaco(
            @Valid Taco taco, Errors errors,
            @ModelAttribute TacoOrder order) {

        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = tacoRepository.save(taco);
        order.addTaco(saved);

        return "redirect:/orders/current";
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Taco> tacoById(@PathVariable ("id") Long id){
//        Optional<Taco> byId = tacoRepository.findById(id);
//
//        if(byId.isPresent()){
//            return new ResponseEntity<>(byId.get(), HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping()
    public ResponseEntity<List<Taco>>allTacos(){
        Optional<List<Taco>> byId = Optional.of(tacoRepository.findAll());

        if(byId.isPresent()){
            return new ResponseEntity<>(byId.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

//
//    @PostMapping(consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Taco postTaco(@RequestBody Taco taco){
//        return tacoRepository.save(taco);
//    }


    @PatchMapping(path = "/{orderId}")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder patch){
        TacoOrder tacoOrder = orderRepository.findById(orderId).get();

        if(patch.getDeliveryName() != null){
            tacoOrder.setDeliveryName(patch.getDeliveryName());
        }
        if(patch.getTacos() != null){
            tacoOrder.setTacos(patch.getTacos());
        }
        if(patch.getCcCVV() !=null){
            tacoOrder.setCcCVV(patch.getCcCVV());
        }
        if(patch.getCcExpiration() != null){
            tacoOrder.setCcExpiration(patch.getCcExpiration());
        }
        if(patch.getCcNumber() != null){
            tacoOrder.setCcNumber(patch.getCcNumber());
        }
        if(patch.getDeliveryCity() != null){
            tacoOrder.setDeliveryCity(patch.getDeliveryCity());
        }
        if(patch.getDeliveryZip() != null){
            tacoOrder.setDeliveryCity(patch.getDeliveryZip());
        }
        if(patch.getDeliveryState() != null){
            tacoOrder.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryStreet() != null) {
            tacoOrder.setDeliveryStreet(patch.getDeliveryStreet());
        }

        return orderRepository.save(tacoOrder);

    }


    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        try{
            tacoRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
        }
    }
}
