package tacos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.domain.Users;
import tacos.repository.UserRepository;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Users users = user.get();
            return User.withUsername(users.getUsername())
                    .password(users.getPassword())
                    .roles(users.getRole())
                    .build();

        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}