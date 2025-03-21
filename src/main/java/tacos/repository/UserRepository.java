package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.Users;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users,Long> {

    Optional<Users> findByUsername(String username);
}
