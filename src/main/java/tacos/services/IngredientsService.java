//package tacos.services;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//import tacos.domain.Ingredient;
//
//import java.net.URI;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Service
//public class IngredientsService {
//
//
//
//    public Ingredient getIngredientById(String ingredientId) {
//        return rest.getForObject("http://localhost:8080/data-api/ingredients/{id}",
//                Ingredient.class, ingredientId);
//    }
//
//
//    public Ingredient getIngredientByIdOtimizado(String ingredientId) {
//
//        Map<String, String> urlVariables = new HashMap<>();
//        urlVariables.put("id", ingredientId);
//        URI url = UriComponentsBuilder
//                .fromHttpUrl("http://localhost:8080/data-api/ingredients/{id}")
//                .build(urlVariables);
//        return rest.getForObject(url,Ingredient.class);
//
//    }
//
//
//    public void updateIngredient(Ingredient ingredient) {
//        rest.put("http://localhost:8080/data-api/ingredients/{id}",
//                ingredient, ingredient.getId());
//    }
//
//    public Ingredient createIngredient(Ingredient ingredient) {
//        ResponseEntity<Ingredient> responseEntity =
//                rest.postForEntity("http://localhost:8080/ingredients",
//                        ingredient,
//                        Ingredient.class);
//        log.info("New resource created at {}",
//                responseEntity.getHeaders().getLocation());
//        return responseEntity.getBody();
//    }
//
//
////    public Ingredient getIngredientByIdMap(String ingredientId) {
////        Map<String, String> urlVariables = new HashMap<>();
////        urlVariables.put("id", ingredientId);
////
////        return rest.getForObject("http://localhost:8080/ingredients/{id}",
////                Ingredient.class, urlVariables);
////    }
////    o RestTemplate do Spring Framework automaticamente reconhece e substitui as chaves do mapa na URL quando você usa getForObject() com um mapa de variáveis.
//
//}
