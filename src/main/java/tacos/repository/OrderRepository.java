package tacos.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.domain.Taco;
import tacos.domain.TacoOrder;
import tacos.domain.Users;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {

    public List<TacoOrder> findByUserOrderByPlacedAtDesc(Users user, Pageable pageable);
}

