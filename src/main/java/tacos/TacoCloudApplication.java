package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tacos.domain.Ingredient;
import tacos.domain.OrderProps;
import tacos.domain.Taco;
import tacos.repository.IngredientsRepository;
import tacos.repository.TacoRepository;
//import tacos.services.IngredientsService;

import java.util.Arrays;

import static tacos.domain.Ingredient.*;

@Slf4j
@SpringBootApplication
//@EnableWebSecurity
@EnableConfigurationProperties(OrderProps.class)
public class TacoCloudApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);


    }

}
