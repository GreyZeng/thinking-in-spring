package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.TacoOrder;

import java.util.UUID;

public interface OrderRepository
        extends CrudRepository<TacoOrder, UUID> {

}
