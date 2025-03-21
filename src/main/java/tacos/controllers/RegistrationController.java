package tacos.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.domain.RegistrationForm;
import tacos.domain.Users;
import tacos.repository.UserRepository;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return "registerForm";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form){
        //antes de salvar, passa para  toUser codificar antes de salvar no banco de dados
        Users uss = userRepository.save(form.toUser(passwordEncoder));
        log.info(uss.toString());
        return "redirect:/login";
    }





}
