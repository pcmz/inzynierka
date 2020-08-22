package pl.agh.student.pcmz.pracainzynierska.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.agh.student.pcmz.pracainzynierska.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
