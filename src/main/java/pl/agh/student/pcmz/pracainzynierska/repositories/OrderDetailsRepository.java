package pl.agh.student.pcmz.pracainzynierska.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.agh.student.pcmz.pracainzynierska.models.OrderDetails;

@Repository
public interface OrderDetailsRepository  extends JpaRepository<OrderDetails, Long> {
}
