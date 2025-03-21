package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import tacos.domain.Users;
import tacos.repository.UserRepository;

;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    public UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //BCryptPasswordEncoder é um dos melhores  funções de hash de senha
        //bcrypt: Uma das funções hash mais seguras
        return new BCryptPasswordEncoder();
    }




    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests((authorize) ->
                        {
//                            authorize.requestMatchers("/home","/register2/**").permitAll();
//                            authorize.requestMatchers("/register").permitAll();
//                            authorize.requestMatchers("/api/**").permitAll();
//                            authorize.requestMatchers("/design").hasRole("USER");
//                            authorize.requestMatchers("/orders/**").hasRole("USER");
                            authorize.requestMatchers("/**").permitAll();
                            authorize.anyRequest().authenticated();

                        }
                )
//                .oauth2Login(Customizer.withDefaults())

                .formLogin(httpSecurityFormLoginConfigurer ->{
                    httpSecurityFormLoginConfigurer.loginPage("/login")
                            .defaultSuccessUrl("/design",true)
                            .permitAll();
                }  );

        return http.build();
    }



    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


}
